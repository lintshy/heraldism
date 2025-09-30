package com.jaba.heraldism.domain;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.Instant;
import java.util.List;

/**
 * Domain entity representing a Dota 2 match.
 */
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Match {
    
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
    private Instant createdAt;
    
    /**
     * Check if player won the match.
     *
     * @param isRadiant Whether player was on Radiant team
     * @return true if player won
     */
    public boolean isWin(boolean isRadiant) {
        return radiantWin != null && radiantWin == isRadiant;
    }
    
    /**
     * Calculate KDA ratio.
     *
     * @return KDA ratio
     */
    public double calculateKda() {
        if (deaths == null || deaths == 0) {
            return (kills != null ? kills : 0) + (assists != null ? assists : 0);
        }
        return ((kills != null ? kills : 0) + (assists != null ? assists : 0)) / (double) deaths;
    }
}
