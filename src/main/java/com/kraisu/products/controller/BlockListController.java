package com.kraisu.products.controller;

import com.kraisu.products.DTO.BlockListDTO;
import com.kraisu.products.exceptions.MessageResponse;
import com.kraisu.products.model.BlockList;
import com.kraisu.products.service.BlockListService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/api/v1/block-list")
@RequiredArgsConstructor
public class BlockListController {
    private final BlockListService blockListService;

    @GetMapping
    public ResponseEntity<List<BlockList>> getBlockList() {
        return ResponseEntity.ok(blockListService.getBlockList());
    }

    @GetMapping("/{id}")
    public ResponseEntity<BlockList> getBlockListById(@PathVariable UUID id) {
        return ResponseEntity.ok(blockListService.getBlockList(id));
    }

    @PostMapping
    public ResponseEntity<BlockList> createBlockList(@RequestBody BlockListDTO blockListDTO) {
        return ResponseEntity.ok(blockListService.createBlockList(blockListDTO));
    }

    @PatchMapping("/{id}")
    public ResponseEntity<BlockList> updateBlockList(@PathVariable UUID id, @RequestBody BlockListDTO blockListDTO) {
        return ResponseEntity.ok(blockListService.updateBlockList(id, blockListDTO));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<MessageResponse> deleteBlockList(@PathVariable UUID id) {
        return ResponseEntity.ok(blockListService.deleteBlockList(id));
    }
}
