package indi.xm;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * @ProjectName: foodie-dev
 * @Package: indi.xm
 * @ClassName: Application
 * @Author: albert.fang
 * @Description: api启动类
 * @Date: 2021/9/13 15:38
 */
@SpringBootApplication
// 扫描 mybatis 通用 mapper 所在的包
//@MapperScan(basePackages = "indi.xm.mapper")
public class Application {

    public static void main(String[] args) {
        SpringApplication.run(Application.class,args);
    }
}
