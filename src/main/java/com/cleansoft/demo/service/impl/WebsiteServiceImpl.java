package com.cleansoft.demo.service.impl;

import java.util.ArrayList;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.cleansoft.demo.entity.Website;
import com.cleansoft.demo.entity.dto.WebsiteCategoryDto;
import com.cleansoft.demo.mapper.WebsiteMapper;
import com.cleansoft.demo.service.WebsiteService;


@Service
public  class WebsiteServiceImpl implements WebsiteService {
	private static final Logger logger = LoggerFactory.getLogger(WebsiteServiceImpl.class);
	@Autowired(required=false)
	private WebsiteMapper websiteMapper;
    @Autowired(required = false)
    Website websiteo;
	
	@Override
	public List<WebsiteCategoryDto> findAll() {
		List<WebsiteCategoryDto> websiteCategoryList  = websiteMapper.findAll();
		List<Website> websiteList  = websiteMapper.findWebsite();
		
		for(WebsiteCategoryDto websiteCategory : websiteCategoryList) {
			List<Website> categoryWebsiteList = new ArrayList();
			for(Website website:websiteList) {
				if((websiteCategory.getCategoryCodes())
						.equals(website.getCategoryCode())) {			
					categoryWebsiteList.add(website);
				}
			}
			websiteCategory.setWebsite(categoryWebsiteList);
		}
		return websiteCategoryList;
	}
}