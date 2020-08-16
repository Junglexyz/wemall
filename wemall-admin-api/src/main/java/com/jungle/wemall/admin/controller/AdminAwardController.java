package com.jungle.wemall.admin.controller;

import com.jungle.wemall.admin.dto.AwardAllinone;
import com.jungle.wemall.admin.util.ResponseUtil;
import com.jungle.wemall.common.util.FastJsonUtil;
import com.jungle.wemall.db.pojo.WemallAward;
import com.jungle.wemall.db.pojo.WemallPrize;
import com.jungle.wemall.db.service.WemallAwardService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

/**
 * @description: 奖品设置
 * @author: jungle
 * @date: 2020-06-03 23:26
 */
@RestController
@RequestMapping("/admin/award")
public class AdminAwardController {
    @Autowired
    private WemallAwardService awardService;
    @PostMapping("/create")
    public Object loginByUserName(@RequestBody AwardAllinone awardAllinone){
        int result = awardService.create(awardAllinone.getAward(),awardAllinone.getPrizes());
        if(result > 0){
            return ResponseUtil.ok();
        }
        return ResponseUtil.fail();
    }

    @GetMapping("/list")
    public Object list(){
        List<WemallAward> list = awardService.list();
        if(list.size() > 0){
            return ResponseUtil.okList(list);
        }
        return ResponseUtil.fail();
    }

    @PostMapping("/prizes")
    public Object prizes(@RequestBody String body){
        Integer awardId = FastJsonUtil.getInteger(body, "awardId");
        List<WemallPrize> list = awardService.listPrize(awardId);
        if(list.size() > 0){
            return ResponseUtil.okList(list);
        }
        return ResponseUtil.fail();
    }

    @PostMapping("/delete")
    public Object deleteAward(@RequestBody String body){
        Integer awardId = FastJsonUtil.getInteger(body, "awardId");
        int result = awardService.delete(awardId);
        if(result > 0){
            return ResponseUtil.ok();
        }
        return ResponseUtil.fail();
    }
}
