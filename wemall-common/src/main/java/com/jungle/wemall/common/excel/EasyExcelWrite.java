package com.jungle.wemall.common.excel;

import com.alibaba.excel.EasyExcelFactory;
import com.alibaba.excel.ExcelWriter;
import com.alibaba.excel.metadata.Sheet;

import javax.servlet.http.HttpServletResponse;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.net.URLEncoder;
import java.util.ArrayList;
import java.util.List;

/**
 * @description: 写入excel
 * @author: jungle
 * @date: 2020-03-14 09:46
 */
public class EasyExcelWrite {
    public void write(){
        OutputStream out = null;
        try {
            out = new FileOutputStream("test.xlsx");
            ExcelWriter writer = EasyExcelFactory.getWriter(out);

            // 写仅有一个 Sheet 的 Excel 文件, 此场景较为通用
            Sheet sheet1 = new Sheet(1, 0, OrderModel.class);

            // 第一个 sheet 名称
            sheet1.setSheetName("第一个sheet");

            // 写数据到 Writer 上下文中
            // 入参1: 数据库查询的数据list集合
            // 入参2: 要写入的目标 sheet
            writer.write(getAllOrder(), sheet1);

            // 将上下文中的最终 outputStream 写入到指定文件中
            writer.finish();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }finally {
            try {
                // 关闭流
                out.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    public List<OrderModel> getAllOrder(){
        List<OrderModel> orderList = new ArrayList<>();
        for (int i=0;i<100;i++){
            OrderModel order = new OrderModel();
            order.setOrderId(i+"");
            order.setName("张三"+i);
            order.setTotalMoney(11.11+i);
            if(i % 3 == 0){
                order.setPackupCode("12"+i);
            }
            order.setTel("123456778908");
            order.setAddress("地址11"+i);
            order.setDetail("面包x3 牛奶x4 ");
            orderList.add(order);
        }
        return orderList;
    }

    public void downLoadExcel(String fileName, HttpServletResponse response) {
        OutputStream outputStream=null;
        try {
            response.setCharacterEncoding("UTF-8");
            response.setHeader("content-Type", "application/vnd.ms-excel");
            response.setHeader("Content-Disposition",
                    "attachment;filename=" + URLEncoder.encode(fileName, "UTF-8"));
            outputStream = response.getOutputStream();

            ExcelWriter writer = EasyExcelFactory.getWriter(outputStream);

            // 写仅有一个 Sheet 的 Excel 文件, 此场景较为通用
            Sheet sheet1 = new Sheet(1, 0, OrderModel.class);

            // 第一个 sheet 名称
            sheet1.setSheetName("第一个sheet");

            // 写数据到 Writer 上下文中
            // 入参1: 数据库查询的数据list集合
            // 入参2: 要写入的目标 sheet
            writer.write(getAllOrder(), sheet1);
            Sheet sheet2 = new Sheet(2, 0, OrderModel.class);

            // 第一个 sheet 名称
            sheet2.setSheetName("第二个sheet");

            // 写数据到 Writer 上下文中
            // 入参1: 数据库查询的数据list集合
            // 入参2: 要写入的目标 sheet
            writer.write(getAllOrder(), sheet2);

            // 将上下文中的最终 outputStream 写入到指定文件中
            writer.finish();
        } catch (IOException e) {

        } finally {
            try {
                outputStream.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
}
