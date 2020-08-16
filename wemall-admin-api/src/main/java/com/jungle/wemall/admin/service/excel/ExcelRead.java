package com.jungle.wemall.admin.service.excel;

/*
 * @description: excel reader
 * @author: jungle
 * @date: 2020-02-18 12:07
*/


import com.alibaba.excel.ExcelReader;
import com.alibaba.excel.support.ExcelTypeEnum;
import com.jungle.wemall.db.pojo.WemallGoods;

import java.io.*;
import java.util.List;

public class ExcelRead {
    private static InputStream getInputStream(String fileName) {
        try {
            return new FileInputStream(new File(fileName));
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        return null;
    }
    public static List<WemallGoods> excelRead(InputStream inputStream, Integer categoryId) {
        try {
            // 解析每行结果在listener中处理
            ExcelListener listener = new ExcelListener();
            ExcelReader excelReader = new ExcelReader(inputStream, ExcelTypeEnum.XLS, categoryId, listener);
            excelReader.read();
            List<WemallGoods> datas = listener.getDatas();
            return datas;
        } catch (Exception e) {
            e.printStackTrace();
            return  null;
        } finally {
            try {
                inputStream.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
}
