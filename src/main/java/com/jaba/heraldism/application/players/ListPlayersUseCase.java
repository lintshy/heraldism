package com.jaba.heraldism.application.players;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

/**
 * Use case for listing all players.
 */
@Slf4j
@Service
@RequiredArgsConstructor
public class ListPlayersUseCase {
    
    // TODO: Inject repositories when implemented
    // private final PlayersRepository playersRepository;
    
    /**
     * Execute the list players use case.
     *
     * @return List of players
     */
    public Object execute() {
        log.info("Executing ListPlayersUseCase");
        // TODO: Implement business logic
        // 1. Fetch all players from repository
        // 2. Map to DTOs
        // 3. Return list
        throw new UnsupportedOperationException("Not implemented yet");
    }
}
