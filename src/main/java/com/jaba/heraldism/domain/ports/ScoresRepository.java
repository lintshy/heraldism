package com.jaba.heraldism.domain.ports;

import com.jaba.heraldism.domain.Score;

import java.util.List;
import java.util.Optional;

/**
 * Port interface for Score repository operations.
 */
public interface ScoresRepository {
    
    /**
     * Save a score.
     *
     * @param score Score to save
     * @return Saved score
     */
    Score save(Score score);
    
    /**
     * Find a score by match ID and player ID.
     *
     * @param matchId Match ID
     * @param playerId Player ID
     * @return Optional containing score if found
     */
    Optional<Score> findByMatchIdAndPlayerId(String matchId, String playerId);
    
    /**
     * Find all scores for a match.
     *
     * @param matchId Match ID
     * @return List of scores
     */
    List<Score> findByMatchId(String matchId);
    
    /**
     * Find all scores for a player.
     *
     * @param playerId Player ID
     * @return List of scores
     */
    List<Score> findByPlayerId(String playerId);
    
    /**
     * Calculate average score for a player.
     *
     * @param playerId Player ID
     * @return Average overall score
     */
    Double calculateAverageScore(String playerId);
    
    /**
     * Delete a score.
     *
     * @param matchId Match ID
     * @param playerId Player ID
     */
    void deleteByMatchIdAndPlayerId(String matchId, String playerId);
}
