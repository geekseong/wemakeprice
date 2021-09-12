package com.practice.wemakeprice.parser;

import com.practice.wemakeprice.enumtype.ParserType;
import com.practice.wemakeprice.exception.NotFoundParserException;
import com.practice.wemakeprice.infra.WebRequest;

public class ParserFactory {

    public static Parser getParser(String url, ParserType parserType, int chunkNum) {

        // HTML 태그 포함 전체 파서( HTML TAG 포함 )
        if( parserType.equals(ParserType.INCLUDE_TAG) )
            return new SimpleHtmlParser(new WebRequest(url), chunkNum);

        // HTML 제거 파서
        if (parserType.equals(ParserType.REMOVE_TAG))
            return new RemoveHtmlTagParser(new WebRequest(url), chunkNum);

        throw new NotFoundParserException("타입에 맞는 파서를 찾을 수 없습니다." + parserType);
    }
}
