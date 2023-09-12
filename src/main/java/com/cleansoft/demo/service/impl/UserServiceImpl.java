package com.cleansoft.demo.service.impl;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;

import com.cleansoft.demo.entity.ResponseEntity;
import com.cleansoft.demo.entity.User;
import com.cleansoft.demo.mapper.UserMapper;
import com.cleansoft.demo.service.UserService;
import com.cleansoft.demo.util.JWTUtil;

@Service
public class UserServiceImpl implements UserService {
	@Autowired(required=false)
	private UserMapper userMapper;
	@Autowired
	private RedisTemplate<String, Object> redisTemplate;
    //ユーザー登録
    @Override
    public ResponseEntity addUsers(User user) {
    	User u = userMapper.findUsers(user.getAccount());
    	//既存ユーザーチェック]
        if (u != null) {
            return ResponseEntity.error("該当ユーザー名は既に存在しています");
        }else {
        	userMapper.addUsers(user);
        	return ResponseEntity.success("登録成功");
        }
    }
    
    //ログイン
    @Override
    public ResponseEntity loginUsers(User user) {
        User a = userMapper.findUsers(user.getAccount()); 
        User p = userMapper.passwordKensa(user.getAccount());
        //既存ユーザーチェック
        if (a != null) {
            if(p.getPassword().equals(user.getPassword())){
            	
                // 生成 JWT 并将其存储到用户对象中
                String token = JWTUtil.sign(user.getAccount(), user.getPassword());
                a.setToken(token);
                // 将 TOKEN 存储到 Redis
                // redisTemplate.opsForValue().set(token, a, 30, TimeUnit.MINUTES);
                // 将 TOKEN 存储到 db
                userMapper.addToken(token);

                return ResponseEntity.success("ログイン成功");

            }
            return ResponseEntity.error("該当passwordは正しくありません");
        }else {
            return ResponseEntity.error("該当ユーザー名は存在しません");
        }
    }

}
	