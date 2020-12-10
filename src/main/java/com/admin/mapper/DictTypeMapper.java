package com.admin.mapper;

import com.admin.entity.DictType;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Component;

import java.util.List;

@Mapper
@Component
public interface DictTypeMapper {
    List<DictType> getDictTypeAll();

    int insert(DictType dictType);

    int deleteByDictId(Long dictId);

    DictType getDictTypeByDictId(Long dictId);

    DictType getDictType(DictType dictType);

    List<DictType> getDictTypeList(DictType dictType);
}
