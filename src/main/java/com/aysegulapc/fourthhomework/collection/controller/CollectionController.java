package com.aysegulapc.fourthhomework.collection.controller;

import com.aysegulapc.fourthhomework.collection.dto.CollectionDto;
import com.aysegulapc.fourthhomework.collection.dto.CollectionSaveRequestDto;
import com.aysegulapc.fourthhomework.collection.service.CollectionService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("/api/v1/collections")
@RequiredArgsConstructor
public class CollectionController {

    private final CollectionService collectionService;

    @GetMapping("")
    public ResponseEntity getAll() {
        List<CollectionDto> collectionDtoList = collectionService.findAll();
        return ResponseEntity.ok(collectionDtoList);
    }

    @PostMapping
    public ResponseEntity create(@RequestBody CollectionSaveRequestDto collectionSaveRequestDto) {
        collectionService.createDebtByDate(collectionSaveRequestDto);
        CollectionDto collectionDto = collectionService.save(collectionSaveRequestDto);
        return ResponseEntity.ok(collectionDto);
    }

}
