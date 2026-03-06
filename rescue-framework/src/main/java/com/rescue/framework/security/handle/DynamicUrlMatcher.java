package com.rescue.framework.security.handle;

import cn.hutool.extra.spring.SpringUtil;
import com.rescue.common.constant.RabbitConstants;
import com.rescue.framework.redis.service.RedisService;
import jakarta.annotation.PostConstruct;
import jakarta.annotation.Resource;
import lombok.Setter;
import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.rabbit.annotation.Queue;
import org.springframework.amqp.rabbit.annotation.QueueBinding;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.security.web.DefaultSecurityFilterChain;
import org.springframework.security.web.FilterChainProxy;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;
import org.springframework.stereotype.Component;

import java.lang.reflect.Field;
import java.util.HashSet;
import java.util.List;
import java.util.Optional;
import java.util.Set;

@Slf4j
@Component
@ConfigurationProperties(prefix = "security")
public class DynamicUrlMatcher {
    private Set<String> whitePathListUrls = new HashSet<>();
    @Resource
    RedisService redisService;

    // url黑名单 redis key
    private static final String URL_BLACKLIST_KEY = "wlip:url_blacklist";

    @Setter
    private Set<String> whitelistPaths;

    @PostConstruct
    public void init() {
        refreshWhiteList();
    }

    public void refreshWhiteList() {
        // 从 Redis 中加载白名单路径列表
        Set refererWhitelist = Optional.ofNullable(redisService.getCacheSet(URL_BLACKLIST_KEY)).orElse(new HashSet<>());
        // 配置文件中的白名单路径列表
        refererWhitelist.addAll(whitelistPaths);
        this.whitePathListUrls = refererWhitelist;
    }

    public Set<String> getWhiteListUrls() {
        return whitePathListUrls;
    }

    /**
     * 监听白名单刷新事件
     *
     * @param invalidPathList
     */
    @RabbitListener(bindings = @QueueBinding(value = @Queue(name = RabbitConstants.QUEUE_FANOUT_URLS + "-" + "${server.port}"),
        exchange = @org.springframework.amqp.rabbit.annotation.Exchange(value = RabbitConstants.FANOUT_EXCHANGE_URLS_NAME,
            type = org.springframework.amqp.core.ExchangeTypes.FANOUT)))
    public void listenerUrlBlacklistChange(Set<String> invalidPathList) {
        refreshWhiteList();
        ignoreUrlAuthentication(invalidPathList);
    }

    public void ignoreUrlAuthentication(Set<String> invalidPathList) {
        log.info("监听到白名单刷新消息");
        FilterChainProxy obj = (FilterChainProxy)SpringUtil.getBean("springSecurityFilterChain");
        // 使用 getProperty 方法获取 filterChains 并进行类型转换，使用泛型通配符避免 unchecked 警告
        List<SecurityFilterChain> securityFilterChains = (List<SecurityFilterChain>) getProperty(obj, "filterChains");
        if (securityFilterChains == null) {
            return;
        }

        // 存储需要移除的元素
        java.util.ArrayList<SecurityFilterChain> toRemove = new java.util.ArrayList<>();

        // 遍历 securityFilterChains
        for (SecurityFilterChain securityFilterChain : securityFilterChains) {
            if (!(securityFilterChain instanceof DefaultSecurityFilterChain)) {
                continue;
            }
            DefaultSecurityFilterChain defaultSecurityFilterChain = (DefaultSecurityFilterChain) securityFilterChain;
            if (!(defaultSecurityFilterChain.getRequestMatcher() instanceof AntPathRequestMatcher)) {
                continue;
            }
            AntPathRequestMatcher requestMatcher = (AntPathRequestMatcher) defaultSecurityFilterChain.getRequestMatcher();
            String pattern = requestMatcher.getPattern();

            // 若白名单包含该路径，则从白名单中移除
            if (whitePathListUrls.contains(pattern)) {
                whitePathListUrls.remove(pattern);
            }

            // 若黑名单包含该路径，则将其添加到待移除列表
            if (invalidPathList.contains(pattern)) {
                toRemove.add(securityFilterChain);
            }
        }

        // 移除黑名单中的路径
        securityFilterChains.removeAll(toRemove);

        // 遍历白名单，将路径添加到 securityFilterChains 头部
        if (whitePathListUrls != null) {
            for (String url : whitePathListUrls) {
                securityFilterChains.add(0, new DefaultSecurityFilterChain(new AntPathRequestMatcher(url)));
            }
        }

    }

    private static Object getProperty(Object obj, String fieldName) {
        try {
            Field field = obj.getClass().getDeclaredField(fieldName);
            field.setAccessible(true);
            return field.get(obj);
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

}