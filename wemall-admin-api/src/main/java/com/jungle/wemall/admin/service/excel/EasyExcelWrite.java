package com.jungle.wemall.admin.service.excel;

import com.alibaba.excel.EasyExcelFactory;
import com.alibaba.excel.ExcelWriter;
import com.alibaba.excel.metadata.Sheet;
import com.jungle.wemall.admin.dto.Sum;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.OutputStream;
import java.net.URLEncoder;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * @description: 写入excel
 * @author: jungle
 * @date: 2020-03-14 09:46
 */
@Service
public class EasyExcelWrite {
    public List<Sum> getAllOrder(List<Map<String, Object>> listSum){
        List<Sum> sumList = new ArrayList<>();
        for(Map map: listSum){
            Sum sum = new Sum();
            sum.setTitle((String)map.get("title"));
            sum.setNumber((Long) map.get("number"));
            sumList.add(sum);
        }
        return sumList;
    }

    public void downLoadExcel(String fileName, List<Map<String, Object>> listSum, HttpServletResponse response) {
        OutputStream outputStream=null;
        try {
            outputStream = response.getOutputStream();
            response.setCharacterEncoding("UTF-8");
            response.setHeader("content-Type", "application/vnd.ms-excel");
            response.setHeader("Content-Disposition",
                    "attachment;filename=" + URLEncoder.encode(fileName, "UTF-8"));


            ExcelWriter writer = EasyExcelFactory.getWriter(outputStream);

            // 写仅有一个 Sheet 的 Excel 文件, 此场景较为通用
            Sheet sheet1 = new Sheet(1, 0, Sum.class);

            // 第一个 sheet 名称
            sheet1.setSheetName("第一个sheet");

            // 写数据到 Writer 上下文中
            // 入参1: 数据库查询的数据list集合
            // 入参2: 要写入的目标 sheet
            writer.write(getAllOrder(listSum), sheet1);
//            Sheet sheet2 = new Sheet(2, 0, OrderModel.class);

            // 第一个 sheet 名称
//            sheet2.setSheetName("第二个sheet");

            // 写数据到 Writer 上下文中
            // 入参1: 数据库查询的数据list集合
            // 入参2: 要写入的目标 sheet
//            writer.write(getAllOrder(), sheet2);

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
