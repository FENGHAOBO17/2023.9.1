package com.cleansoft.demo.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.cleansoft.demo.dao.UserDao;
import com.cleansoft.demo.entity.ResponseEntity;
import com.cleansoft.demo.entity.User;
import com.cleansoft.demo.service.UserService;

@Service
public class UserServiceImpl implements UserService {
    @Autowired(required = false)
    UserDao userDao;
    //ユーザー登録
    @Override
    public ResponseEntity addUsers(User user) {
        ResponseEntity responseEntity;
        User u = userDao.findUsers(user.getAccount());
        //判断用户是否重复
        if (u != null) {
            return ResponseEntity.error("該当ユーザー名は既に存在しています");
        }else {
        	return ResponseEntity.success("登録成功");
        }
    }

}
	