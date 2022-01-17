package com.aysegulapc.fourthhomework.debt.dao;

import com.aysegulapc.fourthhomework.debt.dto.DebtForSpecificTwoDatesDto;
import com.aysegulapc.fourthhomework.debt.dto.UserDebtDetailDto;
import com.aysegulapc.fourthhomework.debt.dto.UserSumOfDebtDto;
import com.aysegulapc.fourthhomework.debt.entity.Debt;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.Date;
import java.util.List;

@Repository
public interface DebtDao extends JpaRepository<Debt, Long> {

    @Query("select" +
            " new com.aysegulapc.fourthhomework.debt.dto.DebtForSpecificTwoDatesDto(" +
            " debt.debtType, " +
            " debt.mainDebt, " +
            " debt.remainingDebt, " +
            " debt.expiryDate " +
            " ) " +
            " from Debt debt " +
            " where debt.expiryDate >= :startDate and debt.expiryDate <= :endDate ")
    List<DebtForSpecificTwoDatesDto> findDebtBySpecificTwoDates(@Param("startDate") Date startDate, @Param("endDate") Date endDate);

    @Query("select " +
            " new com.aysegulapc.fourthhomework.debt.dto.UserDebtDetailDto(" +
            " user.id, " +
            " user.email, " +
            " user.firstName, " +
            " user.lastName, " +
            " debt.debtType, " +
            " debt.mainDebt, " +
            " debt.remainingDebt, " +
            " debt.expiryDate " +
            " ) " +
            " from User user " +
            " left join Debt debt " +
            " on user.id = debt.userId " +
            " where user.id = :userId and debt.remainingDebt > 0")
    List<UserDebtDetailDto> findDebtByUserId(@Param("userId") Long userId);

    @Query("select " +
            " new com.aysegulapc.fourthhomework.debt.dto.UserDebtDetailDto(" +
            " user.id, " +
            " user.email, " +
            " user.firstName, " +
            " user.lastName, " +
            " debt.debtType, " +
            " debt.mainDebt, " +
            " debt.remainingDebt, " +
            " debt.expiryDate " +
            " ) " +
            " from User user " +
            " left join Debt debt " +
            " on user.id = debt.userId " +
            " where user.id = :userId and debt.expiryDate < CURRENT_DATE"
    )
    List<UserDebtDetailDto> findOverdueDebtsByUserId(@Param("userId") Long userId);

    @Query("select " +
            " new com.aysegulapc.fourthhomework.debt.dto.UserSumOfDebtDto(" +
            " user.id, " +
            " user.firstName, " +
            " user.lastName," +
            " sum(debt.remainingDebt)" +
            " ) " +
            " from User user" +
            " left join Debt debt " +
            " on user.id = debt.userId " +
            " where user.id = :userId " +
            " group by user.id")
    UserSumOfDebtDto findSumOfDebtByUserId(@Param("userId") Long userId);

    @Query("select " +
            " new com.aysegulapc.fourthhomework.debt.dto.UserSumOfDebtDto(" +
            " user.id, " +
            " user.firstName, " +
            " user.lastName," +
            " sum(debt.remainingDebt)" +
            " ) " +
            " from User user" +
            " left join Debt debt " +
            " on user.id = debt.userId " +
            " where user.id = :userId and debt.expiryDate < CURRENT_DATE" +
            " group by user.id")
    UserSumOfDebtDto findSumOfOverdueDebtByUserId(@Param("userId") Long userId);
}
