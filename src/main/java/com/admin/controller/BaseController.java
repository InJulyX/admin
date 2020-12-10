package com.admin.controller;

import com.admin.config.TableDataInfo;
import com.admin.entity.PageDomain;
import com.admin.entity.Result;
import com.admin.utils.ServletUtils;
import com.admin.utils.StringUtils;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.apache.commons.lang3.time.DateUtils;
import org.apache.shiro.SecurityUtils;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.InitBinder;

import java.beans.PropertyEditorSupport;
import java.text.ParseException;
import java.util.Date;
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
}
