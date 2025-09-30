package com.jaba.heraldism.domain;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.Instant;

/**
 * Domain entity representing a match performance score.
 */
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Score {
    
    private String matchId;
    private String playerId;
    private Double overallScore;
    private Double combatScore;
    private Double farmingScore;
    private Double supportScore;
    private Double objectiveScore;
    private String performanceGrade; // S, A, B, C, D, F
    private Instant calculatedAt;
    
    /**
     * Determine performance grade based on overall score.
     *
     * @param score Overall score (0-100)
     * @return Performance grade
     */
    public static String determineGrade(double score) {
        if (score >= 90) return "S";
        if (score >= 80) return "A";
        if (score >= 70) return "B";
        if (score >= 60) return "C";
        if (score >= 50) return "D";
        return "F";
    }
}
