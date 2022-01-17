package com.aysegulapc.fourthhomework.debt.service;

import com.aysegulapc.fourthhomework.debt.converter.DebtMapper;
import com.aysegulapc.fourthhomework.debt.dto.*;
import com.aysegulapc.fourthhomework.debt.entity.Debt;
import com.aysegulapc.fourthhomework.debt.service.entityService.DebtEntityService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;
import java.util.Objects;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class DebtService {
    private final DebtEntityService debtEntityService;

    public List<DebtDto> findAll() {
        List<Debt> debtList = debtEntityService.findAll();
        List<DebtDto> debtDtoList = DebtMapper.INSTANCE.convertAllDebtToDebtDto(debtList);
        return debtDtoList;
    }

    public DebtDto saveDebt(DebtSaveRequestDto debtSaveRequestDto) {
        Debt debt = DebtMapper.INSTANCE.convertDebtSaveRequestDtoToDebt(debtSaveRequestDto);
        if(!Objects.equals(debtSaveRequestDto.getDebtType(), "GECIKME_ZAMMI")) {
            debt = debtEntityService.save(debt);
        } else {
            throw new RuntimeException("Cannot save debt type of GECIKME_ZAMMI!");
        }
        DebtDto debtDto = DebtMapper.INSTANCE.convertDebtToDebtDto(debt);
        return debtDto;
    }

    public DebtDto update(DebtDto debtDto) {
        Debt debt = DebtMapper.INSTANCE.convertDebtDtoToDebt(debtDto);
        if(Objects.equals(debt.getMainDebt(), debtDto.getMainDebt())) {
            debt = debtEntityService.save(debt);
            debtDto = DebtMapper.INSTANCE.convertDebtToDebtDto(debt);
            return debtDto;
        } else {
            throw new RuntimeException("Cannot update main debt amount!");
        }
    }

    public void delete(Long id) {
        Debt debt = findDebtById(id);
        debtEntityService.delete(debt);
    }

    private Debt findDebtById(Long id) {
        Optional<Debt> optionalDebt = debtEntityService.findById(id);

        Debt debt;
        if(optionalDebt.isPresent()) {
            debt = optionalDebt.get();
        } else {
            throw new RuntimeException("Debt not found!");
        }
        return debt;
    }

    public List<DebtForSpecificTwoDatesDto> getAllDebtBySpecificTwoDate(Date startDate, Date endDate) {
        List<DebtForSpecificTwoDatesDto> debtForSpecificTwoDatesDtoList =
                debtEntityService.findDebtBySpecificTwoDates(startDate, endDate);
        return debtForSpecificTwoDatesDtoList;
    }

    public List<UserDebtDetailDto> getAllUserDebtDetailListByUserId(Long userId) {
        List<UserDebtDetailDto> userDebtDetailDtoList = debtEntityService
                .findAllUserDeptDetailDtoByUserId(userId);
        return userDebtDetailDtoList;
    }

    public List<UserDebtDetailDto> getAllUserDebtDetailByLateExpiryDate(Long userId) {
        List<UserDebtDetailDto> userDebtDetailDtoList = debtEntityService.findAllOverdueDebtsByUserId(userId);
        return userDebtDetailDtoList;
    }

    public UserSumOfDebtDto getSumOfDebtByUserId(Long userId) {
        UserSumOfDebtDto userSumOfDebtDto = debtEntityService.findSumOfDebtByUserId(userId);
        return userSumOfDebtDto;
    }

    public UserSumOfDebtDto getSumOfOverdueDebtByUserId(Long userId) {
        UserSumOfDebtDto userSumOfDebtDto = debtEntityService.findSumOfOverdueDebtByUserId(userId);
        return userSumOfDebtDto;
    }

}
