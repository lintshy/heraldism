package com.jaba.heraldism.api;

import com.jaba.heraldism.common.dto.ApiResponse;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

/**
 * REST controller for draft advice operations.
 */
@Slf4j
@RestController
@RequestMapping("/draft")
@RequiredArgsConstructor
@Tag(name = "Draft", description = "Draft advice endpoints")
public class DraftController {
    
    // TODO: Inject use case when implemented
    // private final AdviseDraftUseCase adviseDraftUseCase;
    
    @Operation(
        summary = "Get draft advice",
        description = "Get AI-powered draft recommendations based on current picks and bans"
    )
    @PostMapping("/advise")
    public ResponseEntity<ApiResponse<String>> getDraftAdvice() {
        log.info("Getting draft advice");
        // TODO: Implement when use case is available
        return ResponseEntity.ok(ApiResponse.success("TODO: Implement"));
    }
    
    @Operation(
        summary = "Analyze draft",
        description = "Analyze a completed draft and provide insights"
    )
    @PostMapping("/analyze")
    public ResponseEntity<ApiResponse<String>> analyzeDraft() {
        log.info("Analyzing draft");
        // TODO: Implement when use case is available
        return ResponseEntity.ok(ApiResponse.success("TODO: Implement"));
    }
}
