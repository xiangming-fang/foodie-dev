package indi.xm.vo;

/**
 * @ProjectName: foodie-dev
 * @Package: indi.xm.vo
 * @ClassName: CommentLevelCounts
 * @Author: albert.fang
 * @Description: 评价等级数量
 * @Date: 2021/10/14 15:14
 */
public class CommentLevelCountVO {

    // 好、中、坏总数
    public Integer totalCounts;

    public Integer goodCounts;

    public Integer normalCounts;

    public Integer badCounts;

    public Integer getTotalCounts() {
        return totalCounts;
    }

    public void setTotalCounts(Integer totalCounts) {
        this.totalCounts = totalCounts;
    }

    public Integer getGoodCounts() {
        return goodCounts;
    }

    public void setGoodCounts(Integer goodCounts) {
        this.goodCounts = goodCounts;
    }

    public Integer getNormalCounts() {
        return normalCounts;
    }

    public void setNormalCounts(Integer normalCounts) {
        this.normalCounts = normalCounts;
    }

    public Integer getBadCounts() {
        return badCounts;
    }

    public void setBadCounts(Integer badCounts) {
        this.badCounts = badCounts;
    }
}
