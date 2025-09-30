package com.jaba.heraldism.infrastructure.clients;

import com.jaba.heraldism.domain.ports.Notifier;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.client.WebClient;

/**
 * Discord webhook implementation of Notifier.
 */
@Slf4j
@Component
@RequiredArgsConstructor
public class DiscordWebhookNotifier implements Notifier {
    
    private final WebClient.Builder webClientBuilder;
    
    @Value("${app.discord.webhook-url:}")
    private String webhookUrl;
    
    @Value("${app.discord.enabled:false}")
    private boolean enabled;
    
    @Override
    public void sendNotification(String message) {
        if (!enabled || webhookUrl == null || webhookUrl.isEmpty()) {
            log.debug("Discord notifications disabled or webhook URL not configured");
            return;
        }
        
        log.info("Sending Discord notification: {}", message);
        // TODO: Implement Discord webhook call
        // POST to webhook URL with JSON body: { "content": message }
    }
    
    @Override
    public void sendFormattedNotification(String title, String description, String color) {
        if (!enabled || webhookUrl == null || webhookUrl.isEmpty()) {
            log.debug("Discord notifications disabled or webhook URL not configured");
            return;
        }
        
        log.info("Sending formatted Discord notification: {}", title);
        // TODO: Implement Discord embed webhook call
        // POST to webhook URL with JSON body containing embeds
    }
    
    @Override
    public void sendMatchNotification(String playerName, String matchId, boolean isWin, Double score) {
        String emoji = isWin ? "🎉" : "😢";
        String result = isWin ? "Victory" : "Defeat";
        String title = String.format("%s %s - Match %s", emoji, result, matchId);
        String description = String.format("Player: %s\nPerformance Score: %.2f", playerName, score);
        String color = isWin ? "#00FF00" : "#FF0000";
        
        sendFormattedNotification(title, description, color);
    }
    
    private WebClient getWebClient() {
        return webClientBuilder.build();
    }
}
