package com.jungle.wemall.db.service;

import com.jungle.wemall.db.dao.WemallAwardMapper;
import com.jungle.wemall.db.dao.WemallPrizeMapper;
import com.jungle.wemall.db.dto.Award;
import com.jungle.wemall.db.dto.Prize;
import com.jungle.wemall.db.pojo.WemallAward;
import com.jungle.wemall.db.pojo.WemallPrize;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

/**
 * @description: 抽奖设置
 * @author: jungle
 * @date: 2020-06-03 23:37
 */
@Service
public class WemallAwardService {
    @Resource
    private WemallAwardMapper awardMapper;
    @Resource
    private WemallPrizeMapper prizeMapper;

    /**
     * 随机生成奖品
     * @param award
     * @param prizes
     * @return
     */
    public int create(WemallAward award, List<WemallPrize> prizes){
        int total = 0;
        for(WemallPrize prize: prizes){
            total += prize.getCount();
        }
        Object[] objs = generateAward(prizes,total);
        award.setCount(total);
        awardMapper.insertSelective(award);
        for(Object obj: objs){
            ((WemallPrize)obj).setAwardId(award.getId());
            prizeMapper.insertSelective(((WemallPrize)obj));
        }
        // 持久化
        return 0;
    }
    private Object[] generateAward(List<WemallPrize> prizes, int total){
        // 生成奖项
        // 奖品总数
        Object[] objs = new Object[total];
        for(int i = 0; i < prizes.size(); i++){
            while(prizes.get(i).getCount() > 0){
                Random random = new Random();
                int index = (int)(10000*random.nextDouble())%total;
                prizes.get(i).setCount(prizes.get(i).getCount() - 1);
                if(objs[index] == null){
                    objs[index] = prizes.get(i);
                    continue;
                }
                while((index < objs.length - 1) && objs[index] != null){
                    index ++;
                    if(objs[index] == null){
                        objs[index] = prizes.get(i);
                        break;
                    }
                }
                while((index > 0) && objs[index] != null){
                    index --;
                    if(objs[index] == null){
                        objs[index] = prizes.get(i);
                        break;
                    }
                }
            }
        }
        for(int i = 0; i < objs.length; i++){
            System.out.println(objs[i]);
            // 持久化
        }
        for(WemallPrize prize: prizes){
            System.out.println(prize.getCount());
        }
        return objs;
    }

    /**
     * 查询活动状态
     * @return
     */
    public WemallAward select() {
        return awardMapper.selectOne();
    }

    /**
     * 更新奖品状态
     * @param prize
     * @return
     */
    public int updatePrize(WemallPrize prize) {
        prize.setUsed(true);
        return prizeMapper.updateNoUsed(prize);
    }

    /**
     * 剩余奖品数量减一
     * @param awardId
     * @return
     */
    public int reduceAward(Integer awardId) {
        return awardMapper.reduceOne(awardId);
    }

    /**
     * 抽取奖品
     * @param awardId
     * @return
     */
    public WemallPrize getPrize(Integer awardId) {
        return prizeMapper.selectOne(awardId);
    }

    /**
     * 获取抽奖活动信息
     * @return
     */
    public List<WemallAward> list() {
        return awardMapper.list();
    }

    public List<WemallPrize> listPrize(Integer awardId) {
        return prizeMapper.list(awardId);
    }

    public int delete(Integer awardId) {
       int result =  awardMapper.deleteByPrimaryKey(awardId);
       int re = prizeMapper.deleteByAward(awardId);
       return 1;
    }
}
