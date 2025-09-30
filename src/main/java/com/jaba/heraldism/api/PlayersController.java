package com.jaba.heraldism.api;

import com.jaba.heraldism.common.dto.ApiResponse;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

/**
 * REST controller for player management operations.
 */
@Slf4j
@RestController
@RequestMapping("/players")
@RequiredArgsConstructor
@Tag(name = "Players", description = "Player management endpoints")
public class PlayersController {
    
    // TODO: Inject use cases when implemented
    // private final CreatePlayerUseCase createPlayerUseCase;
    // private final ListPlayersUseCase listPlayersUseCase;
    // private final DeletePlayerUseCase deletePlayerUseCase;
    // private final UpdatePlayerUseCase updatePlayerUseCase;
    
    @Operation(summary = "Create a new player", description = "Register a new player in the system")
    @PostMapping
    public ResponseEntity<ApiResponse<String>> createPlayer() {
        log.info("Creating new player");
        // TODO: Implement when use case is available
        return ResponseEntity.status(HttpStatus.CREATED)
                .body(ApiResponse.success("Player created successfully", "TODO: Implement"));
    }
    
    @Operation(summary = "Get all players", description = "Retrieve a list of all registered players")
    @GetMapping
    public ResponseEntity<ApiResponse<String>> listPlayers() {
        log.info("Listing all players");
        // TODO: Implement when use case is available
        return ResponseEntity.ok(ApiResponse.success("TODO: Implement"));
    }
    
    @Operation(summary = "Get player by ID", description = "Retrieve a specific player by their ID")
    @GetMapping("/{playerId}")
    public ResponseEntity<ApiResponse<String>> getPlayer(@PathVariable String playerId) {
        log.info("Getting player: {}", playerId);
        // TODO: Implement when use case is available
        return ResponseEntity.ok(ApiResponse.success("TODO: Implement"));
    }
    
    @Operation(summary = "Update player", description = "Update an existing player's information")
    @PutMapping("/{playerId}")
    public ResponseEntity<ApiResponse<String>> updatePlayer(@PathVariable String playerId) {
        log.info("Updating player: {}", playerId);
        // TODO: Implement when use case is available
        return ResponseEntity.ok(ApiResponse.success("Player updated successfully", "TODO: Implement"));
    }
    
    @Operation(summary = "Delete player", description = "Remove a player from the system")
    @DeleteMapping("/{playerId}")
    public ResponseEntity<ApiResponse<Void>> deletePlayer(@PathVariable String playerId) {
        log.info("Deleting player: {}", playerId);
        // TODO: Implement when use case is available
        return ResponseEntity.ok(ApiResponse.success(null, "Player deleted successfully"));
    }
}
