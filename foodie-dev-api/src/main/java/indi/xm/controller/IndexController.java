package indi.xm.controller;

import indi.xm.enums.YesOrNo;
import indi.xm.pojo.Carousel;
import indi.xm.service.CarouselService;
import indi.xm.utils.XMJSONResult;
import io.swagger.annotations.Api;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.util.List;

/**
 * @ProjectName: foodie-dev
 * @Package: indi.xm.controller
 * @ClassName: IndexController
 * @Author: albert.fang
 * @Description: 首页控制器
 * @Date: 2021/10/14 10:18
 */
@Api(value = "首页",tags = {"首页展示的相关接口"})
@RestController
@RequestMapping("index")
public class IndexController {

    @Resource
    private CarouselService carouselService;

    @GetMapping("/carousel")
    public XMJSONResult carousel(){
        List<Carousel> carousels = carouselService.queryAll(YesOrNo.YES.type);
        return XMJSONResult.ok(carousels);
    }

    /**
     * 首页分类展示需求
     * 1、第一次刷新主页查询大分类，渲染展示到首页
     * 2、如果鼠标上移到大分类，则加载其子分类内容，如果存在子分类，则不需要加载（懒加载模式）
     */
}