package com.aysegulapc.fourthhomework.collection.service.entityService;

import com.aysegulapc.fourthhomework.collection.dao.CollectionDao;
import com.aysegulapc.fourthhomework.collection.entity.Collection;
import com.aysegulapc.fourthhomework.gen.service.BaseEntityService;
import org.springframework.stereotype.Service;

@Service
public class CollectionEntityService extends BaseEntityService<Collection, CollectionDao> {
    public CollectionEntityService(CollectionDao dao) {
        super(dao);
    }
}
