package com.wang.filter;


import javax.servlet.*;
import java.io.IOException;

/**
 * @描述
 * @参数 $
 * @返回值 $
 * @创建人 chenjie
 * @创建时间 $
 * @修改人和其它信息
 */
public class CharacterEncoding implements Filter {
    @Override
    public void init(FilterConfig filterConfig) throws ServletException {

    }

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        servletRequest.setCharacterEncoding("utf-8");
        servletResponse.setCharacterEncoding("utf-8");
        servletResponse.setContentType("text/html;charset=utf-8");

        filterChain.doFilter(servletRequest,servletResponse);
    }

    @Override
    public void destroy() {

    }
}
