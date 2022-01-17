package com.aysegulapc.fourthhomework.collection.service;

import com.aysegulapc.fourthhomework.collection.converter.CollectionMapper;
import com.aysegulapc.fourthhomework.collection.dto.CollectionDto;
import com.aysegulapc.fourthhomework.collection.dto.CollectionSaveRequestDto;
import com.aysegulapc.fourthhomework.collection.entity.Collection;
import com.aysegulapc.fourthhomework.collection.service.entityService.CollectionEntityService;
import com.aysegulapc.fourthhomework.debt.converter.DebtMapper;
import com.aysegulapc.fourthhomework.debt.dto.DebtDto;
import com.aysegulapc.fourthhomework.debt.dto.DebtSaveRequestDto;
import com.aysegulapc.fourthhomework.debt.entity.Debt;
import com.aysegulapc.fourthhomework.debt.service.DebtService;
import com.aysegulapc.fourthhomework.debt.service.entityService.DebtEntityService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;
import java.util.Objects;
import java.util.concurrent.TimeUnit;

@Service
@RequiredArgsConstructor
public class CollectionService {
    private final CollectionEntityService collectionEntityService;
    private final DebtService debtService;
    private final DebtEntityService debtEntityService;

    public List<CollectionDto> findAll() {
        List<Collection> collectionList = collectionEntityService.findAll();
        List<CollectionDto> collectionDtoList = CollectionMapper.INSTANCE.convertAllCollectionToCollectionDto(collectionList);
        return collectionDtoList;
    }

    public CollectionDto save(CollectionSaveRequestDto collectionSaveRequestDto) {
        Collection collection = CollectionMapper.INSTANCE.convertCollectionSaveRequestDtoToCollection(collectionSaveRequestDto);
        collection = collectionEntityService.save(collection);
        CollectionDto collectionDto = CollectionMapper.INSTANCE.convertCollectionToCollectionDto(collection);
        return collectionDto;
    }

    public void createDebtByDate(CollectionSaveRequestDto collectionSaveRequestDto) {
        DebtSaveRequestDto debtSaveRequestDto = new DebtSaveRequestDto();
        DebtDto debtDto = new DebtDto();

        long diff = (new Date(System.currentTimeMillis())).getTime() - collectionSaveRequestDto.getDebt().getExpiryDate().getTime();
        long days = TimeUnit.DAYS.convert(diff, TimeUnit.MILLISECONDS);
        List<DebtDto> debts = debtService.findAll();
        if(diff > 0) {
            debts.forEach(debt -> {
                double rateRaise = calculateRateRaise(days, debt);
                if (Objects.equals(debt.getId(), collectionSaveRequestDto.getDebt().getId())) {
                    if(Objects.equals(debt.getMainDebt(), collectionSaveRequestDto.getAmount())) {
                        debtSaveRequestDto.setRemainingDebt(0L);
                        debtSaveRequestDto.setUserId(collectionSaveRequestDto.getUserId());
                        debtSaveRequestDto.setTopDebtId(collectionSaveRequestDto.getDebt().getId());
                        debtSaveRequestDto.setExpiryDate(collectionSaveRequestDto.getCreatedDate());
                        debtSaveRequestDto.setMainDebt(collectionSaveRequestDto.getAmount() + rateRaise);
                        debtSaveRequestDto.setDebtType("GECIKME_ZAMMI");
                    } else {
                        throw new RuntimeException("Cannot become pieced collection!");
                    }
                }
            });
            saveDebtForFee(debtSaveRequestDto);
        } else {
            debts.forEach(debt -> {
                if (Objects.equals(debt.getId(), collectionSaveRequestDto.getDebt().getId())) {
                    if (Objects.equals(debt.getMainDebt(), collectionSaveRequestDto.getAmount())) {
                        debtDto.setRemainingDebt(0L);
                        debtDto.setUserId(collectionSaveRequestDto.getUserId());
                        debtDto.setTopDebtId(collectionSaveRequestDto.getDebt().getId());
                        debtDto.setExpiryDate(collectionSaveRequestDto.getCreatedDate());
                        debtDto.setDebtType("NORMAL");
                    }
                }
            });
            debtService.update(debtDto);
        }
    }

    private DebtDto saveDebtForFee(DebtSaveRequestDto debtSaveRequestDto) {
        Debt debt = DebtMapper.INSTANCE.convertDebtSaveRequestDtoToDebt(debtSaveRequestDto);
        debt = debtEntityService.save(debt);
        DebtDto debtDto = DebtMapper.INSTANCE.convertDebtToDebtDto(debt);
        return debtDto;
    }

    public double calculateRateRaise(long days, DebtDto debt) {
        double rateRaise = 0.0;
        if(debt.getExpiryDate().getTime() <= 1514780530) {
            rateRaise = 1.5 * days;
        } else {
            rateRaise = 2.0 * days;
        }
        return rateRaise;
    }

//    public void createDebtByCollection(CollectionSaveRequestDto collectionSaveRequestDto) {
//        List<DebtDto> debts = debtService.findAll();
//        debts.forEach(debt -> {
//            if(Objects.equals(debt.getId(), collectionSaveRequestDto.getDebt().getId()) &&
//                    Objects.equals(debt.getMainDebt(), collectionSaveRequestDto.getAmount())) {
//                if(((new Date(System.currentTimeMillis())).getTime() - collectionSaveRequestDto.getDebt().getExpiryDate().getTime() > 0)) {
//                    DebtSaveRequestDto debtSaveRequestDto = DebtSaveRequestDto.builder()
//                            .remainingDebt(0L)
//                            .userId(collectionSaveRequestDto.getUserId())
//                            .expiryDate(collectionSaveRequestDto.getCreatedDate())
//                            .topDebtId(collectionSaveRequestDto.getDebt().getTopDebtId())
//                            .mainDebt(collectionSaveRequestDto.getAmount())
//                            .debtType("GECIKME_ZAMMI").build();
//                    debtService.saveDebt(debtSaveRequestDto);
//                } else {
//                    DebtDto debtDto = DebtDto.builder()
//                            .remainingDebt(0L)
//                            .userId(collectionSaveRequestDto.getUserId())
//                            .topDebtId(collectionSaveRequestDto.getDebt().getTopDebtId())
//                            .mainDebt(collectionSaveRequestDto.getDebt().getMainDebt())
//                            .expiryDate(collectionSaveRequestDto.getCreatedDate())
//                            .debtType("NORMAL").build();
//                    debtService.update(debtDto);
//                }
//            } else {
//                throw new RuntimeException("Cannot become pieced collection!");
//            }
//        });
//    }



}
