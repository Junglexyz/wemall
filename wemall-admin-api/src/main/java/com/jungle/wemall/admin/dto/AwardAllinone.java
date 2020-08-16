package com.jungle.wemall.admin.dto;

import com.jungle.wemall.db.dto.Award;
import com.jungle.wemall.db.dto.Prize;
import com.jungle.wemall.db.pojo.WemallAward;
import com.jungle.wemall.db.pojo.WemallPrize;

import java.util.List;

/**
 * @description:
 * @author: jungle
 * @date: 2020-06-04 00:26
 */
public class AwardAllinone {
    private WemallAward award;
    private List<WemallPrize> prizes;

    public WemallAward getAward() {
        return award;
    }

    public void setAward(WemallAward award) {
        this.award = award;
    }

    public List<WemallPrize> getPrizes() {
        return prizes;
    }

    public void setPrizes(List<WemallPrize> prizes) {
        this.prizes = prizes;
    }
}
