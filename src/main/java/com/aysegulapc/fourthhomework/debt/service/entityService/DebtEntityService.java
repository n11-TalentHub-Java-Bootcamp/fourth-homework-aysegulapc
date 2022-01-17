package com.aysegulapc.fourthhomework.debt.service.entityService;

import com.aysegulapc.fourthhomework.debt.dao.DebtDao;
import com.aysegulapc.fourthhomework.debt.dto.DebtForSpecificTwoDatesDto;
import com.aysegulapc.fourthhomework.debt.dto.UserDebtDetailDto;
import com.aysegulapc.fourthhomework.debt.dto.UserSumOfDebtDto;
import com.aysegulapc.fourthhomework.debt.entity.Debt;
import com.aysegulapc.fourthhomework.gen.service.BaseEntityService;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

@Service
public class DebtEntityService extends BaseEntityService<Debt, DebtDao> {
    public DebtEntityService(DebtDao dao) {
        super(dao);
    }

    public List<DebtForSpecificTwoDatesDto> findDebtBySpecificTwoDates(Date startDate, Date endDate) {
        return getDao().findDebtBySpecificTwoDates(startDate, endDate);
    }

    public List<UserDebtDetailDto> findAllUserDeptDetailDtoByUserId(Long userId) {
        return getDao().findDebtByUserId(userId);
    }

    public List<UserDebtDetailDto> findAllOverdueDebtsByUserId(Long userId) {
        return getDao().findOverdueDebtsByUserId(userId);
    }

    public UserSumOfDebtDto findSumOfDebtByUserId(Long userId) {
        return getDao().findSumOfDebtByUserId(userId);
    }

    public UserSumOfDebtDto findSumOfOverdueDebtByUserId(Long userId) {
        return getDao().findSumOfOverdueDebtByUserId(userId);
    }
}
