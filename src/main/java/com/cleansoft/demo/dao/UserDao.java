package com.cleansoft.demo.dao;

import com.cleansoft.demo.entity.User;

//ユーザー登録

public interface UserDao {
        //ユーザー追加
        int addUsers(User user);
		
		//ユーザー既存チェック 
        User findUsers(String account);
		 
}