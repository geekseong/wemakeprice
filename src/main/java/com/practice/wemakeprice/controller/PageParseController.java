package com.practice.wemakeprice.controller;

import com.practice.wemakeprice.dto.PageParseRequestDto;
import com.practice.wemakeprice.dto.PageParseResponseDto;
import com.practice.wemakeprice.service.PageParseService;
import jdk.nashorn.internal.objects.annotations.Getter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

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
    public ResponseEntity<PageParseResponseDto> result(@Valid @RequestBody PageParseRequestDto dto){
        PageParseResponseDto responseDto = pageParseService.parse(dto);
        return ResponseEntity
                .status(HttpStatus.OK)
                .body(responseDto);
    }
}
