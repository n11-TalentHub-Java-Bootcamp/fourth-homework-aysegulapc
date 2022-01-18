package com.aysegulapc.fourthhomework.debt.controller;

import com.aysegulapc.fourthhomework.debt.dto.*;
import com.aysegulapc.fourthhomework.debt.service.DebtService;
import lombok.RequiredArgsConstructor;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Date;
import java.util.List;

@RestController
@RequestMapping("/api/v1/debts")
@CrossOrigin
@RequiredArgsConstructor
public class DebtController {

    private final DebtService debtService;

    @GetMapping("")
    public ResponseEntity getAll() {
        List<DebtDto> debtDtoList = debtService.findAll();
        return ResponseEntity.ok(debtDtoList);
    }

    @GetMapping("/{userId}")
    public ResponseEntity getAllUserDebtDetailList(@PathVariable Long userId) {
        List<UserDebtDetailDto> userDebtDetailDtoList =
                debtService.getAllUserDebtDetailListByUserId(userId);
        return ResponseEntity.ok(userDebtDetailDtoList);
    }

    @GetMapping("/{startDate}/{endDate}")
    public ResponseEntity getAllDebtBySpecificTwoDays(@PathVariable("startDate") @DateTimeFormat(pattern = "yyyy-MM-dd") Date startDate,
                                                      @PathVariable("endDate") @DateTimeFormat(pattern = "yyyy-MM-dd") Date endDate) {
        List<DebtForSpecificTwoDatesDto> debtForSpecificTwoDatesDtoList =
                debtService.getAllDebtBySpecificTwoDate(startDate, endDate);
        return ResponseEntity.ok(debtForSpecificTwoDatesDtoList);
    }

    @GetMapping("/{userId}/overduedate")
    public ResponseEntity getAllUserDebtDetailListByEntryDate(@PathVariable Long userId) {
        List<UserDebtDetailDto> userDebtDetailDtoList =
                debtService.getAllUserDebtDetailByLateExpiryDate(userId);
        return ResponseEntity.ok(userDebtDetailDtoList);
    }

    @GetMapping("/{userId}/sum")
    public ResponseEntity getSumOfDebtByUserId(@PathVariable Long userId) {
        UserSumOfDebtDto userSumOfDebtDto = debtService.getSumOfDebtByUserId(userId);
        return ResponseEntity.ok(userSumOfDebtDto);
    }

    @GetMapping("/{userId}/overduesum")
    public ResponseEntity getSumOfOverdueDebtByUserId(@PathVariable Long userId) {
        UserSumOfDebtDto userSumOfDebtDto = debtService.getSumOfOverdueDebtByUserId(userId);
        return ResponseEntity.ok(userSumOfDebtDto);
    }

    @PostMapping
    public ResponseEntity save(@RequestBody DebtSaveRequestDto debtSaveRequestDto) {
        DebtDto debtDto = debtService.saveDebt(debtSaveRequestDto);
        return ResponseEntity.ok(debtDto);
    }

    @PutMapping
    public DebtDto update(@RequestBody DebtDto debtDto) {
        return debtService.update(debtDto);
    }

    @DeleteMapping("/{id}")
    public void delete(@PathVariable Long id) {
        debtService.delete(id);
    }
}
