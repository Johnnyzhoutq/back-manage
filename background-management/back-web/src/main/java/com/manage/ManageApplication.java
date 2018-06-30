package com.manage;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.web.support.SpringBootServletInitializer;
import org.springframework.transaction.annotation.EnableTransactionManagement;

/**
 * 启动程序
 * 
 * @author ruoyi
 */
@SpringBootApplication
@EnableTransactionManagement
@MapperScan("com.manage.project.**.dao")
public class ManageApplication extends SpringBootServletInitializer
{
    public static void main(String[] args)
    {
        SpringApplication.run(ManageApplication.class, args);
    }
    
    @Override
    protected SpringApplicationBuilder configure(SpringApplicationBuilder application)
    {
        return application.sources(ManageApplication.class);
    }
}