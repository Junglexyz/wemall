package com.jungle.wemall.admin.controller;

import com.jungle.wemall.admin.service.excel.EasyExcelWrite;
import com.jungle.wemall.common.notify.NotifyService;
import com.jungle.wemall.common.util.ResponseUtil;
import com.jungle.wemall.db.pojo.WemallOrder;
import com.jungle.wemall.db.pojo.WemallUser;
import com.jungle.wemall.db.service.WemallOrderService;
import com.jungle.wemall.db.service.WemallUserService;
import com.jungle.wemall.db.util.FastJsonUtil;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.transaction.interceptor.TransactionAspectSupport;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletResponse;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @description: 订单处理
 * @author: jungle
 * @date: 2020-02-28 01:47
 */
@RestController
@RequestMapping("/admin/order")
public class AdminOrderController {
    private final Log logger = LogFactory.getLog(AdminOrderController.class);
    @Autowired
    private WemallOrderService wemallOrderService;
    @Autowired
    private WemallUserService wemallUserService;
    @Autowired
    private NotifyService notifyService;

    @PostMapping("/list")
    public Object listOrders(@RequestBody String body){
        Map order = FastJsonUtil.getMap(body, "order");
        Object result = wemallOrderService.listOrder(order);
        if(result != null){
            return ResponseUtil.ok(result);
        }
        return ResponseUtil.fail();
    }

    @PostMapping("/update")
    @Transactional(rollbackFor=Exception.class)
    public Object updateOrder(@RequestBody WemallOrder order){
        order.setDeliverTime(new Date());
        int result = wemallOrderService.updateOrder(order);
        // 发货通知
        if(result > 0){
            WemallUser user = wemallUserService.findUserById(order.getUserId());
            String send = notifyService.sendDeliveryMessage(user.getOpenId(), order.getOrderId(), order.getPackupCode(), order.getDeliveryWay());
            // 发货成功模板消息发送成功返回的状态吗码 0: 发送成功
            String errCode = "0";
            // 返回结果的键名
            String resultCodeKey = "errcode";
            if(errCode.equals(FastJsonUtil.getString(send, resultCodeKey))){
                return ResponseUtil.ok();
            } else {
                // 手动回滚
                TransactionAspectSupport.currentTransactionStatus().setRollbackOnly();
            }
        }
        return ResponseUtil.fail();
    }

    @PostMapping("/sum")
    public Object sumOrder(@RequestBody String body){
        Map order = FastJsonUtil.getMap(body, "order");
        List<Map<String, Object>> maps = wemallOrderService.sumOrder(order);
        if(maps.size() > 0){
            return ResponseUtil.okList(maps);
        }
        return ResponseUtil.fail();
    }

    @GetMapping("/export")
    public void exportOrder(HttpServletResponse response){
        /*EasyExcelWrite easyExcelWrite = new EasyExcelWrite();
        easyExcelWrite.downLoadExcel("order.xlsx", response);*/
    }

    @GetMapping("/exportSum")
    public void exportSumOrder(HttpServletResponse response, @RequestParam(value="createTime") String createTime, @RequestParam(value="endTime") String endTime){
        Map<String, Object> order = new HashMap<>(4);
        order.put("createTime", createTime);
        order.put("endTime", endTime);
        List<Map<String, Object>> listSum = wemallOrderService.sumOrder(order);
        EasyExcelWrite easyExcelWrite = new EasyExcelWrite();
        easyExcelWrite.downLoadExcel("order_sum"+createTime+"_"+endTime+".xlsx",listSum, response);
    }
}
