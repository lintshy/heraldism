package com.jaba.heraldism.domain;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.Instant;

/**
 * Domain entity representing a Dota 2 player.
 */
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Player {
    
    private String playerId;
    private String steamId;
    private String nickname;
    private String avatarUrl;
    private Integer mmr;
    private String rank;
    private Instant createdAt;
    private Instant updatedAt;
    
    /**
     * Check if player is ranked.
     *
     * @return true if player has MMR
     */
    public boolean isRanked() {
        return mmr != null && mmr > 0;
    }
}
