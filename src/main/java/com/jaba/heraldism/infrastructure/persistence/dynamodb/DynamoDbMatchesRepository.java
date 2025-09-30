package com.jaba.heraldism.infrastructure.persistence.dynamodb;

import com.jaba.heraldism.domain.Match;
import com.jaba.heraldism.domain.ports.MatchesRepository;
import com.jaba.heraldism.infrastructure.persistence.model.MatchEntity;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Repository;
import software.amazon.awssdk.enhanced.dynamodb.DynamoDbEnhancedClient;
import software.amazon.awssdk.enhanced.dynamodb.DynamoDbTable;
import software.amazon.awssdk.enhanced.dynamodb.Key;
import software.amazon.awssdk.enhanced.dynamodb.TableSchema;

import java.time.Instant;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

/**
 * DynamoDB implementation of MatchesRepository.
 */
@Slf4j
@Repository
@RequiredArgsConstructor
public class DynamoDbMatchesRepository implements MatchesRepository {
    
    private final DynamoDbEnhancedClient dynamoDbClient;
    
    // TODO: Inject table name from configuration
    private static final String TABLE_NAME = "dota-service-dev-matches";
    
    @Override
    public Match save(Match match) {
        log.debug("Saving match: {}", match.getMatchId());
        DynamoDbTable<MatchEntity> table = getTable();
        MatchEntity entity = toEntity(match);
        table.putItem(entity);
        return match;
    }
    
    @Override
    public List<Match> saveAll(List<Match> matches) {
        log.debug("Saving {} matches", matches.size());
        matches.forEach(this::save);
        return matches;
    }
    
    @Override
    public Optional<Match> findById(String matchId) {
        log.debug("Finding match by ID: {}", matchId);
        // TODO: Implement with proper sort key
        throw new UnsupportedOperationException("Not implemented yet");
    }
    
    @Override
    public List<Match> findByPlayerId(String playerId) {
        log.debug("Finding matches for player: {}", playerId);
        // TODO: Implement GSI query
        throw new UnsupportedOperationException("Not implemented yet");
    }
    
    @Override
    public List<Match> findRecentByPlayerId(String playerId, int limit) {
        log.debug("Finding {} recent matches for player: {}", limit, playerId);
        // TODO: Implement GSI query with limit
        throw new UnsupportedOperationException("Not implemented yet");
    }
    
    @Override
    public boolean existsById(String matchId) {
        return findById(matchId).isPresent();
    }
    
    @Override
    public void deleteById(String matchId) {
        log.debug("Deleting match: {}", matchId);
        // TODO: Implement with proper sort key
        throw new UnsupportedOperationException("Not implemented yet");
    }
    
    private DynamoDbTable<MatchEntity> getTable() {
        return dynamoDbClient.table(TABLE_NAME, TableSchema.fromBean(MatchEntity.class));
    }
    
    private MatchEntity toEntity(Match match) {
        return MatchEntity.builder()
                .matchId(match.getMatchId())
                .playerId(match.getPlayerId())
                .timestamp(match.getTimestamp())
                .duration(match.getDuration())
                .gameMode(match.getGameMode())
                .radiantWin(match.getRadiantWin())
                .heroId(match.getHeroId())
                .heroName(match.getHeroName())
                .kills(match.getKills())
                .deaths(match.getDeaths())
                .assists(match.getAssists())
                .lastHits(match.getLastHits())
                .denies(match.getDenies())
                .goldPerMin(match.getGoldPerMin())
                .xpPerMin(match.getXpPerMin())
                .heroDamage(match.getHeroDamage())
                .towerDamage(match.getTowerDamage())
                .heroHealing(match.getHeroHealing())
                .items(match.getItems())
                .createdAt(match.getCreatedAt() != null ? match.getCreatedAt().getEpochSecond() : null)
                .build();
    }
    
    private Match toDomain(MatchEntity entity) {
        return Match.builder()
                .matchId(entity.getMatchId())
                .playerId(entity.getPlayerId())
                .timestamp(entity.getTimestamp())
                .duration(entity.getDuration())
                .gameMode(entity.getGameMode())
                .radiantWin(entity.getRadiantWin())
                .heroId(entity.getHeroId())
                .heroName(entity.getHeroName())
                .kills(entity.getKills())
                .deaths(entity.getDeaths())
                .assists(entity.getAssists())
                .lastHits(entity.getLastHits())
                .denies(entity.getDenies())
                .goldPerMin(entity.getGoldPerMin())
                .xpPerMin(entity.getXpPerMin())
                .heroDamage(entity.getHeroDamage())
                .towerDamage(entity.getTowerDamage())
                .heroHealing(entity.getHeroHealing())
                .items(entity.getItems())
                .createdAt(entity.getCreatedAt() != null ? Instant.ofEpochSecond(entity.getCreatedAt()) : null)
                .build();
    }
}
