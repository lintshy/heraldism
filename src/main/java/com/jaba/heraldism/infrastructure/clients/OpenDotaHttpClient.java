package com.jaba.heraldism.infrastructure.clients;

import com.jaba.heraldism.domain.Match;
import com.jaba.heraldism.domain.ports.OpenDotaClient;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.client.WebClient;

import java.util.List;

/**
 * HTTP client implementation for OpenDota API.
 */
@Slf4j
@Component
@RequiredArgsConstructor
public class OpenDotaHttpClient implements OpenDotaClient {
    
    private final WebClient.Builder webClientBuilder;
    
    @Value("${app.opendota.base-url:https://api.opendota.com/api}")
    private String baseUrl;
    
    @Override
    public List<Match> fetchRecentMatches(String steamId, int limit) {
        log.info("Fetching {} recent matches for Steam ID: {}", limit, steamId);
        // TODO: Implement API call
        // GET /players/{account_id}/recentMatches
        throw new UnsupportedOperationException("Not implemented yet");
    }
    
    @Override
    public Match fetchMatchDetails(String matchId) {
        log.info("Fetching match details for match ID: {}", matchId);
        // TODO: Implement API call
        // GET /matches/{match_id}
        throw new UnsupportedOperationException("Not implemented yet");
    }
    
    @Override
    public Object fetchPlayerProfile(String steamId) {
        log.info("Fetching player profile for Steam ID: {}", steamId);
        // TODO: Implement API call
        // GET /players/{account_id}
        throw new UnsupportedOperationException("Not implemented yet");
    }
    
    @Override
    public boolean isValidSteamId(String steamId) {
        log.debug("Validating Steam ID: {}", steamId);
        // TODO: Implement validation logic
        // Steam ID should be a numeric string
        if (steamId == null || steamId.isEmpty()) {
            return false;
        }
        try {
            Long.parseLong(steamId);
            return true;
        } catch (NumberFormatException e) {
            return false;
        }
    }
    
    private WebClient getWebClient() {
        return webClientBuilder.baseUrl(baseUrl).build();
    }
}
