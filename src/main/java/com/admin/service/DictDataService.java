package com.admin.service;

import com.admin.entity.DictData;
import com.admin.mapper.DictDataMapper;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@Slf4j
public class DictDataService {
    final
    DictDataMapper dictDataMapper;

    public DictDataService(DictDataMapper dictDataMapper) {
        this.dictDataMapper = dictDataMapper;
    }

    public List<DictData> getDictDataList(String dictType) {
        DictData dictData = new DictData();
        dictData.setDictType(dictType);
        log.error(dictData.toString());
        List<DictData> s11 =dictDataMapper.getDictDataList(dictData);
        return s11;
    }

    public List<DictData> getDictDataList(DictData dictData) {
        return dictDataMapper.getDictDataList(dictData);
    }

    public int insert(DictData dictData) {
        return dictDataMapper.insert(dictData);
    }

    public int deleteByDictCode(Long dictCode) {
        return dictDataMapper.delete(dictCode);
    }

    public DictData getDictData(Long dictCode) {
        DictData dictData = new DictData();
        dictData.setDictCode(dictCode);
        return dictDataMapper.getDictData(dictData);
    }
}
