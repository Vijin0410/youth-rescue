package com.rescue.framework.config;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.module.SimpleModule;
import com.fasterxml.jackson.databind.ser.std.ToStringSerializer;
import com.rescue.common.result.PageResult;
import com.rescue.framework.deserializer.PageResultDeserializer;
import com.rescue.framework.security.TokenInterceptor;
import jakarta.annotation.Resource;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.converter.HttpMessageConverter;
import org.springframework.http.converter.json.MappingJackson2HttpMessageConverter;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import java.math.BigInteger;
import java.util.List;

/**
 * Web MVC Configuration
 */
@Slf4j
@Configuration
public class WebMvcConfig implements WebMvcConfigurer {

    @Resource
    private TokenInterceptor tokenInterceptor;

    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        registry.addInterceptor(tokenInterceptor)
                .addPathPatterns("/**")
                .excludePathPatterns(
                        "/auth/login",
                        "/auth/register",
                        "/doc.html",
                        "/webjars/**",
                        "/swagger-resources",
                        "/v3/api-docs/**"
                );
    }

    @Override
    public void addCorsMappings(CorsRegistry registry) {
        registry.addMapping("/**")
                .allowedOriginPatterns("*")
                .allowedMethods("GET", "POST", "PUT", "DELETE", "OPTIONS")
                .allowedHeaders("*")
                .allowCredentials(true)
                .maxAge(3600);
    }
    /**
     * 扩展消息转换器（使用extend，不是configure）
     */
    @Override
    public void extendMessageConverters(List<HttpMessageConverter<?>> converters) {
        log.info("扩展消息转换器...");

        for (HttpMessageConverter<?> converter : converters) {
            if (converter instanceof MappingJackson2HttpMessageConverter) {
                customizeJacksonConverter((MappingJackson2HttpMessageConverter) converter);
            }
        }
    }

    /**
     * 自定义Jackson转换器
     */
    private void customizeJacksonConverter(MappingJackson2HttpMessageConverter converter) {
        ObjectMapper objectMapper = converter.getObjectMapper();

        // 创建自定义模块
        SimpleModule simpleModule = new SimpleModule();

        // 1. Long/BigInteger转String（解决前端JS精度丢失）
        simpleModule.addSerializer(Long.class, ToStringSerializer.instance);
        simpleModule.addSerializer(Long.TYPE, ToStringSerializer.instance);
        simpleModule.addSerializer(BigInteger.class, ToStringSerializer.instance);

        // 2. 自定义PageResult反序列化
        simpleModule.addDeserializer(PageResult.class, new PageResultDeserializer(PageResult.class));

        // 3. 允许非引号字段名（如果需要）
        objectMapper.configure(JsonParser.Feature.ALLOW_UNQUOTED_FIELD_NAMES, true);

        // 4. 其他常用配置
        objectMapper.configure(JsonParser.Feature.ALLOW_SINGLE_QUOTES, true);
        objectMapper.configure(JsonParser.Feature.ALLOW_COMMENTS, true);

        // 注册模块
        objectMapper.registerModule(simpleModule);

        log.debug("Jackson转换器配置完成");
    }
}
