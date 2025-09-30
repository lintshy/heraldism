package com.jaba.heraldism.infrastructure.persistence.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import software.amazon.awssdk.enhanced.dynamodb.mapper.annotations.DynamoDbBean;
import software.amazon.awssdk.enhanced.dynamodb.mapper.annotations.DynamoDbPartitionKey;
import software.amazon.awssdk.enhanced.dynamodb.mapper.annotations.DynamoDbSecondaryPartitionKey;
import software.amazon.awssdk.enhanced.dynamodb.mapper.annotations.DynamoDbSecondarySortKey;
import software.amazon.awssdk.enhanced.dynamodb.mapper.annotations.DynamoDbSortKey;

import java.util.List;

/**
 * DynamoDB entity for Match table.
 */
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@DynamoDbBean
public class MatchEntity {
    
    private String matchId;
    private String playerId;
    private Long timestamp;
    private Integer duration;
    private String gameMode;
    private Boolean radiantWin;
    private Integer heroId;
    private String heroName;
    private Integer kills;
    private Integer deaths;
    private Integer assists;
    private Integer lastHits;
    private Integer denies;
    private Integer goldPerMin;
    private Integer xpPerMin;
    private Integer heroDamage;
    private Integer towerDamage;
    private Integer heroHealing;
    private List<Integer> items;
    private Long createdAt;
    
    @DynamoDbPartitionKey
    public String getMatchId() {
        return matchId;
    }
    
    @DynamoDbSortKey
    public Long getTimestamp() {
        return timestamp;
    }
    
    @DynamoDbSecondaryPartitionKey(indexNames = "PlayerIdIndex")
    public String getPlayerId() {
        return playerId;
    }
    
    @DynamoDbSecondarySortKey(indexNames = "PlayerIdIndex")
    public Long getPlayerIdIndexTimestamp() {
        return timestamp;
    }
}
