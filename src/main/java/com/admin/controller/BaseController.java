package com.admin.controller;

import com.admin.entity.AjaxResult;
import com.admin.entity.Result;
import com.admin.entity.TableDataInfo;
import com.admin.utils.ServletUtils;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.apache.shiro.SecurityUtils;

import java.util.List;

public class BaseController {
//    @InitBinder
//    public void initBinder(WebDataBinder binder) {
//        binder.registerCustomEditor(Date.class, new PropertyEditorSupport() {
//            @Override
//            public void setAsText(String text) {
//                try {
//                    setValue(DateUtils.parseDate(text));
//                } catch (ParseException e) {
//                    e.printStackTrace();
//                }
//            }
//        });
//    }

    public String getUsername() {
        return (String) SecurityUtils.getSubject().getPrincipal();
    }

    protected void startPage() {

        Integer pageNum = ServletUtils.getParameterToInt("pageNum");
        Integer pageSize = ServletUtils.getParameterToInt("pageSize");

        PageHelper.startPage(pageNum, pageSize);
    }

    protected Result getResultInfo(List<?> list) {
        Result result = new Result();
        result.setRows(list);
        result.setTotal(new PageInfo(list).getTotal());
        return result;
    }

    protected Result getResultInfo(long total) {
        Result result = new Result();
        result.setTotal(total);
        return result;
    }

    @SuppressWarnings({"rawtypes", "unchecked"})
    protected TableDataInfo getTableData(List<?> list) {
        TableDataInfo rspData = new TableDataInfo();
        rspData.setCode(200);
        rspData.setMessage("操作成功");
        rspData.setRows(list);
        rspData.setTotal(new PageInfo(list).getTotal());
        return rspData;
    }

    protected AjaxResult toAjax(int rows) {
        return rows > 0 ? AjaxResult.success() : AjaxResult.error();
    }
}
