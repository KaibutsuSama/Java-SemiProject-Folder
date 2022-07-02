package com.kaibutsusama.reggie.filter;

import com.alibaba.fastjson.JSON;
import com.kaibutsusama.reggie.common.BaseContext;
import com.kaibutsusama.reggie.common.R;
import lombok.extern.slf4j.Slf4j;
import org.springframework.util.AntPathMatcher;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * @author KaibutsuSama
 * @date 2022/6/28
 */
@Slf4j
@WebFilter(filterName = "loginCheckFilter",urlPatterns = "/*")
public class LoginCheckFilter implements Filter {

    //路径匹配器,支持通配符
    public static final AntPathMatcher PATH_MATCHER = new AntPathMatcher();

    @Override
    public void doFilter (ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        HttpServletRequest request = (HttpServletRequest) servletRequest;
        HttpServletResponse response = (HttpServletResponse) servletResponse;

        //1. 获取本次请求的URI
        String requestURI = request.getRequestURI();
        log.info("本次拦截到请求的是"+requestURI);
        //1.1 定义不拦截的路径
        String urls[] = new String[]{
                "/employee/login",
                "/employee/logout",
                "/backend/**",
                "/front/**",
                "/common/**"
        };
        //2. 判断本次请求是否需要处理
        boolean check = check(urls, requestURI);

        //3. 如果不需要处理，放行
        if(check){
            log.info("本次请求{}不需要处理"+requestURI);
            filterChain.doFilter(request,response);
            return;
        }
        //4. 如果需要处理，则判断用户是否已经登录
        if (request.getSession().getAttribute("employee")!=null){
            log.info("用户已登录,用户的id为{}",request.getSession().getAttribute("employee"));

            Long empId = (Long) request.getSession().getAttribute("employee");
            BaseContext.setCurrentId(empId);

            filterChain.doFilter(request,response);
            return;
        }
        //5. 没登录则返回未登录结果,通过输出流方式向客户端页面相应数据
        log.info("用户未登录");
        response.getWriter().write(JSON.toJSONString(R.error("NOTLOGIN")));
        return;
    }

    public boolean check(String[] urls,String requestURI){
        for (String url : urls) {
            boolean match = PATH_MATCHER.match(url, requestURI);
            if (match){
                return true;
            }
        }
        return false;
    }
}
