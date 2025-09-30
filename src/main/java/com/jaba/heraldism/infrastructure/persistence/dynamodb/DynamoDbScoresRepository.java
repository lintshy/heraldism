package com.jaba.heraldism.infrastructure.persistence.dynamodb;

import com.jaba.heraldism.domain.Score;
import com.jaba.heraldism.domain.ports.ScoresRepository;
import com.jaba.heraldism.infrastructure.persistence.model.ScoreEntity;
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

/**
 * DynamoDB implementation of ScoresRepository.
 */
@Slf4j
@Repository
@RequiredArgsConstructor
public class DynamoDbScoresRepository implements ScoresRepository {
    
    private final DynamoDbEnhancedClient dynamoDbClient;
    
    // TODO: Inject table name from configuration
    private static final String TABLE_NAME = "dota-service-dev-match-scores";
    
    @Override
    public Score save(Score score) {
        log.debug("Saving score for match: {}, player: {}", score.getMatchId(), score.getPlayerId());
        DynamoDbTable<ScoreEntity> table = getTable();
        ScoreEntity entity = toEntity(score);
        table.putItem(entity);
        return score;
    }
    
    @Override
    public Optional<Score> findByMatchIdAndPlayerId(String matchId, String playerId) {
        log.debug("Finding score for match: {}, player: {}", matchId, playerId);
        DynamoDbTable<ScoreEntity> table = getTable();
        Key key = Key.builder()
                .partitionValue(matchId)
                .sortValue(playerId)
                .build();
        ScoreEntity entity = table.getItem(key);
        return Optional.ofNullable(entity).map(this::toDomain);
    }
    
    @Override
    public List<Score> findByMatchId(String matchId) {
        log.debug("Finding scores for match: {}", matchId);
        // TODO: Implement query
        throw new UnsupportedOperationException("Not implemented yet");
    }
    
    @Override
    public List<Score> findByPlayerId(String playerId) {
        log.debug("Finding scores for player: {}", playerId);
        // TODO: Implement scan with filter or GSI
        throw new UnsupportedOperationException("Not implemented yet");
    }
    
    @Override
    public Double calculateAverageScore(String playerId) {
        log.debug("Calculating average score for player: {}", playerId);
        // TODO: Implement
        throw new UnsupportedOperationException("Not implemented yet");
    }
    
    @Override
    public void deleteByMatchIdAndPlayerId(String matchId, String playerId) {
        log.debug("Deleting score for match: {}, player: {}", matchId, playerId);
        DynamoDbTable<ScoreEntity> table = getTable();
        Key key = Key.builder()
                .partitionValue(matchId)
                .sortValue(playerId)
                .build();
        table.deleteItem(key);
    }
    
    private DynamoDbTable<ScoreEntity> getTable() {
        return dynamoDbClient.table(TABLE_NAME, TableSchema.fromBean(ScoreEntity.class));
    }
    
    private ScoreEntity toEntity(Score score) {
        return ScoreEntity.builder()
                .matchId(score.getMatchId())
                .playerId(score.getPlayerId())
                .overallScore(score.getOverallScore())
                .combatScore(score.getCombatScore())
                .farmingScore(score.getFarmingScore())
                .supportScore(score.getSupportScore())
                .objectiveScore(score.getObjectiveScore())
                .performanceGrade(score.getPerformanceGrade())
                .calculatedAt(score.getCalculatedAt() != null ? score.getCalculatedAt().getEpochSecond() : null)
                .build();
    }
    
    private Score toDomain(ScoreEntity entity) {
        return Score.builder()
                .matchId(entity.getMatchId())
                .playerId(entity.getPlayerId())
                .overallScore(entity.getOverallScore())
                .combatScore(entity.getCombatScore())
                .farmingScore(entity.getFarmingScore())
                .supportScore(entity.getSupportScore())
                .objectiveScore(entity.getObjectiveScore())
                .performanceGrade(entity.getPerformanceGrade())
                .calculatedAt(entity.getCalculatedAt() != null ? Instant.ofEpochSecond(entity.getCalculatedAt()) : null)
                .build();
    }
}
