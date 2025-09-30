package com.jaba.heraldism.application.players;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

/**
 * Use case for deleting a player.
 */
@Slf4j
@Service
@RequiredArgsConstructor
public class DeletePlayerUseCase {
    
    // TODO: Inject repositories when implemented
    // private final PlayersRepository playersRepository;
    
    /**
     * Execute the delete player use case.
     *
     * @param playerId Player ID to delete
     */
    public void execute(String playerId) {
        log.info("Executing DeletePlayerUseCase for player: {}", playerId);
        // TODO: Implement business logic
        // 1. Validate player ID
        // 2. Check if player exists
        // 3. Delete player from repository
        // 4. Optionally delete related data (matches, scores)
        throw new UnsupportedOperationException("Not implemented yet");
    }
}
