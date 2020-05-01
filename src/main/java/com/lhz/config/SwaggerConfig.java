package com.lhz.config;

import com.github.xiaoymin.swaggerbootstrapui.annotations.EnableSwaggerBootstrapUI;
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
 * Created by Vi
 * Date on 2019/5/4 13:14
 */
@Configuration
@EnableSwagger2
@EnableSwaggerBootstrapUI
public class SwaggerConfig {

    /**
     * 配置文档生成器
     *
     * @return
     */
    @Bean
    public Docket createApiDoc() {
        return new Docket(DocumentationType.SWAGGER_2)
                .apiInfo(this.apiInfo())
                .select()
                .apis(RequestHandlerSelectors.basePackage("com.lhz.controller"))
                .paths(PathSelectors.any())
                .build();
    }

    /**
     * 配置文档标题、描述、api地址、作者、版本号等
     *
     * @return
     */
    private ApiInfo apiInfo() {
        return new ApiInfoBuilder()
                .title("API")
                .description("接口详细文档")
                .contact(new Contact("lhz", null, "xxxxxx@gmail.com"))
                .version("1.0")
                .build();
    }
}
