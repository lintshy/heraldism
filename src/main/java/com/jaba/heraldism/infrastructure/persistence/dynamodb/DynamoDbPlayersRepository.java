package com.jaba.heraldism.infrastructure.persistence.dynamodb;

import com.jaba.heraldism.domain.Player;
import com.jaba.heraldism.domain.ports.PlayersRepository;
import com.jaba.heraldism.infrastructure.persistence.model.PlayerEntity;
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
 * DynamoDB implementation of PlayersRepository.
 */
@Slf4j
@Repository
@RequiredArgsConstructor
public class DynamoDbPlayersRepository implements PlayersRepository {
    
    private final DynamoDbEnhancedClient dynamoDbClient;
    
    // TODO: Inject table name from configuration
    private static final String TABLE_NAME = "dota-service-dev-players";
    
    @Override
    public Player save(Player player) {
        log.debug("Saving player: {}", player.getPlayerId());
        DynamoDbTable<PlayerEntity> table = getTable();
        PlayerEntity entity = toEntity(player);
        table.putItem(entity);
        return player;
    }
    
    @Override
    public Optional<Player> findById(String playerId) {
        log.debug("Finding player by ID: {}", playerId);
        DynamoDbTable<PlayerEntity> table = getTable();
        Key key = Key.builder().partitionValue(playerId).build();
        PlayerEntity entity = table.getItem(key);
        return Optional.ofNullable(entity).map(this::toDomain);
    }
    
    @Override
    public Optional<Player> findBySteamId(String steamId) {
        log.debug("Finding player by Steam ID: {}", steamId);
        // TODO: Implement GSI query
        throw new UnsupportedOperationException("Not implemented yet");
    }
    
    @Override
    public List<Player> findAll() {
        log.debug("Finding all players");
        DynamoDbTable<PlayerEntity> table = getTable();
        return table.scan().items().stream()
                .map(this::toDomain)
                .collect(Collectors.toList());
    }
    
    @Override
    public void deleteById(String playerId) {
        log.debug("Deleting player: {}", playerId);
        DynamoDbTable<PlayerEntity> table = getTable();
        Key key = Key.builder().partitionValue(playerId).build();
        table.deleteItem(key);
    }
    
    @Override
    public boolean existsById(String playerId) {
        return findById(playerId).isPresent();
    }
    
    @Override
    public boolean existsBySteamId(String steamId) {
        return findBySteamId(steamId).isPresent();
    }
    
    private DynamoDbTable<PlayerEntity> getTable() {
        return dynamoDbClient.table(TABLE_NAME, TableSchema.fromBean(PlayerEntity.class));
    }
    
    private PlayerEntity toEntity(Player player) {
        return PlayerEntity.builder()
                .playerId(player.getPlayerId())
                .steamId(player.getSteamId())
                .nickname(player.getNickname())
                .avatarUrl(player.getAvatarUrl())
                .mmr(player.getMmr())
                .rank(player.getRank())
                .createdAt(player.getCreatedAt() != null ? player.getCreatedAt().getEpochSecond() : null)
                .updatedAt(player.getUpdatedAt() != null ? player.getUpdatedAt().getEpochSecond() : null)
                .build();
    }
    
    private Player toDomain(PlayerEntity entity) {
        return Player.builder()
                .playerId(entity.getPlayerId())
                .steamId(entity.getSteamId())
                .nickname(entity.getNickname())
                .avatarUrl(entity.getAvatarUrl())
                .mmr(entity.getMmr())
                .rank(entity.getRank())
                .createdAt(entity.getCreatedAt() != null ? Instant.ofEpochSecond(entity.getCreatedAt()) : null)
                .updatedAt(entity.getUpdatedAt() != null ? Instant.ofEpochSecond(entity.getUpdatedAt()) : null)
                .build();
    }
}
