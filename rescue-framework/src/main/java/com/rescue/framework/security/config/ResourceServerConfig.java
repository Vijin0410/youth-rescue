package com.rescue.framework.security.config;


import com.rescue.common.constant.SecurityConstants;
import com.rescue.framework.security.exception.MyAccessDeniedHandler;
import com.rescue.framework.security.exception.MyAuthenticationEntryPoint;
import com.rescue.framework.security.handle.DynamicUrlMatcher;
import jakarta.annotation.Resource;
import lombok.extern.slf4j.Slf4j;
import org.apache.logging.log4j.util.Strings;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.DependsOn;
import org.springframework.core.convert.converter.Converter;
import org.springframework.security.authentication.AbstractAuthenticationToken;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityCustomizer;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.oauth2.jwt.Jwt;
import org.springframework.security.oauth2.server.resource.authentication.JwtAuthenticationConverter;
import org.springframework.security.oauth2.server.resource.authentication.JwtAuthenticationProvider;
import org.springframework.security.oauth2.server.resource.authentication.JwtGrantedAuthoritiesConverter;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.servlet.util.matcher.MvcRequestMatcher;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;
import org.springframework.security.web.util.matcher.RequestMatcher;
import org.springframework.web.servlet.handler.HandlerMappingIntrospector;

import java.util.Arrays;

/**
 * 资源服务器配置
 *
 * @author lenvo
 * @since 3.0.0
 */
@Configuration
@EnableWebSecurity
@Slf4j
@DependsOn("dynamicUrlMatcher")
public class ResourceServerConfig {
  @Resource
  MyAccessDeniedHandler accessDeniedHandler;
  @Resource
  MyAuthenticationEntryPoint authenticationEntryPoint;
  @Resource
  DynamicUrlMatcher dynamicUrlMatcher;

  private SecurityFilterChain securityFilterChain;

  @Bean
  public SecurityFilterChain securityFilterChain(HttpSecurity http, HandlerMappingIntrospector introspector) throws Exception {
    return createSecurityFilterChain(http, introspector);
  }

  private String[] getWhiteListUrls() {
    // 从数据库或缓存获取白名单URL
    return dynamicUrlMatcher.getWhiteListUrls().toArray(new String[0]);
  }

  public SecurityFilterChain createSecurityFilterChain(HttpSecurity http, HandlerMappingIntrospector introspector) throws Exception {

    MvcRequestMatcher.Builder mvcMatcherBuilder = new MvcRequestMatcher.Builder(introspector);
    // 将白名单 URL 转换为 MvcRequestMatcher 数组
    RequestMatcher[] whiteListMatchers = Arrays.stream(getWhiteListUrls()).map(mvcMatcherBuilder::pattern).toArray(
        RequestMatcher[]::new);

    http.authorizeHttpRequests(request -> request.requestMatchers(whiteListMatchers).permitAll().anyRequest().authenticated()).csrf(
        AbstractHttpConfigurer::disable);

    http.oauth2ResourceServer(
        resourceServerConfigurer ->
            resourceServerConfigurer
                .jwt(jwtConfigurer -> jwtAuthenticationConverter())
                .authenticationEntryPoint(authenticationEntryPoint)
                .accessDeniedHandler(accessDeniedHandler));
    this.securityFilterChain = http.build();
    return this.securityFilterChain;
  }

  /** 不走过滤器链的放行配置 */
  @Bean
  public WebSecurityCustomizer webSecurityCustomizer() {
    return (web) ->
        web.ignoring()
            .requestMatchers(
                AntPathRequestMatcher.antMatcher("/webjars/**"),
                AntPathRequestMatcher.antMatcher("/doc.html"),
                AntPathRequestMatcher.antMatcher("/swagger-resources/**"),
                AntPathRequestMatcher.antMatcher("/v3/api-docs/**"),
                AntPathRequestMatcher.antMatcher("/swagger-ui/**"),
                AntPathRequestMatcher.antMatcher("/websocket/**"),
                AntPathRequestMatcher.antMatcher("/iot/**"));
  }

  @Bean
  MvcRequestMatcher.Builder mvc(HandlerMappingIntrospector introspector) {
    return new MvcRequestMatcher.Builder(introspector);
  }

  /**
   * 自定义JWT Converter
   *
   * @return Converter
   * @see JwtAuthenticationProvider#setJwtAuthenticationConverter(Converter)
   */
  @Bean
  public Converter<Jwt, ? extends AbstractAuthenticationToken> jwtAuthenticationConverter() {
    JwtGrantedAuthoritiesConverter jwtGrantedAuthoritiesConverter =
        new JwtGrantedAuthoritiesConverter();
    jwtGrantedAuthoritiesConverter.setAuthorityPrefix(Strings.EMPTY);
    jwtGrantedAuthoritiesConverter.setAuthoritiesClaimName(
        SecurityConstants.AUTHORITIES_CLAIM_NAME_KEY);

    JwtAuthenticationConverter jwtAuthenticationConverter = new JwtAuthenticationConverter();
    jwtAuthenticationConverter.setJwtGrantedAuthoritiesConverter(jwtGrantedAuthoritiesConverter);
    return jwtAuthenticationConverter;
  }
}
