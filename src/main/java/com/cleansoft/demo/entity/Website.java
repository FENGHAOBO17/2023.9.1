package com.cleansoft.demo.entity;


import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.Data;

@Data
public class Website{

	private int id;
	//url
    private String url;
    //logo
    private String logo;
    //title
    private String title;
    //desc
    @JsonProperty("desc")
    private String des;
    //category_code
    private String categoryCode;

}