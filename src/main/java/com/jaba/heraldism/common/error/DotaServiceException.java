package com.jaba.heraldism.common.error;

import lombok.Getter;

/**
 * Base exception for Dota Service.
 */
@Getter
public class DotaServiceException extends RuntimeException {
    
    private final ErrorCode errorCode;
    private final String details;
    
    public DotaServiceException(ErrorCode errorCode) {
        super(errorCode.getMessage());
        this.errorCode = errorCode;
        this.details = null;
    }
    
    public DotaServiceException(ErrorCode errorCode, String details) {
        super(errorCode.getMessage() + ": " + details);
        this.errorCode = errorCode;
        this.details = details;
    }
    
    public DotaServiceException(ErrorCode errorCode, String details, Throwable cause) {
        super(errorCode.getMessage() + ": " + details, cause);
        this.errorCode = errorCode;
        this.details = details;
    }
    
    public DotaServiceException(ErrorCode errorCode, Throwable cause) {
        super(errorCode.getMessage(), cause);
        this.errorCode = errorCode;
        this.details = null;
    }
}
