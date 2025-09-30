package com.jaba.heraldism.common.util;

import java.time.Instant;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;

/**
 * Utility class for date and time operations.
 */
public class DateTimeUtils {
    
    private static final DateTimeFormatter ISO_FORMATTER = DateTimeFormatter.ISO_DATE_TIME;
    
    private DateTimeUtils() {
        // Utility class, prevent instantiation
    }
    
    /**
     * Get current timestamp as Instant.
     *
     * @return Current Instant
     */
    public static Instant now() {
        return Instant.now();
    }
    
    /**
     * Convert epoch seconds to Instant.
     *
     * @param epochSeconds Epoch seconds
     * @return Instant
     */
    public static Instant fromEpochSeconds(long epochSeconds) {
        return Instant.ofEpochSecond(epochSeconds);
    }
    
    /**
     * Convert Instant to epoch seconds.
     *
     * @param instant Instant
     * @return Epoch seconds
     */
    public static long toEpochSeconds(Instant instant) {
        return instant.getEpochSecond();
    }
    
    /**
     * Format Instant to ISO 8601 string.
     *
     * @param instant Instant to format
     * @return Formatted string
     */
    public static String formatIso(Instant instant) {
        return ISO_FORMATTER.format(
            LocalDateTime.ofInstant(instant, ZoneId.systemDefault())
        );
    }
}
