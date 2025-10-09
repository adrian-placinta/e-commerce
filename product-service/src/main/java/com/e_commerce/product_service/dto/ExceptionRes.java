package com.e_commerce.product_service.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import java.io.Serializable;
import org.springframework.http.HttpStatusCode;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ExceptionRes implements Serializable {
    private String message;
    private String timestamp;
    private HttpStatusCode httpStatusCode;
    private String path;
}
