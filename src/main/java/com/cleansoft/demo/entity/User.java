package com.cleansoft.demo.entity;


import lombok.Data;

@Data
public class User{

	private String id;
	//ユーザー名
    private String account;
    //パスワード
    private String password;
    //token
    private String token;
}