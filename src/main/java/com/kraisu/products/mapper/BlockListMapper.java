package com.kraisu.products.mapper;

import com.kraisu.products.DTO.BlockListDTO;
import com.kraisu.products.model.BlockList;
import org.mapstruct.*;

@Mapper(componentModel = "spring")
public interface BlockListMapper {
    BlockList mapToEntity(BlockListDTO blockListDTO);

    @Mapping(target = "id", ignore = true)
    @BeanMapping(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
    void updateBlockList(@MappingTarget BlockList blockList, BlockListDTO blockListDTO);
}
