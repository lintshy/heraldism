package com.jaba.heraldism.domain.ports;

import com.jaba.heraldism.domain.Match;

import java.util.List;

/**
 * Port interface for OpenDota API client.
 */
public interface OpenDotaClient {
    
    /**
     * Fetch recent matches for a player.
     *
     * @param steamId Steam ID
     * @param limit Maximum number of matches to fetch
     * @return List of matches
     */
    List<Match> fetchRecentMatches(String steamId, int limit);
    
    /**
     * Fetch detailed match data.
     *
     * @param matchId Match ID
     * @return Match details
     */
    Match fetchMatchDetails(String matchId);
    
    /**
     * Fetch player profile data.
     *
     * @param steamId Steam ID
     * @return Player profile data as generic object (to be mapped)
     */
    Object fetchPlayerProfile(String steamId);
    
    /**
     * Check if Steam ID is valid.
     *
     * @param steamId Steam ID
     * @return true if valid
     */
    boolean isValidSteamId(String steamId);
}
