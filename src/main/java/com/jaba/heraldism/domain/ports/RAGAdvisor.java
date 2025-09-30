package com.jaba.heraldism.domain.ports;

import com.jaba.heraldism.domain.DraftAdvice;

import java.util.List;

/**
 * Port interface for RAG-based draft advisor.
 */
public interface RAGAdvisor {
    
    /**
     * Get draft advice based on current game state.
     *
     * @param currentPicks List of already picked hero IDs
     * @param currentBans List of banned hero IDs
     * @param playerPreferences Player's preferred heroes/roles
     * @param enemyTeam Enemy team composition
     * @return Draft advice with recommendations
     */
    DraftAdvice getDraftAdvice(
        List<Integer> currentPicks,
        List<Integer> currentBans,
        List<Integer> playerPreferences,
        List<Integer> enemyTeam
    );
    
    /**
     * Analyze a completed draft.
     *
     * @param teamComposition Team composition to analyze
     * @param enemyComposition Enemy team composition
     * @return Analysis with strengths and weaknesses
     */
    Object analyzeDraft(List<Integer> teamComposition, List<Integer> enemyComposition);
}
