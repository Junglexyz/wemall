package com.jungle.wemall.admin.service.excel;

import com.alibaba.excel.read.context.AnalysisContext;
import com.alibaba.excel.read.event.AnalysisEventListener;
import com.jungle.wemall.db.pojo.WemallGoods;
import com.jungle.wemall.db.service.WemallGoodsService;
import org.springframework.beans.factory.annotation.Autowired;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

/**
 * @description: excel监听
 * @author: jungle
 * @date: 2020-02-18 12:03
 */

public class ExcelListener extends AnalysisEventListener {
    //自定义用于暂时存储data。
    //可以通过实例获取该值
    public List<WemallGoods> datas = new ArrayList<>();
    @Override
    public void invoke(Object object, AnalysisContext context) {
        List<String> stringList= (List<String>) object;
        WemallGoods goods = new WemallGoods();
        System.out.println("cus:"+context.getCustom());
        Integer categoryId = Integer.parseInt(context.getCustom().toString());
        if(context.getCurrentRowNum() > 0){
            goods.setTitle(stringList.get(4));
            goods.setCategoryId(categoryId);
            goods.setPurchasingPrice(new BigDecimal(stringList.get(11)));
            goods.setPurchasingUnit(stringList.get(8));
            goods.setOriginPrice(new BigDecimal(stringList.get(13)));
            goods.setSellPrice(new BigDecimal(stringList.get(13)));
            goods.setStatus("1");
            datas.add(goods); //数据存储到list，供批量处理，或后续自己业务逻辑处理。
            doSomething(goods); //根据自己业务做处理
        }
    }
    private void doSomething(WemallGoods goods) {
        //1、入库调用接口
        System.out.println("入库"+goods.getTitle());
    }
    @Override
    public void doAfterAllAnalysed(AnalysisContext context) {
//        datas.clear();//解析结束销毁不用的资源
    }
    public List<WemallGoods> getDatas() { return datas; }
    public void setDatas(List<WemallGoods> datas) { this.datas = datas; }
}
