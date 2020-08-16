package com.jungle.wemall.db.dto;

/**
 * @description: 奖品
 * @author: jungle
 * @date: 2020-06-04 00:24
 */
public class Prize {
    private Integer count;
    private Integer awardId;
    private String grade;

    public Integer getCount() {
        return count;
    }

    public void setCount(Integer count) {
        this.count = count;
    }

    public Integer getAwardId() {
        return awardId;
    }

    public void setAwardId(Integer awardId) {
        this.awardId = awardId;
    }

    public String getGrade() {
        return grade;
    }

    public void setGrade(String grade) {
        this.grade = grade;
    }

    @Override
    public String toString() {
        return "Prize{" +
                "count=" + count +
                ", awardId=" + awardId +
                ", grade='" + grade + '\'' +
                '}';
    }
}
