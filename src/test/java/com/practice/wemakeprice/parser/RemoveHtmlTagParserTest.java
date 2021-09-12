package com.practice.wemakeprice.parser;

import com.practice.wemakeprice.infra.WebRequest;
import com.practice.wemakeprice.utils.StringUtil;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.regex.Pattern;
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
    public void HTML_태그_제거_테스트(String input, String expected) {

        //stubbing
        when(webRequest.get()).thenReturn(input);

        // given
        RemoveHtmlTagParser removeHtmlTagParser = new RemoveHtmlTagParser(webRequest);

        // when
        String cleanData = removeHtmlTagParser.parse();

        // then
        assertThat(cleanData, equalTo(expected));
    }

    // 검증을 위한 테스트 케이스.
    private static Stream<Arguments> testcase() { // argument source method
        return Stream.of(
                Arguments.of("<head><title>위메프 사전과제!@#$%^&*();`~:\'\"</title></head>", ""),
                Arguments.of("<head><title>wemakeprice!@#$%^&*();`~:\'\"</title></head>", "wemakeprice"),
                Arguments.of("<img src=\"./image.jpg\"/>", ""),
                Arguments.of("<script>alert('hello');!@#$%^&*();`~:\'\"</script>", "alerthello"),
                Arguments.of("simple text!@#$%^&*();`~:\'\"", "simpletext")
        );
    }
}
