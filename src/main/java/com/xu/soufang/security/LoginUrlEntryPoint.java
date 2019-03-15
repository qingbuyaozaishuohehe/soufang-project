package com.xu.soufang.security;

import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.authentication.LoginUrlAuthenticationEntryPoint;
import org.springframework.util.AntPathMatcher;
import org.springframework.util.PathMatcher;
import org.springframework.web.servlet.config.annotation.PathMatchConfigurer;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.HashMap;
import java.util.Map;

/**
 * 基于角色的登陆入口控制器
 *
 * @author xuzhenqin
 * @date 2019/3/1
 */
public class LoginUrlEntryPoint extends LoginUrlAuthenticationEntryPoint {

    private PathMatcher pathMatch = new AntPathMatcher();

    private final Map<String,String> authEntryPointMap;

    public LoginUrlEntryPoint(String loginFormUrl) {
        super(loginFormUrl);
        authEntryPointMap = new HashMap<>();

        //普通用户登陆入口映射
        authEntryPointMap.put("/user/**","/user/login");

        authEntryPointMap.put("/admin/**","/admin/login");

    }

    /**
     * 根据请求跳转到制定的页面，父类是默认使用
     * @param request
     * @param response
     * @param exception
     * @return
     */
    @Override
    protected String determineUrlToUseForThisRequest(HttpServletRequest request, HttpServletResponse response, AuthenticationException exception) {
        String uri = request.getRequestURI().replace(request.getContextPath(),"");
        for (Map.Entry<String,String> authEntry:this.authEntryPointMap.entrySet()) {
            if (this.pathMatch.match(authEntry.getKey(),uri)){
                return authEntry.getValue();
            }
        }
        return super.determineUrlToUseForThisRequest(request, response, exception);
    }
}
