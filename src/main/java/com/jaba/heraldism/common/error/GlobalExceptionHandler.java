package com.jaba.heraldism.common.error;

import com.jaba.heraldism.common.dto.ApiResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.context.request.WebRequest;

import java.util.HashMap;
import java.util.Map;

/**
 * Global exception handler for the application.
 */
@Slf4j
@RestControllerAdvice
public class GlobalExceptionHandler {
    
    @ExceptionHandler(DotaServiceException.class)
    public ResponseEntity<ApiResponse<Void>> handleDotaServiceException(
            DotaServiceException ex, WebRequest request) {
        log.error("DotaServiceException: {}", ex.getMessage(), ex);
        
        ApiResponse<Void> response = ApiResponse.error(
                ex.getMessage(),
                ex.getErrorCode().getCode()
        );
        
        HttpStatus status = mapErrorCodeToHttpStatus(ex.getErrorCode());
        return new ResponseEntity<>(response, status);
    }
    
    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<ApiResponse<Map<String, String>>> handleValidationException(
            MethodArgumentNotValidException ex) {
        log.error("Validation error: {}", ex.getMessage());
        
        Map<String, String> errors = new HashMap<>();
        ex.getBindingResult().getAllErrors().forEach(error -> {
            String fieldName = ((FieldError) error).getField();
            String errorMessage = error.getDefaultMessage();
            errors.put(fieldName, errorMessage);
        });
        
        ApiResponse<Map<String, String>> response = ApiResponse.<Map<String, String>>builder()
                .success(false)
                .data(errors)
                .message("Validation failed")
                .errorCode(ErrorCode.VALIDATION_ERROR.getCode())
                .build();
        
        return new ResponseEntity<>(response, HttpStatus.BAD_REQUEST);
    }
    
    @ExceptionHandler(Exception.class)
    public ResponseEntity<ApiResponse<Void>> handleGenericException(
            Exception ex, WebRequest request) {
        log.error("Unexpected error: {}", ex.getMessage(), ex);
        
        ApiResponse<Void> response = ApiResponse.error(
                "An unexpected error occurred",
                ErrorCode.INTERNAL_SERVER_ERROR.getCode()
        );
        
        return new ResponseEntity<>(response, HttpStatus.INTERNAL_SERVER_ERROR);
    }
    
    private HttpStatus mapErrorCodeToHttpStatus(ErrorCode errorCode) {
        return switch (errorCode) {
            case RESOURCE_NOT_FOUND, PLAYER_NOT_FOUND, MATCH_NOT_FOUND, SCORE_NOT_FOUND ->
                    HttpStatus.NOT_FOUND;
            case DUPLICATE_RESOURCE, PLAYER_ALREADY_EXISTS, DUPLICATE_REQUEST, IDEMPOTENCY_CONFLICT ->
                    HttpStatus.CONFLICT;
            case VALIDATION_ERROR, INVALID_STEAM_ID, INVALID_MATCH_ID ->
                    HttpStatus.BAD_REQUEST;
            case OPENDOTA_API_ERROR, RAG_SERVICE_ERROR ->
                    HttpStatus.BAD_GATEWAY;
            default ->
                    HttpStatus.INTERNAL_SERVER_ERROR;
        };
    }
}
