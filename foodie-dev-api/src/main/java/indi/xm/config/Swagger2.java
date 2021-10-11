package indi.xm.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.Contact;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

/**
 * @ProjectName: foodie-dev
 * @Package: indi.xm.config
 * @ClassName: Swagger2
 * @Author: albert.fang
 * @Description: Swagger2配置
 * @Date: 2021/10/11 15:13
 */
@Configuration
@EnableSwagger2 // 开启Swagger2
public class Swagger2 {

    // swagger 访问原访问地址：http://localhost:8088/swagger-ui.html
    // swagger 访问原访问地址：http://localhost:8088/doc.html

    // 配置swagger2 核心配置 docket
    @Bean
    public Docket createRestApi(){
        return new Docket(DocumentationType.SWAGGER_2)
                .apiInfo(apiInfo()) // 用于定义api文档汇总信息
                .select()
                .apis(RequestHandlerSelectors.basePackage("indi.xm.controller")) // 指定controller包
                .paths(PathSelectors.any()) // 所有controller
                .build();
    }

    private ApiInfo apiInfo(){
        return new ApiInfoBuilder()
                .title("架构师直通车 电商平台api") // 文档页标题
                .contact(new Contact("xm","https://www.xm.com","fxm970829@qq.com")) // 联系人信息
                .description("架构师直通车api文档") // 详细信息
                .version("1.0.1") // 文档版本号
                .termsOfServiceUrl("https://www.xm.com") // 网站信息
                .build();
    }
}
