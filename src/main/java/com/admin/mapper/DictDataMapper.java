package com.admin.mapper;

import com.admin.entity.DictData;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Component;

import java.util.List;

@Mapper
@Component
public interface DictDataMapper {

    int insert(DictData dictData);

    int insertBatch(List<DictData> dictDataList);

    DictData getDictData(DictData dictData);

    List<DictData> getDictDataList(DictData dictData);

    int delete(Long dictCode);
    List<DictData> test();
}
