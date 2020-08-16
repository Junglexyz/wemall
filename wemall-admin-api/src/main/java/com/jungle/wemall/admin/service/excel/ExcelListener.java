package com.jungle.wemall.admin.service.excel;


import com.alibaba.excel.context.AnalysisContext;
import com.alibaba.excel.event.AnalysisEventListener;
import com.jungle.wemall.db.pojo.WemallGoods;
import com.jungle.wemall.db.pojo.WemallGoodsSpecification;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

/**
 * @description: excel监听
 * @author: jungle
 * @date: 2020-02-18 12:03*/



public class ExcelListener extends AnalysisEventListener {
    //自定义用于暂时存储data。
    //可以通过实例获取该值
    public List<WemallGoods> datas = new ArrayList<>();
    @Override
    public void invoke(Object object, AnalysisContext context) {
        List<String> stringList= (List<String>) object;
        WemallGoods goods = new WemallGoods();
        WemallGoodsSpecification specification = new WemallGoodsSpecification();
        Integer categoryId = Integer.parseInt(context.getCustom().toString());

        // 去除表头
        if(context.getCurrentRowNum() > 0 && stringList.get(2) != null){
            goods.setCover("http://wemall-jungle.oss-cn-chengdu.aliyuncs.com/goods/"+stringList.get(2)+".jpg");
            goods.setTitle(stringList.get(4));
            goods.setCategoryId(categoryId);
            goods.setPurchasingUnit(stringList.get(8));
            goods.setOriginPrice(new BigDecimal(stringList.get(13)));
            goods.setSellPrice(new BigDecimal(stringList.get(13)));
            goods.setStatus("1");
            goods.setStock(50);
            specification.setSpecification(stringList.get(8));
            specification.setPrice(new BigDecimal(stringList.get(13)));
            List<WemallGoodsSpecification> list = new ArrayList<>();
            list.add(specification);
            goods.setSpecifications(list);
            // 数据存储到list，供批量处理，或后续自己业务逻辑处理。
            datas.add(goods);
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
