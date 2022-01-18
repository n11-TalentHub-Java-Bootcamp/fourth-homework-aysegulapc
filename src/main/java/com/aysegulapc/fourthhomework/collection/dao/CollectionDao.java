package com.aysegulapc.fourthhomework.collection.dao;

import com.aysegulapc.fourthhomework.collection.dto.CollectionForSpecificTwoDatesDto;
import com.aysegulapc.fourthhomework.collection.dto.UserCollectionDetailDto;
import com.aysegulapc.fourthhomework.collection.entity.Collection;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.Date;
import java.util.List;

@Repository
public interface CollectionDao extends JpaRepository<Collection, Long> {

    @Query("select" +
            " new com.aysegulapc.fourthhomework.collection.dto.CollectionForSpecificTwoDatesDto(" +
            " collection.id, " +
            " collection.amount, " +
            " collection.createdDate, " +
            " collection.userId " +
            " ) " +
            " from Collection collection " +
            " where collection.createdDate >= :startDate and collection.createdDate <= :endDate ")
    List<CollectionForSpecificTwoDatesDto> findCollectionBySpecificTwoDates(@Param("startDate") Date startDate, @Param("endDate") Date endDate);

    @Query("select " +
            " new com.aysegulapc.fourthhomework.collection.dto.UserCollectionDetailDto(" +
            " collection.id, " +
            " collection.amount, " +
            " collection.createdDate, " +
            " collection.userId, " +
            " user.firstName, " +
            " user.lastName " +
            " ) " +
            " from Collection collection " +
            " left join User user " +
            " on user.id = collection.userId " +
            " where collection.userId = :userId")
    List<UserCollectionDetailDto> findAllCollectionByUserId(@Param("userId") Long userId);
}
