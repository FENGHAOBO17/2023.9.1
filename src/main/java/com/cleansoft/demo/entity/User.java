package com.cleansoft.demo.entity;

import java.io.Serializable;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class User implements Serializable {

	//ユーザー名
    private String account;
    //パスワード
    private String password;
}