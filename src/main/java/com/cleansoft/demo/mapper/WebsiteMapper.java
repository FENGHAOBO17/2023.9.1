package com.cleansoft.demo.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import com.cleansoft.demo.entity.Website;
import com.cleansoft.demo.entity.dto.WebsiteCategoryDto;




//ユーザー登録
@Mapper
public interface WebsiteMapper {
	//findAll
	List<WebsiteCategoryDto> findAll();
	//findWebsite
	List<Website> findWebsite();
}