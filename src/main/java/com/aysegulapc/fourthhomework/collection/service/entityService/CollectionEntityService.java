package com.aysegulapc.fourthhomework.collection.service.entityService;

import com.aysegulapc.fourthhomework.collection.dao.CollectionDao;
import com.aysegulapc.fourthhomework.collection.dto.CollectionForSpecificTwoDatesDto;
import com.aysegulapc.fourthhomework.collection.dto.UserCollectionDetailDto;
import com.aysegulapc.fourthhomework.collection.entity.Collection;
import com.aysegulapc.fourthhomework.debt.dto.DebtForSpecificTwoDatesDto;
import com.aysegulapc.fourthhomework.gen.service.BaseEntityService;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

@Service
public class CollectionEntityService extends BaseEntityService<Collection, CollectionDao> {
    public CollectionEntityService(CollectionDao dao) {
        super(dao);
    }

    public List<CollectionForSpecificTwoDatesDto> findCollectionBySpecificTwoDates(Date startDate, Date endDate) {
        return getDao().findCollectionBySpecificTwoDates(startDate, endDate);
    }

    public List<UserCollectionDetailDto> findAllCollectionByUserId(Long userId) {
        return getDao().findAllCollectionByUserId(userId);
    }
}
