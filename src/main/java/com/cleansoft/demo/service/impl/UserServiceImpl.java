package com.cleansoft.demo.service.impl;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.cleansoft.demo.entity.ResponseEntity;
import com.cleansoft.demo.entity.User;
import com.cleansoft.demo.mapper.UserMapper;
import com.cleansoft.demo.service.UserService;

@Service
public class UserServiceImpl implements UserService {
	@Autowired(required=false)
	private UserMapper userMapper;
    //ユーザー登録
    @Override
    public ResponseEntity addUsers(User user) {
    	User u = userMapper.findUsers(user.getAccount());
    	//既存ユーザーチェック
        if (u != null) {
            return ResponseEntity.error("該当ユーザー名は既に存在しています");
        }else {
        	return ResponseEntity.success("登録成功");
        }
    }

}
	