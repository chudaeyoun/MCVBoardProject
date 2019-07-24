package com.multicampus.controller.common;

import java.io.IOException;
import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;

public class CharacterEncodingFilter implements Filter {
	private String boardEncoding;

    public CharacterEncodingFilter() {
//    	System.out.println("===> CharacterEncodingFilter »ý¼º");
    }

	public void destroy() {}

	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
		request.setCharacterEncoding(boardEncoding);		
		chain.doFilter(request, response);
	}

	public void init(FilterConfig fConfig) throws ServletException {
		boardEncoding = fConfig.getInitParameter("encoding");
	}

}
