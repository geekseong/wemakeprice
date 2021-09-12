package com.practice.wemakeprice.parser;

import com.practice.wemakeprice.infra.WebRequest;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.stream.Stream;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.equalTo;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class SimpleHtmlParserTest {

    private final WebRequest webRequest = mock(WebRequest.class);

    @ParameterizedTest
    @MethodSource("testcase")
    public void Text_전체_테스트(String input, int chunkNum, String expectedQ, String expectedR) {

        //stubbing
        when(webRequest.get()).thenReturn(input);

        // given
        Parser simpleHtmlParser = new SimpleHtmlParser(webRequest, chunkNum);

        // when
        Parser.ParserResult result = simpleHtmlParser.parse();

        // then
        assertThat(result.getQuotient(), equalTo(expectedQ));
        assertThat(result.getRemainder(), equalTo(expectedR));

    }

    // 검증을 위한 테스트 케이스.
    private static Stream<Arguments> testcase() { // argument source method
        return Stream.of(
                Arguments.of("<head><title>위메프 사전과제</title></head>", 5, "aadde,eeehh,iillt", "ttt"),
                Arguments.of("<head><title>wemakeprice!@#$%^&*();`~:\'\"</title></head>", 5,  "aaacd,deeee,eeehh,iiikl,lmprt", "tttw"),
                Arguments.of("<img src=\"./image.jpg\"/>", 5, "acegg,giijm", "mprs"),
                Arguments.of("<script>alert('hello');!@#$%^&*();`~:\'\"</script>", 5,  "accee,hiill,loppr,rrsst", "tt"),
                Arguments.of("simple text!@#$%^&*();`~:'", 5, "eeilm,psttx", "")
        );
    }
}
