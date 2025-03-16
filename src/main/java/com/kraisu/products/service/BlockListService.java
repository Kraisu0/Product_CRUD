package com.kraisu.products.service;

import com.kraisu.products.DTO.BlockListDTO;
import com.kraisu.products.exceptions.MessageResponse;
import com.kraisu.products.model.BlockList;

import java.util.List;
import java.util.UUID;

public interface BlockListService {
    List<BlockList> getBlockList();

    BlockList getBlockList(UUID id);

    boolean existsByName(String name);

    BlockList createBlockList(BlockListDTO blockedWordDTO);

    BlockList updateBlockList(UUID id, BlockListDTO blockedWordDTO);

    MessageResponse deleteBlockList(UUID id);
}
