package com.jaba.heraldism.common.error;

/**
 * Application-wide error codes.
 */
public enum ErrorCode {
    
    // General errors (1000-1999)
    INTERNAL_SERVER_ERROR("ERR-1000", "An internal server error occurred"),
    VALIDATION_ERROR("ERR-1001", "Validation failed"),
    RESOURCE_NOT_FOUND("ERR-1002", "Resource not found"),
    DUPLICATE_RESOURCE("ERR-1003", "Resource already exists"),
    
    // Player errors (2000-2999)
    PLAYER_NOT_FOUND("ERR-2000", "Player not found"),
    PLAYER_ALREADY_EXISTS("ERR-2001", "Player already exists"),
    INVALID_STEAM_ID("ERR-2002", "Invalid Steam ID"),
    
    // Match errors (3000-3999)
    MATCH_NOT_FOUND("ERR-3000", "Match not found"),
    MATCH_FETCH_FAILED("ERR-3001", "Failed to fetch match data"),
    INVALID_MATCH_ID("ERR-3002", "Invalid match ID"),
    
    // Score errors (4000-4999)
    SCORE_CALCULATION_FAILED("ERR-4000", "Failed to calculate score"),
    SCORE_NOT_FOUND("ERR-4001", "Score not found"),
    
    // External service errors (5000-5999)
    OPENDOTA_API_ERROR("ERR-5000", "OpenDota API error"),
    RAG_SERVICE_ERROR("ERR-5001", "RAG service error"),
    NOTIFICATION_FAILED("ERR-5002", "Failed to send notification"),
    
    // Idempotency errors (6000-6999)
    DUPLICATE_REQUEST("ERR-6000", "Duplicate request detected"),
    IDEMPOTENCY_CONFLICT("ERR-6001", "Idempotency key conflict");
    
    private final String code;
    private final String message;
    
    ErrorCode(String code, String message) {
        this.code = code;
        this.message = message;
    }
    
    public String getCode() {
        return code;
    }
    
    public String getMessage() {
        return message;
    }
}
