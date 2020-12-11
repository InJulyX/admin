package com.admin.controller.system;

import com.admin.config.aspect.BusinessType;
import com.admin.config.aspect.Log;
import com.admin.controller.BaseController;
import com.admin.entity.DictData;
import com.admin.entity.Result;
import com.admin.mapper.DictDataMapper;
import com.admin.service.DictDataService;
import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@Slf4j
@RestController
@RequestMapping(value = "/system/dict/data")
public class DictDataController extends BaseController {
    final
    DictDataMapper dictDataMapper;
    final
    DictDataService dictDataService;

    public DictDataController(DictDataService dictDataService, DictDataMapper dictDataMapper) {
        this.dictDataService = dictDataService;
        this.dictDataMapper = dictDataMapper;
    }

    @GetMapping(value = "/type/{dictType}")
    public List<DictData> getDataInfo(@PathVariable String dictType) {

        DictData s1 = new DictData();
        s1.setDictType(dictType);
        dictDataMapper.getDictDataList(s1);
        return dictDataMapper.getDictDataList(s1);
    }


    @GetMapping(value = "/list")
    public Result getDictDataList(DictData dictData) {
        startPage();
        List<?> list = dictDataMapper.getDictDataList(dictData);
        return getResultInfo(list);
    }

    @PostMapping
    @Log(title = "字典数据", businessType = BusinessType.INSERT)
    public void add(@RequestBody DictData dictData) {
        dictDataService.insert(dictData);
    }

    @DeleteMapping(value = "/{dictCode}")
    @Log(title = "字典数据", businessType = BusinessType.DELETE)
    public Result delete(@PathVariable Long dictCode) {
        Result result = new Result();
        result.setTotal(dictDataService.deleteByDictCode(dictCode));
        return result;
    }

    @GetMapping(value = "/{dictCode}")
    public DictData getDictData(@PathVariable Long dictCode) {
        return dictDataService.getDictData(dictCode);
    }

    @PutMapping
    @Log(title = "字典数据", businessType = BusinessType.UPDATE)
    public Result edit(@RequestBody DictData dictData) {
        Result result = new Result();
        return result;
    }


}
