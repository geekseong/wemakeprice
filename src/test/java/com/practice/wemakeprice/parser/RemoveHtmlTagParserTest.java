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
public class RemoveHtmlTagParserTest {

    private final WebRequest webRequest = mock(WebRequest.class);

    @ParameterizedTest
    @MethodSource("testcase")
    public void HTML_태그_제거_테스트(String input, int chunkNum,  String expectedQ, String expectedR) {

        //stubbing
        when(webRequest.get()).thenReturn(input);

        // given
        Parser removeHtmlTagParser = new RemoveHtmlTagParser(webRequest, chunkNum);

        // when
        Parser.Result result = removeHtmlTagParser.parse();

        // then
        assertThat(result.getQuotient(), equalTo(expectedQ));
        assertThat(result.getRemainder(), equalTo(expectedR));

    }

    // 검증을 위한 테스트 케이스.
    private static Stream<Arguments> testcase() { // argument source method
        return Stream.of(
                Arguments.of("<head><title>위메프 사전과제!@#$%^&*();`~:\'\"</title></head>",5,  "", ""),
                Arguments.of("<head><title>WemaKepriCe!@#$%^&*();`~:\'\"</title></head>", 5,  "aCeee,iKmpr", "W"),
                Arguments.of("<img src=\"./image.jpg\"/>", 5, "", ""),
                Arguments.of("<script>alert('HellO12');!@#$%^&*();`~:\'\"</script>", 5, "a1e2e,HlllO", "rt"),
                Arguments.of("simple text!@#$%^&*();`~:\'\"", 5, "eeilm,psttx", "")
        );
    }
}
