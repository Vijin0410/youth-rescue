package com.rescue.framework.security;

import io.jsonwebtoken.Claims;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;
import org.springframework.web.servlet.HandlerInterceptor;

/**
 * Token Interceptor
 */
@Component
public class TokenInterceptor implements HandlerInterceptor {

    @Autowired
    private JwtUtils jwtUtils;

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        String token = request.getHeader("Authorization");
        if (StringUtils.hasText(token) && token.startsWith("Bearer ")) {
            token = token.substring(7);
            if (jwtUtils.validateToken(token)) {
                Claims claims = jwtUtils.getClaims(token);
                String username = claims.getSubject();
                // Assuming "userId" is stored in claims
                Long userId = claims.get("userId", Long.class);
                
                CurrentUserHolder.setUsername(username);
                if (userId != null) {
                    CurrentUserHolder.setUserId(userId);
                }
                return true;
            }
        }
        
        // Return 401 Unauthorized if token is missing or invalid
        response.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
        return false;
    }

    @Override
    public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex) throws Exception {
        CurrentUserHolder.clear();
    }
}
