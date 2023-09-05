package com.cleansoft.demo.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.cleansoft.demo.entity.ResponseEntity;
import com.cleansoft.demo.entity.User;
import com.cleansoft.demo.service.UserService;

//ユーザー登録

//@CrossOrigin(origins = "http://localhost:8081")
@RestController
@RequestMapping("/users")
public class UserController {
	@Autowired(required = false)
    UserService userService;
	//ユーザー登録
    @RequestMapping("/addUsers")
    public ResponseEntity addUser(@RequestBody User user) {
        return userService.addUsers(user);
    }
	/*
	 * //既存ユーザーチェック
	 * 
	 * @RequestMapping("/findUser/{account}") public ResponseEntity
	 * findUserAccount(@PathVariable("account") String account){ return
	 * userService.findUserAccount(account); }
	 */
    //ログイン
    @RequestMapping("/loginUsers")
    public ResponseEntity loginUsers(@RequestBody User user) {
        return userService.loginUsers(user);
    }

}


