package com.aysegulapc.fourthhomework.usr.dao;

import com.aysegulapc.fourthhomework.usr.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserDao extends JpaRepository <User, Long> {
}
