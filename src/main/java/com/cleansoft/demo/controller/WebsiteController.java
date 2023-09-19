package com.cleansoft.demo.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.cleansoft.demo.entity.ResponseEntity;
import com.cleansoft.demo.entity.dto.WebsiteCategoryDto;
import com.cleansoft.demo.service.WebsiteService;

@CrossOrigin
@RestController
@RequestMapping("/website")
public class WebsiteController {
    @Autowired(required = false)
    WebsiteService websiteService;
    
    @RequestMapping("/findAll")
    public ResponseEntity<List<WebsiteCategoryDto>> findAll() {
        List<WebsiteCategoryDto> websiteCategoryList = websiteService.findAll();
        return ResponseEntity.success("Success", websiteCategoryList);
    }
   
}