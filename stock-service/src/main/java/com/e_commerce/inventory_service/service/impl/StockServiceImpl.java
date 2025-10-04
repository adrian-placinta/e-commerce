package com.e_commerce.inventory_service.service.impl;

import com.e_commerce.inventory_service.dto.StockReq;
import com.e_commerce.inventory_service.dto.StockRes;
import com.e_commerce.inventory_service.exceptions.InsufficientStockException;
import com.e_commerce.inventory_service.exceptions.StockNotFoundException;
import com.e_commerce.inventory_service.model.Stock;
import com.e_commerce.inventory_service.repository.StockRepository;
import com.e_commerce.inventory_service.service.StockService;
import com.e_commerce.inventory_service.util.StockMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.util.List;

@Service
@RequiredArgsConstructor
public class StockServiceImpl implements StockService {
    private final StockRepository stockRepository;

    @Override
    @Transactional(readOnly = true)
    public List<StockRes> getAllStocks(int pageNo, int pageSize) {
        return stockRepository
                .findAll(PageRequest.of(pageNo, pageSize))
                .stream()
                .map(StockMapper::toRes)
                .toList();
    }

    @Override
    @Transactional(readOnly = true)
    public StockRes getStockByProductId(final long productId) {
        return stockRepository
                .findByProductId(productId)
                .map(StockMapper::toRes)
                .orElseThrow(() ->
                        new StockNotFoundException(String
                                .format("Stock cannot be found for product with id %s", productId)));
    }

    @Override
    @Transactional
    public List<StockRes> saveStock(final List<StockReq> stocks) {
        List<Stock> stockList = stocks
                .stream()
                .map(StockMapper::toEntity)
                .toList();

        return stockRepository
                .saveAll(stockList)
                .stream()
                .map(StockMapper::toRes)
                .toList();
    }

    @Override
    @Transactional
    public void deleteStockByProductId(final long productId) {
        if (!stockRepository.existsByProductId(productId)) {
            throw new StockNotFoundException(String
                    .format("Stock cannot be found for product with id %s", productId));
        }
        stockRepository.deleteByProductId(productId);
    }

    @Override
    @Transactional
    public List<StockRes> decreaseStocks(List<StockReq> stockReqs) {
        List<Stock> updatedStocks = stockReqs.stream()
                .map(req -> stockRepository
                        .findByProductId(req.getProductId())
                        .map(dbStock -> decreaseStock(dbStock, req.getQuantity()))
                        .orElseThrow(() -> new StockNotFoundException(
                                "Stock not found for product ID: " + req.getProductId())))
                .toList();

        return stockRepository.saveAll(updatedStocks)
                .stream()
                .map(StockMapper::toRes)
                .toList();
    }

    @Override
    @Transactional
    public List<StockRes> increaseStocks(List<StockReq> stockReqs) {
        List<Stock> updatedStocks = stockReqs.stream()
                .map(req -> stockRepository
                        .findByProductId(req.getProductId())
                        .map(dbStock -> increaseStock(dbStock, req.getQuantity()))
                        .orElseThrow(() -> new StockNotFoundException(
                                "Stock not found for product ID: " + req.getProductId())))
                .toList();

        return stockRepository.saveAll(updatedStocks)
                .stream()
                .map(StockMapper::toRes)
                .toList();
    }

    private Stock decreaseStock(final Stock dbStock, final BigDecimal quantity) {
        if (dbStock.getQuantity().compareTo(quantity) < 0) {
            throw new InsufficientStockException(String.format(
                    "Insufficient stock for product %s. Available: %s, Requested: %s",
                    dbStock.getProductId(), dbStock.getQuantity(), quantity));
        }

        dbStock.setQuantity(dbStock.getQuantity().subtract(quantity));

        return dbStock;
    }

    private Stock increaseStock(final Stock dbStock, final BigDecimal quantity) {
        dbStock.setQuantity(dbStock.getQuantity().add(quantity));
        return dbStock;
    }
}