package com.aysegulapc.fourthhomework.debt.converter;

import com.aysegulapc.fourthhomework.debt.dto.DebtDto;
import com.aysegulapc.fourthhomework.debt.dto.DebtSaveRequestDto;
import com.aysegulapc.fourthhomework.debt.entity.Debt;
import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;
import org.mapstruct.factory.Mappers;

import java.util.List;

@Mapper(unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface DebtMapper {
    DebtMapper INSTANCE = Mappers.getMapper(DebtMapper.class);

    List<DebtDto> convertAllDebtToDebtDto(List<Debt> debt);

    Debt convertDebtSaveRequestDtoToDebt(DebtSaveRequestDto debtSaveRequestDto);

    DebtDto convertDebtToDebtDto(Debt debt);

    Debt convertDebtDtoToDebt(DebtDto debtDto);

    DebtSaveRequestDto convertDebtToDebtSaveRequestDto(Debt debt);
}
