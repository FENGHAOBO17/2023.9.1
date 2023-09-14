package com.cleansoft.demo.Filter;



import java.io.IOException;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;

import com.cleansoft.demo.util.JWTUtil;

import jakarta.servlet.Filter;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;


public class MyFilter implements Filter {
	private static final Logger logger = LoggerFactory.getLogger(MyFilter.class);
	@Autowired
	private JWTUtil jwtUtil;

	@Override
	public void doFilter(jakarta.servlet.ServletRequest request, jakarta.servlet.ServletResponse response,
			jakarta.servlet.FilterChain chain) throws IOException, jakarta.servlet.ServletException {
		HttpServletRequest httpRequest = (HttpServletRequest) request;
		String token = httpRequest.getHeader("Authorization");
		String account = httpRequest.getParameter("account");
		String password = httpRequest.getParameter("password");
		logger.info("filterスタート");
		logger.info("token:"+token);
		logger.info("account:"+account);
		logger.info("password:"+password);

		if (!(token == null || token.isEmpty()) && token.startsWith("Bearer")) {
            // 去除 "Bearer " 前缀
            token = token.substring(7);
            logger.info("filter採算");	
            if (jwtUtil.verify(token,account,password)) {
                // 令牌验证成功，允许请求继续处理
                chain.doFilter(request, response);
                logger.info("filter成功");
                return;
            }
        }

        // 令牌验证失败，返回错误响应
        HttpServletResponse httpResponse = (HttpServletResponse) response;
        httpResponse.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
	}
}