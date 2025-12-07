package org.example.stockservice.mappers;

import org.example.stockservice.dtos.StockRequestDTO;
import org.example.stockservice.dtos.StockResponseDTO;
import org.example.stockservice.entities.Stock;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;
import org.mapstruct.NullValuePropertyMappingStrategy;
import org.mapstruct.factory.Mappers;

@Mapper(componentModel = "spring",
        nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
public interface StockMapper {
    StockMapper INSTANCE = Mappers.getMapper(StockMapper.class);

    @Mapping(target = "id", ignore = true)
    @Mapping(target = "timestamp", ignore = true)
    @Mapping(target = "createdAt", ignore = true)
    Stock toEntity(StockRequestDTO dto);

    StockResponseDTO toResponseDTO(Stock stock);

    @Mapping(target = "id", ignore = true)
    @Mapping(target = "companyId", ignore = true)
    @Mapping(target = "timestamp", ignore = true)
    @Mapping(target = "createdAt", ignore = true)
    void updateEntityFromDTO(StockRequestDTO dto, @MappingTarget Stock entity);
}
