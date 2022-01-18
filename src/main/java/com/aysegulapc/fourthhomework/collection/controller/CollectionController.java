package com.aysegulapc.fourthhomework.collection.controller;

import com.aysegulapc.fourthhomework.collection.dto.CollectionDto;
import com.aysegulapc.fourthhomework.collection.dto.CollectionForSpecificTwoDatesDto;
import com.aysegulapc.fourthhomework.collection.dto.CollectionSaveRequestDto;
import com.aysegulapc.fourthhomework.collection.dto.UserCollectionDetailDto;
import com.aysegulapc.fourthhomework.collection.service.CollectionService;
import lombok.RequiredArgsConstructor;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Date;
import java.util.List;

@RestController
@RequestMapping("/api/v1/collections")
@CrossOrigin
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

    @GetMapping("/{startDate}/{endDate}")
    public ResponseEntity getAllCollectionBySpecificTwoDays(@PathVariable("startDate") @DateTimeFormat(pattern = "yyyy-MM-dd") Date startDate,
                                                            @PathVariable("endDate") @DateTimeFormat(pattern = "yyyy-MM-dd") Date endDate) {
        List<CollectionForSpecificTwoDatesDto> collectionForSpecificTwoDatesDtoList =
                collectionService.getAllCollectionBySpecificTwoDate(startDate, endDate);
        return ResponseEntity.ok(collectionForSpecificTwoDatesDtoList);
    }

    @GetMapping("/{userId}")
    public ResponseEntity getAllCollectionByUserId(@PathVariable Long userId) {
        List<UserCollectionDetailDto> userCollectionDetailDtoList =
                collectionService.getAllCollectionByUserId(userId);
        return ResponseEntity.ok(userCollectionDetailDtoList);
    }

}
