package com.jaba.heraldism.domain.ports;

import com.jaba.heraldism.domain.Match;

import java.util.List;
import java.util.Optional;

/**
 * Port interface for Match repository operations.
 */
public interface MatchesRepository {
    
    /**
     * Save a match.
     *
     * @param match Match to save
     * @return Saved match
     */
    Match save(Match match);
    
    /**
     * Save multiple matches.
     *
     * @param matches Matches to save
     * @return List of saved matches
     */
    List<Match> saveAll(List<Match> matches);
    
    /**
     * Find a match by ID.
     *
     * @param matchId Match ID
     * @return Optional containing match if found
     */
    Optional<Match> findById(String matchId);
    
    /**
     * Find all matches for a player.
     *
     * @param playerId Player ID
     * @return List of matches
     */
    List<Match> findByPlayerId(String playerId);
    
    /**
     * Find recent matches for a player.
     *
     * @param playerId Player ID
     * @param limit Maximum number of matches to return
     * @return List of recent matches
     */
    List<Match> findRecentByPlayerId(String playerId, int limit);
    
    /**
     * Check if a match exists by ID.
     *
     * @param matchId Match ID
     * @return true if match exists
     */
    boolean existsById(String matchId);
    
    /**
     * Delete a match by ID.
     *
     * @param matchId Match ID
     */
    void deleteById(String matchId);
}
