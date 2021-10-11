package indi.xm.controller;

import indi.xm.service.StuService;
import org.springframework.web.bind.annotation.*;
import springfox.documentation.annotations.ApiIgnore;

import javax.annotation.Resource;

/**
 * @ProjectName: foodie-dev
 * @Package: indi.xm.controller
 * @ClassName: StuFooController
 * @Author: albert.fang
 * @Description: 示例
 * @Date: 2021/10/8 14:24
 */
@RestController
// 忽略在Swagger显示
@ApiIgnore
public class StuFooController {

    @Resource
    private StuService stuService;

    @GetMapping("getStu")
    public Object getStu(int id){
        return stuService.getStuInfo(id);
    }

    @PostMapping("saveStu")
    public Object saveStu(){
        stuService.saveStu();
        return "save success";
    }

    @PutMapping("updateStu")
    public Object updateStu(int id){
        stuService.updateStu(id);
        return "update success";
    }

    @DeleteMapping("deleteStu")
    public Object deleteStu(int id){
        stuService.deleteStu(id);
        return "delete success";
    }
}
