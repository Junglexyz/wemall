package com.jungle.wemall.common.excel;

import com.alibaba.excel.annotation.ExcelProperty;
import com.alibaba.excel.metadata.BaseRowModel;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

/**
 * @description: 订单表头
 * @author: jungle
 * @date: 2020-03-14 09:35
 */
public class OrderModel extends BaseRowModel {
    /*@ExcelProperty(value = "订单编号",index = 0)
    private String orderId;

    @ExcelProperty(value = "用户编号",index = 0)
    private Integer userId;

    @ExcelProperty(value = "订单商品总数",index = 0)
    private Integer totalNumber;

    @ExcelProperty(value = "应付金额",index = 0)
    private BigDecimal totalMoney;

    @ExcelProperty(value = "折扣金额",index = 0)
    private BigDecimal discountMoney;

    @ExcelProperty(value = "优惠金额",index = 0)
    private BigDecimal couponMoney;

    private BigDecimal payMoney;

    private BigDecimal returnMoney;

    private String payWay;

    private String deliveryWay;

    private String packupCode;

    private Integer packNumber;

    private Date dayStamp;

    private Date createTime;

    private Date payTime;

    private Date deliverTime;

    private Date finalTime;

    private Date updateTime;

    private String status;

    private String orderDetail;*/
    @ExcelProperty(value = "订单编号",index = 0)
    private String orderId;

    @ExcelProperty(value = "用户姓名",index = 1)
    private String name;

    @ExcelProperty(value = "订单总价",index = 2)
    private Double totalMoney;

    @ExcelProperty(value = "取件码",index = 3)
    private String packupCode;

    @ExcelProperty(value = "用户电话",index = 4)
    private String tel;

    @ExcelProperty(value = "用户地址",index = 5)
    private String address;

    @ExcelProperty(value = "订单详情",index = 6)
    private String detail;

    public String getOrderId() {
        return orderId;
    }

    public void setOrderId(String orderId) {
        this.orderId = orderId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Double getTotalMoney() {
        return totalMoney;
    }

    public void setTotalMoney(Double totalMoney) {
        this.totalMoney = totalMoney;
    }

    public String getPackupCode() {
        return packupCode;
    }

    public void setPackupCode(String packupCode) {
        this.packupCode = packupCode;
    }

    public String getTel() {
        return tel;
    }

    public void setTel(String tel) {
        this.tel = tel;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getDetail() {
        return detail;
    }

    public void setDetail(String detail) {
        this.detail = detail;
    }
}
