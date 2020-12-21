package com.admin.service;

import com.admin.entity.database.DictType;
import com.admin.mapper.DictTypeMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class DictTypeService {
    @Autowired
    DictTypeMapper dictTypeMapper;

    public List<DictType> getDictTypeAll() {
        return dictTypeMapper.getDictTypeAll();
    }

    public int insert(DictType dictType) {
        return dictTypeMapper.insert(dictType);
    }

    public int deleteByDictId(Long dictId) {
        return dictTypeMapper.deleteByDictId(dictId);
    }

    public DictType getDictTypeByDictId(Long dictId) {
        return dictTypeMapper.getDictTypeByDictId(dictId);
    }

    public int updateDictType(DictType dictType) {
        return dictTypeMapper.update(dictType);
    }
}
