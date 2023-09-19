package com.cleansoft.demo.mapper;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import com.cleansoft.demo.entity.User;

//ユーザー登録
@Mapper
public interface UserMapper {
        //ユーザー追加
        int addUsers(User user);
		
		//ユーザー既存チェック 
        User findUsers(@Param("account") String account);
        
        //ログイン
        int loginUsers(User user);
        
        //passwordチェック
        User passwordKensa(@Param("account") String account);
        
        //addToken
        int addToken(@Param("token") String token,@Param("account") String account);

        //selectToken
        String findToken(@Param("account") String account);
        
        //testToken
        User testToken(@Param("account") String account);
}