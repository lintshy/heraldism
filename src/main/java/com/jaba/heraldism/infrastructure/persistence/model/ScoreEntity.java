package com.jaba.heraldism.infrastructure.persistence.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import software.amazon.awssdk.enhanced.dynamodb.mapper.annotations.DynamoDbBean;
import software.amazon.awssdk.enhanced.dynamodb.mapper.annotations.DynamoDbPartitionKey;
import software.amazon.awssdk.enhanced.dynamodb.mapper.annotations.DynamoDbSortKey;

/**
 * DynamoDB entity for MatchScore table.
 */
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@DynamoDbBean
public class ScoreEntity {
    
    private String matchId;
    private String playerId;
    private Double overallScore;
    private Double combatScore;
    private Double farmingScore;
    private Double supportScore;
    private Double objectiveScore;
    private String performanceGrade;
    private Long calculatedAt;
    
    @DynamoDbPartitionKey
    public String getMatchId() {
        return matchId;
    }
    
    @DynamoDbSortKey
    public String getPlayerId() {
        return playerId;
    }
}
