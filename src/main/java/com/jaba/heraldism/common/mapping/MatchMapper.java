package com.jaba.heraldism.common.mapping;

import org.mapstruct.Mapper;
import org.mapstruct.MappingConstants;

/**
 * MapStruct mapper for Match entities and DTOs.
 * 
 * This interface will be implemented by MapStruct at compile time.
 */
@Mapper(componentModel = MappingConstants.ComponentModel.SPRING)
public interface MatchMapper {
    
    // TODO: Add mapping methods when Match entity and DTOs are created
    // Example:
    // MatchDto toDto(Match match);
    // Match toEntity(MatchData matchData);
}
