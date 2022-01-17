package com.aysegulapc.fourthhomework.collection.dao;

import com.aysegulapc.fourthhomework.collection.entity.Collection;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CollectionDao extends JpaRepository<Collection, Long> {
}
