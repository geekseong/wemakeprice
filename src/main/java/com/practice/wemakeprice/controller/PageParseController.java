package com.practice.wemakeprice.controller;

import com.practice.wemakeprice.dto.PageParseRequestDto;
import com.practice.wemakeprice.dto.PageParseResponseDto;
import com.practice.wemakeprice.service.PageParseService;
import jdk.nashorn.internal.objects.annotations.Getter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

@Controller
public class PageParseController {

    private final PageParseService pageParseService;

    @Autowired
    public PageParseController(PageParseService pageParseService) {
        this.pageParseService = pageParseService;
    }

    @GetMapping("/")
    public String main() {
        return "main";
    }

    @ResponseBody
    @PostMapping("/result")
    public PageParseResponseDto result(@RequestBody PageParseRequestDto dto){
        return pageParseService.parse(dto);
    }
}
