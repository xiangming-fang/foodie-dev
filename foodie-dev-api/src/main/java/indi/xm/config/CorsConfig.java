package indi.xm.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;
import org.springframework.web.filter.CorsFilter;

/**
 * @ProjectName: foodie-dev
 * @Package: indi.xm.config
 * @ClassName: CorsConfig
 * @Author: albert.fang
 * @Description: 解决前后端联调跨域问题
 * @Date: 2021/10/11 17:44
 */
@Configuration
public class CorsConfig {

    public CorsConfig() {
    }

    @Bean
    public CorsFilter corsFilter(){
        // 1、添加cors配置信息
        CorsConfiguration config = new CorsConfiguration();
        config.addAllowedOrigin("http://localhost:8080");

        // 设置是否发送cookie信息
        config.setAllowCredentials(true);

        // 设置允许请求的方式
        config.addAllowedMethod("*");

        // 设置允许的header
        config.addAllowedHeader("*");

        // 2、为url添加映射路径
        UrlBasedCorsConfigurationSource corsSource = new UrlBasedCorsConfigurationSource();
        corsSource.registerCorsConfiguration("/**",config);

        // 3、返回重新定义好的 corsSource
        return new CorsFilter(corsSource);
    }

}
