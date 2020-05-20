package com.jungle.wemall.db.service;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.github.binarywang.wxpay.bean.notify.WxPayNotifyResponse;
import com.github.binarywang.wxpay.bean.notify.WxPayOrderNotifyResult;
import com.github.binarywang.wxpay.bean.order.WxPayMpOrderResult;
import com.github.binarywang.wxpay.bean.request.WxPayUnifiedOrderRequest;
import com.github.binarywang.wxpay.bean.result.BaseWxPayResult;
import com.github.binarywang.wxpay.constant.WxPayConstants;
import com.github.binarywang.wxpay.exception.WxPayException;
import com.github.binarywang.wxpay.service.WxPayService;
import com.google.gson.JsonObject;
import com.jungle.wemall.common.notify.NotifyService;
import com.jungle.wemall.common.print.PrintService;
import com.jungle.wemall.common.util.IpUtil;
import com.jungle.wemall.common.util.ResponseUtil;
import com.jungle.wemall.db.dao.*;
import com.jungle.wemall.db.pojo.WemallOrder;
import com.jungle.wemall.db.pojo.WemallOrderDetail;
import com.jungle.wemall.db.pojo.WemallUserAddress;
import com.jungle.wemall.db.util.FastJsonUtil;
import com.jungle.wemall.db.util.PrintMouldUtil;
import org.apache.commons.io.IOUtils;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.math.BigDecimal;
import java.text.DecimalFormat;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @description: 订单
 * @author: jungle
 * @date: 2020-02-27 23:41
 */
@Service
public class WemallOrderService {
    private final Log logger = LogFactory.getLog(WemallOrderService.class);
    @Resource
    private WemallOrderDetailMapper wemallOrderDetailMapper;
    @Resource
    private WemallOrderMapper wemallOrderMapper;
    @Resource
    private WemallGoodsMapper wemallGoodsMapper;
    @Resource
    private WemallUserAddressMapper wemallUserAddressMapper;
    @Resource
    private WemallCouponAcceptMapper wemallCouponAcceptMapper;
    @Autowired
    private StringRedisTemplate stringRedisTemplate;
    @Autowired
    private WxPayService wxPayService;
    @Autowired
    private PrintService printService;
    @Autowired
    private NotifyService notifyService;

    @Transactional(rollbackFor=Exception.class)
    public Object submitOrder(String body) {
        long suffix = stringRedisTemplate.opsForValue().increment("orderId", 1);
        LocalDate now = LocalDate.now();
        // 格式化前缀
        String prefix = now.format(DateTimeFormatter.ofPattern("yyyyMMdd"));
        String format = new DecimalFormat("000000").format(suffix);
        String orderId = prefix + format;
        JSONObject jsonObject = JSONObject.parseObject(body);
        // 订单详情列表
        List orders = new ArrayList<>();
        // 已售罄商品
        List sellOutGoods = new ArrayList<>();
        // 已减库存商品
        List reduceGoods = new ArrayList<>();
        // 订单详情集合
        String orderList = "orders";
        String orderIdKey = "orderId";
        String goodsIdKey = "goodsId";
        if(jsonObject.containsKey(orderList)){
            JSONArray listOrders = jsonObject.getJSONArray(orderList);
            for(int i = 0; i < listOrders.size(); i ++){
                listOrders.getJSONObject(i).put(orderIdKey, orderId);
                // 减库存
                int reduceResult = wemallGoodsMapper.reduceStock((Integer) listOrders.getJSONObject(i).get(goodsIdKey), (Integer) listOrders.getJSONObject(i).get("number"));
                if(reduceResult == 0){
                    // 存储库存不够的商品信息
                    sellOutGoods.add(listOrders.getJSONObject(i));
                } else {
                    reduceGoods.add(listOrders.getJSONObject(i));
                }
            }
            orders = (List)listOrders;
        }
        // TODO 恢复库存
        if(sellOutGoods.size() > 0){
            for(int i = 0; i< reduceGoods.size(); i++){
                System.out.println(JSONObject.parseObject(reduceGoods.get(0).toString()));
                JSONObject object = JSONObject.parseObject(reduceGoods.get(0).toString());
                wemallGoodsMapper.addStock(object.getInteger("goodsId"), object.getInteger("number"));
            }
        }
        // 订单汇总
        Map order = FastJsonUtil.getMap(body, "order");
        order.put("orderId", orderId);
        JSONArray coupons = FastJsonUtil.getArray(body, "coupons");

        // 作废可使用的优惠券
        if(coupons != null){
            for(int i = 0; i < coupons.size(); i ++){
                HashMap<String, Object> data = new HashMap<>(4);
                data.put("couponId", (Integer)coupons.getJSONObject(i).get("couponId"));
                data.put("userId", order.get("userId"));
                // 清除优惠券
                wemallCouponAcceptMapper.removeUsedCoupon(data);
            }
        }
        int resultOfInsertOrders = wemallOrderDetailMapper.insertByBatch(orders);
        int resultOfInsertOrder = wemallOrderMapper.insertByMap(order);
        Map<String, Object> result = new HashMap<>(4);
        result.put("orderId", orderId);
        result.put("sellOutGoods", sellOutGoods);
        return result;
    }

    public Map<String,Object> listOrder(Map order){
        int page = 1;
        String pageKey = "page";
        if(order.containsKey(pageKey)){
            page = (int)order.get(pageKey);
        }
        order.put(pageKey, (page - 1) * 10);
        order.put("pageSize", 10);
        List<WemallOrder> list = wemallOrderMapper.selectByPage(order);
        Integer count = wemallOrderMapper.count(order);
        for(int i = 0; i < list.size(); i++){
            // 订单详情
            List<WemallOrderDetail> wemallOrderDetails = wemallOrderDetailMapper.selectByOrderId(list.get(i).getOrderId());
            list.get(i).setOrderDetail(wemallOrderDetails);
        }
        Map<String,Object> result = new HashMap<>(4);
        result.put("list",list);
        result.put("count",count);
        return  result;
    }

    public Integer countOrderAll(Map order){
        return wemallOrderMapper.count(order);
    }

    public Double totalSales(Map order){
        return wemallOrderMapper.sumOrderSales(order);
    }

    public int updateOrder(WemallOrder order) {
        return wemallOrderMapper.updateByPrimaryKeySelective(order);
    }

    /**
     * 删除订单
     * @param body
     * @return
     */
    public int deleteOrderByOrderId(String body){
        Integer id = FastJsonUtil.getInteger(body, "id");
        String orderId = FastJsonUtil.getString(body, "orderId");
        // TODO 恢复库存
        List<WemallOrderDetail> wemallOrderDetails = wemallOrderDetailMapper.selectByOrderId(orderId);
        for(WemallOrderDetail detail: wemallOrderDetails){
            wemallGoodsMapper.addStock(detail.getGoodsId(), detail.getNumber());
        }
        int result = wemallOrderMapper.deleteByPrimaryKey(id);
        int result2 = wemallOrderDetailMapper.deleteByOrderId(orderId);
        // TODO 恢复已使用的优惠券

        return  result > 0 && result2 > 0 ? 1 : 0;
    }

    /**
     * @Transactional(rollbackFor=Exception.class) 可检异常回滚
     * @param order
     * @param request
     * @return
     */
    @Transactional(rollbackFor=Exception.class)
    public Object prepay(Map order, HttpServletRequest request){
        WxPayUnifiedOrderRequest orderRequest = new WxPayUnifiedOrderRequest();
        String orderId = String.valueOf(order.get("orderId"));
        orderRequest.setOutTradeNo(orderId);
        orderRequest.setOpenid(String.valueOf(order.get("openid")));
        orderRequest.setBody("订单：" + orderId);
        // 元转成分
        int fee = 0;
        BigDecimal actualPrice = new BigDecimal(String.valueOf(order.get("payMoney")));
        fee = actualPrice.multiply(new BigDecimal(100)).intValue();
        orderRequest.setTotalFee(fee);
        orderRequest.setSpbillCreateIp(IpUtil.getIpAddr(request));
        WxPayMpOrderResult result = null;
        try {
            result = wxPayService.createOrder(orderRequest);
        } catch (WxPayException e) {
            e.printStackTrace();
            return ResponseUtil.fail(1001, "支付失败！");
        }
        // TODO 随机发放红包

        return result;
    }

    /**
     * 微信付款成功或失败回调接口
     * <p>
     * 1. 检测当前订单是否是付款状态;
     * 2. 设置订单付款成功状态相关信息;
     * 3. 响应微信商户平台.
     *
     * @param request  请求内容
     * @param response 响应内容
     * @return 操作结果
     */
    @Transactional(rollbackFor=Exception.class)
    public String payNotify(HttpServletRequest request, HttpServletResponse response) {
        String xmlResult = null;
        try {
            xmlResult = IOUtils.toString(request.getInputStream(), request.getCharacterEncoding());
        } catch (IOException e) {
            e.printStackTrace();
            return WxPayNotifyResponse.fail(e.getMessage());
        }
        System.out.println("notify");
        WxPayOrderNotifyResult result = null;
        try {
            result = wxPayService.parseOrderNotifyResult(xmlResult);

            if(!WxPayConstants.ResultCode.SUCCESS.equals(result.getResultCode())){
                logger.error(xmlResult);
                throw new WxPayException("微信通知支付失败！");
            }
            if(!WxPayConstants.ResultCode.SUCCESS.equals(result.getReturnCode())){
                logger.error(xmlResult);
                throw new WxPayException("微信通知支付失败！");
            }
        } catch (WxPayException e) {
            e.printStackTrace();
            logger.error(e.getMessage());
            return WxPayNotifyResponse.fail(e.getMessage());
        }

        logger.info("处理腾讯支付平台的订单支付");
        // 订单编号
        String orderId = result.getOutTradeNo();
        // TODO 修改订单状态
        // 生成取件码
        int day = LocalDate.now().getDayOfMonth();
        String prefix = day < 10 ? "0"+day : String.valueOf(day);
        String pickupCode = prefix + orderId.substring(10,14);
        Map<String, Object> updateStatus = new HashMap<>(4);
        updateStatus.put("status", 2);
        updateStatus.put("payTime", LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyy-MM-dd hh:mm:ss")));
        updateStatus.put("orderId",orderId);
        updateStatus.put("packupCode", pickupCode);
        int updateResult = wemallOrderMapper.updateByMap(updateStatus);

        String openId = result.getOpenid();
        String totalFee = BaseWxPayResult.fenToYuan(result.getTotalFee());
        // 支付成功通知
        logger.info("支付成功通知");
        Map<String, String> params = new HashMap<>(8);
        params.put("openId",openId);
        params.put("orderId", orderId);
        params.put("payMoney", totalFee);
        params.put("orderType", result.getTotalFee() >= 188*100 ? "送货上门订单" : "到店自取订单");
        params.put("tip", result.getTotalFee() >= 188*100 ? "送货上门订单" : "凭提货单号取货");
        notifyService.sendCheckSuccessMessage(params);

        logger.info("打印小票");
        // 打印小票  1: 送货上门
        WemallOrder wemallOrder = wemallOrderMapper.selectByOrderId(orderId);
        // 配送方式 1:送货上门
        String deliveryWay = "1";
        if(deliveryWay.equals(wemallOrder.getDeliveryWay())){
            WemallUserAddress address = wemallUserAddressMapper.selectByPrimaryKey(wemallOrder.getAddressId());
            List<WemallOrderDetail> orderDetails = wemallOrderDetailMapper.selectByOrderId(orderId);
            String content = PrintMouldUtil.getContent(wemallOrder, address, orderDetails, address.getMobile());
            printService.print(content);
        } else{
            List<WemallOrderDetail> orderDetails = wemallOrderDetailMapper.selectByOrderId(orderId);
            String content = PrintMouldUtil.getContent(wemallOrder, null, orderDetails, null);
            printService.print(content);
        }
        // TODO 随机发放红包*

        return "<xml>\n" +
                "  <return_code><![CDATA[SUCCESS]]></return_code>\n" +
                "  <return_msg><![CDATA[OK]]></return_msg>\n" +
                "</xml>";
    }
}
