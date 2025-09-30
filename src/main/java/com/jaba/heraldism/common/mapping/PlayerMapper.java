package com.jaba.heraldism.common.mapping;

import org.mapstruct.Mapper;
import org.mapstruct.MappingConstants;

/**
 * MapStruct mapper for Player entities and DTOs.
 * 
 * This interface will be implemented by MapStruct at compile time.
 */
@Mapper(componentModel = MappingConstants.ComponentModel.SPRING)
public interface PlayerMapper {
    
    // TODO: Add mapping methods when Player entity and DTOs are created
    // Example:
    // PlayerDto toDto(Player player);
    // Player toEntity(CreatePlayerRequest request);
}
