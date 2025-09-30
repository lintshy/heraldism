package com.jaba.heraldism.infrastructure.persistence.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import software.amazon.awssdk.enhanced.dynamodb.mapper.annotations.DynamoDbBean;
import software.amazon.awssdk.enhanced.dynamodb.mapper.annotations.DynamoDbPartitionKey;
import software.amazon.awssdk.enhanced.dynamodb.mapper.annotations.DynamoDbSecondaryPartitionKey;

/**
 * DynamoDB entity for Player table.
 */
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@DynamoDbBean
public class PlayerEntity {
    
    private String playerId;
    private String steamId;
    private String nickname;
    private String avatarUrl;
    private Integer mmr;
    private String rank;
    private Long createdAt;
    private Long updatedAt;
    
    @DynamoDbPartitionKey
    public String getPlayerId() {
        return playerId;
    }
    
    @DynamoDbSecondaryPartitionKey(indexNames = "SteamIdIndex")
    public String getSteamId() {
        return steamId;
    }
}
