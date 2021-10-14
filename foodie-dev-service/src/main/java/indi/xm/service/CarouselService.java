package indi.xm.service;

import indi.xm.pojo.Carousel;

import java.util.List;

/**
 * @ProjectName: foodie-dev
 * @Package: indi.xm.service
 * @ClassName: CarouselService
 * @Author: albert.fang
 * @Description: 轮播图
 * @Date: 2021/10/14 10:14
 */
public interface CarouselService {

    /**
     * 查询所有轮播图列表
     *
     * @param isShow
     * @return
     */
    public List<Carousel> queryAll(Integer isShow);

}
