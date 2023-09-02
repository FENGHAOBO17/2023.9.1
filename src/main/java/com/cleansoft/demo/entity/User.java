package com.cleansoft.demo.entity;

import java.io.Serializable;

import lombok.Data;

@Data
public class User implements Serializable {

	//ユーザー名
    private String account;
    //パスワード
    private String password;
}