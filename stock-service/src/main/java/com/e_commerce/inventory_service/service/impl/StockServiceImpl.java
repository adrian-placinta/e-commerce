package com.e_commerce.inventory_service.service.impl;

import com.e_commerce.inventory_service.constant.StockExceptionMessage;
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

import java.util.List;

@Service
@RequiredArgsConstructor
public class StockServiceImpl implements StockService {

    private final StockRepository stockRepository;

    @Override
    @Transactional(readOnly = true)
    public List<StockRes> getAllStocks(int pageNo, int pageSize) {
        return stockRepository.findAll(PageRequest.of(pageNo, pageSize))
                .stream()
                .map(StockMapper::toRes)
                .toList();
    }

    @Override
    @Transactional(readOnly = true)
    public StockRes getStockByProductId(final long productId) {
        return stockRepository.findByProductId(productId)
                .map(StockMapper::toRes)
                .orElseThrow(() -> new StockNotFoundException(StockExceptionMessage.NOT_FOUND,
                        productId));
    }

    @Override
    @Transactional
    public List<StockRes> saveAllStocks(final List<StockReq> stocks) {
        List<Stock> stockEntities = stocks.stream()
                .map(StockMapper::toEntity)
                .toList();

        return stockRepository.saveAll(stockEntities)
                .stream()
                .map(StockMapper::toRes)
                .toList();
    }

    @Override
    @Transactional
    public List<StockRes> updateAllStocks(List<Stock> stocks) {
        return stockRepository.saveAll(stocks)
                .stream()
                .map(StockMapper::toRes)
                .toList();
    }

    @Override
    @Transactional
    public void deleteStockByProductId(final long productId) {
        if (!stockRepository.existsByProductId(productId)) {
            throw new StockNotFoundException(StockExceptionMessage.NOT_FOUND, productId);
        }
        stockRepository.deleteByProductId(productId);
    }

    @Transactional
    public StockRes subtractFromStockAndSave(final long productId, final long quantity) {
        Stock updatedStock = stockRepository.save(subtractFromStock(quantity, productId));
        return StockMapper.toRes(updatedStock);
    }

    @Transactional
    public StockRes addToStockAndSave(final long productId, final long quantity) {
        Stock updatedStock = stockRepository.save(addToStock(productId, quantity));
        return StockMapper.toRes(updatedStock);
    }

    @Override
    public Stock subtractFromStock(final long quantity, final long productId) {
        Stock stock = stockRepository.findByProductId(productId)
                .orElseThrow(() -> new StockNotFoundException(StockExceptionMessage.NOT_FOUND,
                        productId));

        if (stock.getQuantity() < quantity) {
            throw new InsufficientStockException(StockExceptionMessage.INSUFFICIENT,
                    productId,
                    stock.getQuantity(),
                    quantity);
        }

        stock.setQuantity(stock.getQuantity() - quantity);
        return stock;
    }

    @Override
    public Stock addToStock(final long productId, final long quantityToAdd) {
        Stock stock = stockRepository.findByProductId(productId)
                .orElseThrow(() -> new StockNotFoundException(StockExceptionMessage.NOT_FOUND,
                        productId));

        stock.setQuantity(stock.getQuantity() + quantityToAdd);
        return stock;
    }
}
