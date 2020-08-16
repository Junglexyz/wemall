package com.jungle.wemall.db.util;

import com.jungle.wemall.db.pojo.WemallOrder;
import com.jungle.wemall.db.pojo.WemallOrderDetail;
import com.jungle.wemall.db.pojo.WemallUserAddress;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.Map;

/**
 * @description: 打印模板
 * @author: jungle
 * @date: 2020-03-21 09:08
 */
public class PrintMouldUtil {
    public static String getContent(WemallOrder order, WemallUserAddress address, List<WemallOrderDetail> orderDetails, String mobile){
        StringBuilder content = new StringBuilder();
        // 打印多联
        content.append("<MN>2</MN>")
            .append("<FH><FW> **#3文承维生活超市**</FW></FH>\r")
            .append("................................\r")
            .append("<FH><FW>      --").append("在线支付").append("--</FW></FH>\r")
            .append("下单时间：").append(LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss"))).append("\r")
            .append("订单编号：").append(order.getOrderId()).append("\r")
            .append("***************商品*************\r")
            .append("<FH><FW>名称    数量   总价</FW></FH>\r");
        for(WemallOrderDetail detail: orderDetails){
            content.append(format(detail.getTitle()))
                .append(String.format("%6s", detail.getNumber()))
                .append(String.format("%10s", detail.getSellPrice()))
                .append("\r");
        }
        content.append("................................\r")
                .append("<FH2><FW2>").append("取件码:").append(order.getPackupCode()).append("</FW2></FH2>\r")
                .append("<FH><FW>小计: ￥").append(order.getPayMoney()).append("</FW></FH>\r")
                .append("<FH><FW>折扣: ￥").append(order.getDiscountMoney()).append("</FW></FH>\r");

        if(address != null){
            content.append("<FH><FW>").append(address.getUsername()).append(" ").append(address.getMobile()).append("</FW></FH>\r")
                    .append("<FH><FW>").append(address.getTown()).append(address.getVillage()).append(address.getGroups()).append(address.getAddress()).append("</FW></FH>\r");
        }
        if(mobile != null){
            content.append("<FH><FW>").append(mobile).append("</FW></FH>\r");
        }
    return content.toString();
    }

    private static String format(String title){
        String format = null;
        if(title.length() <= 8){
            format = "%-"+ space(title) + "s";
//            return title+"\r\n"+String.format(format, "");
            return String.format(format, title);
        }else{
            format = "%-"+ 16 + "s";
            return title+"\r\n"+String.format(format, "");
        }
    }

    /**
     * 计算中文个数
     * @param title
     * @return
     */
    private static int space(String title){
        int space = 0;
        char[] charArray = title.toCharArray();
        for (char c : charArray) {
            int result = isCnorEn(c);
            if(result == 1){
                space += 1;
            }
        }
        return 16 - space;
    }

    /**
     * 判断字符是否为中文
     * @param c
     * @return
     */
    private  static int isCnorEn(char c){
        if(c >= 0x0391 && c <= 0xFFE5) //中文字符
            return 1;
        if(c>=0x0000 && c<=0x00FF){ //英文字符
            return 2;
        }
        return 3;
    }
}
