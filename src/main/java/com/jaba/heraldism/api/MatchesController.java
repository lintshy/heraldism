package com.jaba.heraldism.api;

import com.jaba.heraldism.common.dto.ApiResponse;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

/**
 * REST controller for match management operations.
 */
@Slf4j
@RestController
@RequestMapping("/matches")
@RequiredArgsConstructor
@Tag(name = "Matches", description = "Match management endpoints")
public class MatchesController {
    
    // TODO: Inject use cases when implemented
    // private final RefreshMatchesUseCase refreshMatchesUseCase;
    // private final ComputeScoreUseCase computeScoreUseCase;
    // private final GetMatchReportUseCase getMatchReportUseCase;
    
    @Operation(summary = "Refresh matches", description = "Fetch and update match data from OpenDota API")
    @PostMapping("/refresh")
    public ResponseEntity<ApiResponse<String>> refreshMatches(@RequestParam String playerId) {
        log.info("Refreshing matches for player: {}", playerId);
        // TODO: Implement when use case is available
        return ResponseEntity.ok(ApiResponse.success("Matches refreshed successfully", "TODO: Implement"));
    }
    
    @Operation(summary = "Get match by ID", description = "Retrieve detailed information about a specific match")
    @GetMapping("/{matchId}")
    public ResponseEntity<ApiResponse<String>> getMatch(@PathVariable String matchId) {
        log.info("Getting match: {}", matchId);
        // TODO: Implement when use case is available
        return ResponseEntity.ok(ApiResponse.success("TODO: Implement"));
    }
    
    @Operation(summary = "Get player matches", description = "Retrieve all matches for a specific player")
    @GetMapping("/player/{playerId}")
    public ResponseEntity<ApiResponse<String>> getPlayerMatches(@PathVariable String playerId) {
        log.info("Getting matches for player: {}", playerId);
        // TODO: Implement when use case is available
        return ResponseEntity.ok(ApiResponse.success("TODO: Implement"));
    }
    
    @Operation(summary = "Compute match score", description = "Calculate and store performance scores for a match")
    @PostMapping("/{matchId}/score")
    public ResponseEntity<ApiResponse<String>> computeScore(@PathVariable String matchId) {
        log.info("Computing score for match: {}", matchId);
        // TODO: Implement when use case is available
        return ResponseEntity.ok(ApiResponse.success("Score computed successfully", "TODO: Implement"));
    }
    
    @Operation(summary = "Get match report", description = "Generate a comprehensive report for a match")
    @GetMapping("/{matchId}/report")
    public ResponseEntity<ApiResponse<String>> getMatchReport(@PathVariable String matchId) {
        log.info("Getting match report: {}", matchId);
        // TODO: Implement when use case is available
        return ResponseEntity.ok(ApiResponse.success("TODO: Implement"));
    }
}
