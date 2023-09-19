package com.cleansoft.demo.entity.dto;


import java.util.List;

import com.cleansoft.demo.entity.Website;
import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.Data;

@Data
public class WebsiteCategoryDto{
	//id
	private int id;
    //category_code
    private String categoryCodes;
    //category_name
	@JsonProperty("name")
    private String categoryName;
    //category_name
	@JsonProperty("en_name")
    private String categoryEnName;
    //category_name
	@JsonProperty("icon")
    private String categoryIcon;
    //Website
	@JsonProperty("web")
    List<Website> website;
}