package com.ascenda.hotels_data_merge.services;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.languagetool.JLanguageTool;
import org.languagetool.rules.DemoRule;
import org.languagetool.rules.Rule;
import org.languagetool.rules.RuleMatch;
import org.mockito.junit.jupiter.MockitoExtension;
import org.mockito.Mock;
import org.springframework.test.util.ReflectionTestUtils;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.BDDMockito.given;


@ExtendWith(MockitoExtension.class)
public class TestLanguageService {

    @Mock
    private JLanguageTool jLanguageTool;

    LanguageService languageService = new LanguageService();
    static List<RuleMatch> ruleMatchList = new ArrayList<>();

    @BeforeAll
    public static void setup() {
        Rule rule = new DemoRule();
        RuleMatch ruleMatch = new RuleMatch(rule, null, 0, 10, "hello");
        List<String> suggestions = List.of("hello");
        ruleMatch.setSuggestedReplacements(suggestions);
        ruleMatchList.add(ruleMatch);
    }

    @Test
    public void verifyCorrectionWithSuggestion() throws IOException {
        given(jLanguageTool.check(anyString())).willReturn(ruleMatchList);
        ReflectionTestUtils.setField(languageService, "langTool", jLanguageTool);

        String suggestion = languageService.correction("test");
        assertEquals(suggestion, "hello");
    }

    @Test
    public void verifyCorrectionWithoutSuggestion() throws IOException {
        ReflectionTestUtils.setField(languageService, "langTool", jLanguageTool);

        String suggestion = languageService.correction("test");
        assertEquals(suggestion, "test");
    }
}
