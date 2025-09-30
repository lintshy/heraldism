package com.jaba.heraldism.api.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * Request DTO for Discord webhook notifications.
 */
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class DiscordWebhookRequest {
    
    @NotBlank(message = "Content is required")
    @Schema(description = "Markdown formatted content to send to Discord", example = "**Hello World!** This is a test message.\n- Item 1\n- Item 2")
    private String content;
    
    @Schema(description = "Username to override the webhook's default name", example = "Dota Bot")
    private String username;
    
    @Schema(description = "URL of an avatar to override the webhook's default avatar")
    private String avatarUrl;
}
