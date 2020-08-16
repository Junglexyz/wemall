package com.jungle.wemall.admin.dto;

import com.alibaba.excel.annotation.ExcelProperty;
import com.alibaba.excel.metadata.BaseRowModel;

/**
 * @description: SumOrder
 * @author: jungle
 * @date: 2020-06-06 12:59
 */
public class Sum extends BaseRowModel {
    @ExcelProperty(value = "商品名称",index = 0)
    private String title;

    @ExcelProperty(value = "商品数量",index = 0)
    private Long number;

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public Long getNumber() {
        return number;
    }

    public void setNumber(Long number) {
        this.number = number;
    }
}
