package com.cleansoft.demo.service;



import java.util.List;

import com.cleansoft.demo.entity.dto.WebsiteCategoryDto;



//ユーザー登録
public interface WebsiteService {
        //findAll
		List<WebsiteCategoryDto> findAll();
}