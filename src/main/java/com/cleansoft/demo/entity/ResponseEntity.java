package com.cleansoft.demo.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ResponseEntity<T> {
    private int code;
    private String msg;
    private T data;

    public static <T> ResponseEntity success(String msg){
        return new ResponseEntity(200,msg,null);
    }
    public static <T> ResponseEntity success(String msg,T data){
        return new ResponseEntity(200,msg,data);
    }
    public static <T> ResponseEntity error(String msg){
    	return new ResponseEntity(500,msg,null);
    }
    public static <T> ResponseEntity error(int code,String msg){
    return new ResponseEntity(code,msg,null);
    }

}