package com.jecsamtech.kapebackend.exception;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.Map;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class HttpError {

    private Integer status;
    private String message;
    private LocalDateTime errorTime;
    private Map<String, String> errors;

    public HttpError(Integer status, String message, LocalDateTime errorTime) {
        this.status = status;
        this.message = message;
        this.errorTime = errorTime;
    }
}
