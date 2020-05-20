package com.jungle.wemall.wx.controller;

import com.jungle.wemall.common.util.FastJsonUtil;
import com.jungle.wemall.common.util.ResponseUtil;
import com.jungle.wemall.db.pojo.WemallWithdrawRecord;
import com.jungle.wemall.db.service.WemallWithdrawRecordService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * @description: 提现记录
 * @author: jungle
 * @date: 2020-03-16 18:55
 */
@RestController
@RequestMapping("/wx/withdraw")
public class WxWithdrawRecordController {
    @Autowired
    private WemallWithdrawRecordService wemallWithdrawRecordService;

    @PostMapping("/list")
    public Object listWithdrawRecord(@RequestBody String body){
        Integer userId = FastJsonUtil.getInteger(body, "userId");
        List<WemallWithdrawRecord> wemallWithdrawRecord = wemallWithdrawRecordService.listByUserId(userId);
        if(wemallWithdrawRecord != null){
            return ResponseUtil.okList(wemallWithdrawRecord);
        }
        return ResponseUtil.ok();
    }
}
