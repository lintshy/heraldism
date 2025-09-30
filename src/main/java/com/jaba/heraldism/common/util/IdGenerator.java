package com.jaba.heraldism.common.util;

import java.util.UUID;

/**
 * Utility class for generating unique identifiers.
 */
public class IdGenerator {
    
    private IdGenerator() {
        // Utility class, prevent instantiation
    }
    
    /**
     * Generate a random UUID string.
     *
     * @return A random UUID string
     */
    public static String generateId() {
        return UUID.randomUUID().toString();
    }
    
    /**
     * Generate a prefixed ID with a random UUID.
     *
     * @param prefix The prefix to add to the ID
     * @return A prefixed UUID string
     */
    public static String generateId(String prefix) {
        return prefix + "-" + UUID.randomUUID().toString();
    }
}
