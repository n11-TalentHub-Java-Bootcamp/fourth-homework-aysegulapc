package com.aysegulapc.fourthhomework.usr.service.entityService;

import com.aysegulapc.fourthhomework.gen.service.BaseEntityService;
import com.aysegulapc.fourthhomework.usr.dao.UserDao;
import com.aysegulapc.fourthhomework.usr.entity.User;
import org.springframework.stereotype.Service;

@Service
public class UserEntityService extends BaseEntityService<User, UserDao>{
    public UserEntityService(UserDao dao) {
        super(dao);
    }
}
