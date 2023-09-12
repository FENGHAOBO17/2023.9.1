package com.cleansoft.demo.service;



import com.cleansoft.demo.entity.ResponseEntity;
import com.cleansoft.demo.entity.User;

//ユーザー登録
public interface UserService {
        //ユーザー登録
		ResponseEntity addUsers(User user);
		
		//ログイン
		ResponseEntity loginUsers(User user);
}