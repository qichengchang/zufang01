package com.sunrui.zufang;

import com.baomidou.mybatisplus.extension.plugins.PaginationInterceptor;
import com.baomidou.mybatisplus.extension.plugins.pagination.optimize.JsqlParserCountOptimize;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.jdbc.DataSourceBuilder;
import org.springframework.context.annotation.Bean;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

import javax.sql.DataSource;

@SpringBootApplication
@EnableSwagger2
@MapperScan(basePackages ="com.sunrui.zufang.dao")
public class ZufangApplication {

	public static void main(String[] args) {
		SpringApplication.run(ZufangApplication.class, args);
	}

	@Bean
	@ConfigurationProperties(prefix = "spring.datasource.hikari")
	public DataSource dataSource() {
		return DataSourceBuilder.create().build();
	}

	@Bean
	public PaginationInterceptor paginationInterceptor() {
		PaginationInterceptor  paginationInterceptor = new PaginationInterceptor();
		paginationInterceptor.setDialectType("mysql");
		return paginationInterceptor;
	}

}
