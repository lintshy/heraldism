package com.jaba.heraldism.api;

import com.jaba.heraldism.api.dto.DiscordWebhookRequest;
import com.jaba.heraldism.common.dto.ApiResponse;
import com.jaba.heraldism.service.DiscordWebhookService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.ExampleObject;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Mono;

/**
 * REST controller for Discord webhook operations.
 */
@Slf4j
@RestController
@RequestMapping("/notify")
@RequiredArgsConstructor
@Tag(name = "Discord Webhook", description = "Endpoints for sending notifications to Discord")
public class DiscordWebhookController {

    private final DiscordWebhookService discordWebhookService;

    /**
     * Sends a message to a configured Discord webhook.
     *
     * @param request The webhook request containing message content and optional overrides
     * @return A response indicating success or failure
     */
    @Operation(
        summary = "Send a message to Discord",
        description = "Sends a markdown-formatted message to a pre-configured Discord webhook.",
        requestBody = @io.swagger.v3.oas.annotations.parameters.RequestBody(
            content = @Content(
                mediaType = MediaType.APPLICATION_JSON_VALUE,
                schema = @Schema(implementation = DiscordWebhookRequest.class),
                examples = @ExampleObject(
                    name = "Basic message",
                    summary = "Basic message example",
                    value = """
                    {
                        "content": "**Hello World!** This is a test message.\\n- Item 1\\n- Item 2",
                        "username": "Dota Bot",
                        "avatarUrl": "https://i.imgur.com/example.png"
                    }
                    """
                )
            )
        )
       
    )
    @PostMapping(value = "/discord", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public Mono<ResponseEntity<ApiResponse<String>>> sendToDiscord(
            @Valid @RequestBody DiscordWebhookRequest request) {
        log.info("Received request to send message to Discord");
        return discordWebhookService.sendMessage(request)
                .map(apiResponse -> {
                    // Determine status code based on success flag
                    int statusCode = apiResponse.isSuccess() ? 200 : 400;
                    return ResponseEntity.status(statusCode).body(apiResponse);
                });
    }
}
