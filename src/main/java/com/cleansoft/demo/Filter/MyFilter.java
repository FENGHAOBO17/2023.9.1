package com.cleansoft.demo.Filter;



import java.io.IOException;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;

import com.cleansoft.demo.mapper.UserMapper;
import com.cleansoft.demo.util.JWTUtil;

import jakarta.servlet.Filter;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;


public class MyFilter implements Filter {
	private static final Logger logger = LoggerFactory.getLogger(MyFilter.class);
	@Autowired
	private JWTUtil jwtUtil;
	@Autowired
	private UserMapper userMapper;

	@Override
	public void doFilter(jakarta.servlet.ServletRequest request, jakarta.servlet.ServletResponse response,
			jakarta.servlet.FilterChain chain) throws IOException, jakarta.servlet.ServletException {
		HttpServletRequest httpRequest = (HttpServletRequest) request;
		String token = httpRequest.getHeader("Authorization");
		String account = httpRequest.getParameter("account");
		String password = httpRequest.getParameter("password");
//		String dbToken = userMapper.findToken(account);

		logger.info("token:"+token);
		logger.info("account:"+account);
		logger.info("password:"+password);

		HttpServletRequest req = (HttpServletRequest) request;
        String uri = req.getRequestURI();
        logger.info("uri:"+uri);
		if (!uri.equals("/users/loginUsers") && !uri.endsWith("/users/addUsers")) {
            // 拦截除了 /#/ 和 /#/register 以外的路径
			logger.info("filterスタート");
			if ((token != null && !token.isEmpty()) && token.startsWith("Bearer")) {
	            // 去除 "Bearer " 前缀
	            token = token.substring(7);
//	            if(dbToken.equals(token)) {
	            	logger.info("filter採算");	
	            	if (jwtUtil.verify(token,account,password)){
	            		// 令牌验证成功，允许请求继续处理
	            		chain.doFilter(request, response);
	            		logger.info("filter成功");
	            		return;
//	            	}
	            }
	        }else {
	        	logger.info("tokenチェック失敗");
	        // 令牌验证失败，返回错误响应
	        HttpServletResponse httpResponse = (HttpServletResponse) response;
	        httpResponse.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
	        }
            chain.doFilter(request, response);
            return;
        }
        // 如果需要进行自定义处理，请在此处添加代码
        chain.doFilter(request, response);
		
	}
}