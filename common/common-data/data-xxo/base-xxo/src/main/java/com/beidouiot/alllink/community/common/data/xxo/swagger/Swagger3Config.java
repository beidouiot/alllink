package com.beidouiot.alllink.community.common.data.xxo.swagger;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import io.swagger.annotations.ApiOperation;
import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.Contact;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;

/**
 * 
 *
 * @Description Swagger3配置类
 * @author longww
 * @date 2021年4月11日
 */
@Configuration
public class Swagger3Config {

    @Bean
    public Docket createRestApi() {
        return new Docket(DocumentationType.OAS_30)
        		.pathMapping("/")
        		// 定义是否开启swagger，false为关闭，可以通过变量控制
                .enable(true)
                .apiInfo(apiInfo())
                .select()
                .apis(RequestHandlerSelectors.withMethodAnnotation(ApiOperation.class))
                .paths(PathSelectors.any())
                .build()
                .groupName("alllink");
    }

    private ApiInfo apiInfo() {
        return new ApiInfoBuilder()
                .title("Alllink Application Api Doc")
                .description("更多请咨询服务开发者车轱辘。")
                .contact(new Contact("车轱辘", "http://beidouiot.com", "3827291@qq.com"))
                .version("0.0.1")
                .termsOfServiceUrl("http://localhost:9999/")
                .license("Apache 2.0")
                .licenseUrl("https://www.apache.org/licenses/LICENSE-2.0.html")
                .build();
    }
}
