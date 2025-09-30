package com.jaba.heraldism.domain;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

/**
 * Domain entity representing draft advice from AI/RAG system.
 */
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class DraftAdvice {
    
    private List<HeroRecommendation> recommendations;
    private String reasoning;
    private Double confidenceScore;
    private List<String> counterPicks;
    private List<String> synergies;
    
    /**
     * Hero recommendation with score and reasoning.
     */
    @Data
    @Builder
    @NoArgsConstructor
    @AllArgsConstructor
    public static class HeroRecommendation {
        private Integer heroId;
        private String heroName;
        private Double score;
        private String reasoning;
        private List<String> strengths;
        private List<String> weaknesses;
    }
}
