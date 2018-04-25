package com.example.xy_restaurant;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.web.servlet.support.SpringBootServletInitializer;

@MapperScan("com.example.xy_restaurant.mapper*")
@SpringBootApplication
public class XyRestaurantApplication extends  SpringBootServletInitializer {

    @Override
    protected SpringApplicationBuilder configure(SpringApplicationBuilder builder) {
        // 注意这里要指向原先用main方法执行的Application启动类
        return builder.sources(XyRestaurantApplication.class);
    }
    public static void main(String[] args) {
        SpringApplication.run(XyRestaurantApplication.class, args);

        System.out.println("ヾ(◍°∇°◍)ﾉﾞ    湘乐餐厅管理系统启动成功      ヾ(◍°∇°◍)ﾉﾞ\n" +
                " ______                    _   ______            \n" +
                "|_   _ \\                  / |_|_   _ `.          \n" +
                "  | |_) |   .--.    .--. `| |-' | | `. \\  .--.   \n" +
                "  |  __'. / .'`\\ \\/ .'`\\ \\| |   | |  | |/ .'`\\ \\ \n" +
                " _| |__) || \\__. || \\__. || |, _| |_.' /| \\__. | \n" +
                "|_______/  '.__.'  '.__.' \\__/|______.'  '.__.'  ");
    }
}
