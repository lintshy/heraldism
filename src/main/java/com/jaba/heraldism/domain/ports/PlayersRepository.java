package com.jaba.heraldism.domain.ports;

import com.jaba.heraldism.domain.Player;

import java.util.List;
import java.util.Optional;

/**
 * Port interface for Player repository operations.
 */
public interface PlayersRepository {
    
    /**
     * Save a player.
     *
     * @param player Player to save
     * @return Saved player
     */
    Player save(Player player);
    
    /**
     * Find a player by ID.
     *
     * @param playerId Player ID
     * @return Optional containing player if found
     */
    Optional<Player> findById(String playerId);
    
    /**
     * Find a player by Steam ID.
     *
     * @param steamId Steam ID
     * @return Optional containing player if found
     */
    Optional<Player> findBySteamId(String steamId);
    
    /**
     * Find all players.
     *
     * @return List of all players
     */
    List<Player> findAll();
    
    /**
     * Delete a player by ID.
     *
     * @param playerId Player ID
     */
    void deleteById(String playerId);
    
    /**
     * Check if a player exists by ID.
     *
     * @param playerId Player ID
     * @return true if player exists
     */
    boolean existsById(String playerId);
    
    /**
     * Check if a player exists by Steam ID.
     *
     * @param steamId Steam ID
     * @return true if player exists
     */
    boolean existsBySteamId(String steamId);
}
