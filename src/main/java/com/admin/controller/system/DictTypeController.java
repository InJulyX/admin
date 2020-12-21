package com.admin.controller.system;

import com.admin.config.aspect.BusinessType;
import com.admin.config.aspect.Log;
import com.admin.controller.BaseController;
import com.admin.entity.AjaxResult;
import com.admin.entity.TableDataInfo;
import com.admin.entity.database.DictType;
import com.admin.service.DictTypeService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(value = "/system/dict/type")
public class DictTypeController extends BaseController {

    final
    DictTypeService dictTypeService;

    public DictTypeController(DictTypeService dictTypeService) {
        this.dictTypeService = dictTypeService;
    }

    @GetMapping(value = "/list")
    public TableDataInfo getList(DictType dictType) {
        startPage();
        List<?> list = dictTypeService.getDictTypeAll();
        return getTableData(list);
    }

    @PostMapping
    @Log(title = "字典管理", businessType = BusinessType.INSERT)
    public AjaxResult add(@RequestBody DictType dictType) {
        dictType.setCreateBy(getUsername());
        return toAjax(dictTypeService.insert(dictType));
    }

    @DeleteMapping(value = "/{dictId}")
    @Log(title = "字典管理", businessType = BusinessType.DELETE)
    public AjaxResult delete(@PathVariable Long dictId) {
        return toAjax(dictTypeService.deleteByDictId(dictId));
    }

    @GetMapping(value = "/{dictId}")
    public AjaxResult getInfo(@PathVariable Long dictId) {
        AjaxResult ajax = AjaxResult.success();
        ajax.put("data", dictTypeService.getDictTypeByDictId(dictId));
        return ajax;
    }

    @PutMapping
    @Log(title = "字典管理", businessType = BusinessType.UPDATE)
    public AjaxResult edit(@RequestBody DictType dictType) {
        dictType.setUpdateBy(getUsername());
        return toAjax(dictTypeService.updateDictType(dictType));
    }
}
