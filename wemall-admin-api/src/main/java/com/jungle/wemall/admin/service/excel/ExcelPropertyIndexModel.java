package com.jungle.wemall.admin.service.excel;

/**
 * @description: excelModel
 * @author: jungle
 * @date: 2020-02-18 12:01
 */
import com.alibaba.excel.annotation.ExcelProperty;
import com.alibaba.excel.metadata.BaseRowModel;

// 通过 @ExcelProperty 注解与 index 变量可以标注成员变量所映射的列
//    作为Excel的模型对象,需要setter方法
public class ExcelPropertyIndexModel extends BaseRowModel {
    @ExcelProperty(value = "姓名", index = 0)
    private String name;
    @ExcelProperty(value = "年龄", index = 1)
    private String age;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAge() {
        return age;
    }

    public void setAge(String age) {
        this.age = age;
    }

}
