package com.admin.controller.system;

import com.admin.config.aspect.BusinessType;
import com.admin.config.aspect.Log;
import com.admin.entity.DictType;
import com.admin.entity.Result;
import com.admin.service.DictTypeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(value = "/system/dict/type")
public class DictTypeController {

    final
    DictTypeService dictTypeService;

    public DictTypeController(DictTypeService dictTypeService) {
        this.dictTypeService = dictTypeService;
    }

    @GetMapping(value = "/list")
    public Result getList() {
        Result result = new Result();
        result.setRows(dictTypeService.getDictTypeAll());
        return result;
    }

    @PostMapping
    @Log(title = "字典管理", businessType = BusinessType.INSERT)
    public void add(@RequestBody DictType dictType) {
        dictTypeService.insert(dictType);
    }

    @DeleteMapping(value = "/{dictId}")
    @Log(title = "字典管理", businessType = BusinessType.DELETE)
    public void delete(@PathVariable Long dictId) {
        dictTypeService.deleteByDictId(dictId);
    }

    @GetMapping(value = "/{dictId}")
    public DictType getInfo(@PathVariable Long dictId) {
        return dictTypeService.getDictTypeByDictId(dictId);
    }
}
