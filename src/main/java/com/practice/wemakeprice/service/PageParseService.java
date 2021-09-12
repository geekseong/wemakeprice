package com.practice.wemakeprice.service;

import com.practice.wemakeprice.dto.PageParseResponseDto;
import com.practice.wemakeprice.dto.PageParseRequestDto;
import com.practice.wemakeprice.parser.ParserFactory;
import com.practice.wemakeprice.parser.Parser;
import org.springframework.stereotype.Service;

@Service
public class PageParseService {
    public PageParseResponseDto parse(PageParseRequestDto dto) {
        Parser parser = ParserFactory.getParser(dto.getUrl(), dto.getType(), dto.getChunkNum());
        Parser.ParserResult result = parser.parse();
        return new PageParseResponseDto(result.getQuotient(), result.getRemainder());
    }
}
