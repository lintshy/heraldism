package com.jaba.heraldism.service;

import com.jaba.heraldism.api.dto.DiscordWebhookRequest;
import com.jaba.heraldism.common.dto.ApiResponse;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Mono;

import java.util.HashMap;
import java.util.Map;

/**
 * Service for sending messages to Discord webhooks.
 */
@Slf4j
@Service
@RequiredArgsConstructor
public class DiscordWebhookService {

    private final WebClient webClient;
    
    @Value("${discord.webhook.url}")
    private String webhookUrl;
    
    /**
     * Sends a message to a Discord webhook.
     *
     * @param request The webhook request containing message content and optional overrides
     * @return ApiResponse indicating success or failure
     */
    public Mono<ApiResponse<String>> sendMessage(DiscordWebhookRequest request) {
        if (webhookUrl == null || webhookUrl.isBlank()) {
            log.warn("Discord webhook URL is not configured");
            return Mono.just(ApiResponse.error(
                String.valueOf(HttpStatus.BAD_REQUEST.value()),
                "Discord webhook URL is not configured"
            ));
        }
        
        Map<String, Object> body = new HashMap<>();
        body.put("content", request.getContent());
        
        if (request.getUsername() != null && !request.getUsername().isBlank()) {
            body.put("username", request.getUsername());
        }
        
        if (request.getAvatarUrl() != null && !request.getAvatarUrl().isBlank()) {
            body.put("avatar_url", request.getAvatarUrl());
        }
        
        log.debug("Sending message to Discord webhook: {}", body);
        
        return webClient.post()
                .uri(webhookUrl)
                .contentType(MediaType.APPLICATION_JSON)
                .bodyValue(body)
                .retrieve()
                .toBodilessEntity()
                .map(response -> {
                    log.debug("Successfully sent message to Discord webhook");
                    return ApiResponse.success("Message sent to Discord webhook");
                })
                .onErrorResume(e -> {
                    log.error("Failed to send message to Discord webhook", e);
                    return Mono.just(ApiResponse.error(
                        String.valueOf(HttpStatus.INTERNAL_SERVER_ERROR.value()),
                        "Failed to send message to Discord: " + e.getMessage()
                    ));
                });
    }
}
