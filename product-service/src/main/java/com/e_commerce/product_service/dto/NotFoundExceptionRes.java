package com.e_commerce.product_service.dto;

import org.springframework.http.HttpStatusCode;

public record NotFoundExceptionRes(String message,
                                   String timestamp,
                                   HttpStatusCode httpStatusCode,
                                   String path) {
}
