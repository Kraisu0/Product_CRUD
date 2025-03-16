package com.kraisu.products.service;

import com.kraisu.products.DTO.BlockListDTO;
import com.kraisu.products.exceptions.BlockListNotFoundExceptions;
import com.kraisu.products.exceptions.MessageResponse;
import com.kraisu.products.mapper.BlockListMapper;
import com.kraisu.products.model.BlockList;
import com.kraisu.products.repository.BlockListRepository;
import lombok.RequiredArgsConstructor;
import org.apache.logging.log4j.message.Message;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class BlockListServiceImpl implements BlockListService {
    private final BlockListRepository blockListRepository;
    private final BlockListMapper blockListMapper;

    private void validateBlockListDTO(BlockListDTO blockListDTO) {
        if (blockListDTO.getName() == null || !blockListDTO.getName().matches("^[a-zA-Z0-9_]{2,50}$")) {
            throw  new IllegalArgumentException("Invalid block list name. Product name should be between 2 and 50 characters and contain only letters and numbers");
        }
        if (blockListRepository.existsByName(blockListDTO.getName())) {
            throw  new IllegalArgumentException("Invalid block list name. Product " + blockListDTO.getName() + " already exists");
        }
    }

    @Override
    public List<BlockList> getBlockList() {
        return blockListRepository.findAll();
    }

    @Override
    public BlockList getBlockList(UUID id) {
        return blockListRepository.findById(id).orElseThrow(() -> new BlockListNotFoundExceptions(id));
    }

    @Override
    public boolean existsByName(String name) {
        return blockListRepository.existsByName(name);
    }

    @Override
    public BlockList createBlockList(BlockListDTO blockListDTO) {
        validateBlockListDTO(blockListDTO);
        return blockListRepository.save(blockListMapper.mapToEntity(blockListDTO));
    }

    @Override
    public BlockList updateBlockList(UUID id, BlockListDTO blockListDTO) {
        BlockList blockList = getBlockList(id);
        validateBlockListDTO(blockListDTO);
        blockListMapper.updateBlockList(blockList, blockListDTO);
        return blockListRepository.save(blockList);
    }

    @Override
    public MessageResponse deleteBlockList(UUID id) {
        blockListRepository.deleteById(id);
        return new MessageResponse("Blocked word form BlockList witch id: " + id + " has been deleted");
    }
}
