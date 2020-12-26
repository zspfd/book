package com.atguigu.filter;


import com.atguigu.utils.JdbcUtils;

import javax.servlet.*;
import java.io.IOException;

public class TransactionFilter implements Filter {


    @Override
    public void init(FilterConfig filterConfig) throws ServletException {

    }

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {

        try {
            filterChain.doFilter(servletRequest, servletResponse);
            JdbcUtils.commitAndClose(); //提交事务
        } catch (Exception e) {
            JdbcUtils.rollbackAndClose(); // 回滚事务
            e.printStackTrace();
            throw new RuntimeException(e); // 把错误抛给管理器，为用户调用提示页面
        }
    }

    @Override
    public void destroy() {

    }
}
