package com.multicampus.controller.common;

import java.io.IOException;
import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;

public class TimeCheckFilter implements Filter {
   
    public TimeCheckFilter() {
//        System.out.println("===> TimeCheckFilter 积己");
    }   

	public void init(FilterConfig fConfig) throws ServletException {
//		System.out.println("===> init 积己");
	}

	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
//		System.out.println("===> doFilter 积己");
		long start = System.currentTimeMillis();
		
		chain.doFilter(request, response);
		
		long end = System.currentTimeMillis();
		System.out.println("辑喉复 荐青俊 吧赴 矫埃 : " + (end - start) + "(ms)檬");
	}

	public void destroy() {
//		System.out.println("===> destroy 积己");
	}
}
