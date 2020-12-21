package com.admin.controller.system;

import com.admin.config.aspect.BusinessType;
import com.admin.config.aspect.Log;
import com.admin.controller.BaseController;
import com.admin.entity.AjaxResult;
import com.admin.entity.TableDataInfo;
import com.admin.entity.database.DictData;
import com.admin.mapper.DictDataMapper;
import com.admin.service.DictDataService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

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
    public AjaxResult getDataInfo(@PathVariable String dictType) {
        AjaxResult ajax = AjaxResult.success();
        DictData s1 = new DictData();
        s1.setDictType(dictType);
        ajax.put("data", dictDataMapper.getDictDataList(s1));
        return ajax;
    }


    @GetMapping(value = "/list")
    public TableDataInfo getDictDataList(DictData dictData) {
        startPage();
        List<?> list = dictDataMapper.getDictDataList(dictData);
        return getTableData(list);
    }

    @PostMapping
    @Log(title = "字典数据", businessType = BusinessType.INSERT)
    public AjaxResult add(@RequestBody DictData dictData) {
        dictData.setCreateBy(getUsername());
        return toAjax(dictDataService.insert(dictData));
    }

    @DeleteMapping(value = "/{dictCode}")
    @Log(title = "字典数据", businessType = BusinessType.DELETE)
    public AjaxResult delete(@PathVariable Long dictCode) {
        return toAjax(dictDataService.deleteByDictCode(dictCode));
    }

    @GetMapping(value = "/{dictCode}")
    public AjaxResult getDictData(@PathVariable Long dictCode) {
        AjaxResult ajax = AjaxResult.success();
        ajax.put("data", dictDataService.getDictData(dictCode));
        return ajax;
    }

    @PutMapping
    @Log(title = "字典数据", businessType = BusinessType.UPDATE)
    public AjaxResult edit(@RequestBody DictData dictData) {
        dictData.setUpdateBy(getUsername());
        return toAjax(dictDataService.updateDictData(dictData));

    }


}
