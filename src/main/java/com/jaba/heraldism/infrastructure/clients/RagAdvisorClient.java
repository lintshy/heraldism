package com.jaba.heraldism.infrastructure.clients;

import com.jaba.heraldism.domain.DraftAdvice;
import com.jaba.heraldism.domain.ports.RAGAdvisor;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.client.WebClient;

import java.util.List;

/**
 * HTTP client implementation for RAG advisor service.
 */
@Slf4j
@Component
@RequiredArgsConstructor
public class RagAdvisorClient implements RAGAdvisor {
    
    private final WebClient.Builder webClientBuilder;
    
    @Value("${app.rag.base-url:http://localhost:8081}")
    private String baseUrl;
    
    @Override
    public DraftAdvice getDraftAdvice(
            List<Integer> currentPicks,
            List<Integer> currentBans,
            List<Integer> playerPreferences,
            List<Integer> enemyTeam) {
        log.info("Getting draft advice from RAG service");
        // TODO: Implement API call to MCP/LangChain service
        // POST /draft/advise
        // Request body: { picks, bans, preferences, enemyTeam }
        throw new UnsupportedOperationException("Not implemented yet");
    }
    
    @Override
    public Object analyzeDraft(List<Integer> teamComposition, List<Integer> enemyComposition) {
        log.info("Analyzing draft with RAG service");
        // TODO: Implement API call
        // POST /draft/analyze
        // Request body: { teamComposition, enemyComposition }
        throw new UnsupportedOperationException("Not implemented yet");
    }
    
    private WebClient getWebClient() {
        return webClientBuilder.baseUrl(baseUrl).build();
    }
}
