package com.aysegulapc.fourthhomework.collection.converter;

import com.aysegulapc.fourthhomework.collection.dto.CollectionDto;
import com.aysegulapc.fourthhomework.collection.dto.CollectionSaveRequestDto;
import com.aysegulapc.fourthhomework.collection.entity.Collection;
import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;
import org.mapstruct.factory.Mappers;

import java.util.List;

@Mapper(unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface CollectionMapper {
    CollectionMapper INSTANCE = Mappers.getMapper(CollectionMapper.class);

    List<CollectionDto> convertAllCollectionToCollectionDto(List<Collection> collectionList);

    Collection convertCollectionSaveRequestDtoToCollection(CollectionSaveRequestDto collectionSaveRequestDto);

    CollectionDto convertCollectionToCollectionDto(Collection collection);
}
