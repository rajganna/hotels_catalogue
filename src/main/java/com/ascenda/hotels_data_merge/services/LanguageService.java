package com.ascenda.hotels_data_merge.services;

import org.apache.commons.text.WordUtils;
import org.languagetool.JLanguageTool;
import org.languagetool.Languages;
import org.languagetool.rules.RuleMatch;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.List;

@Service
public class LanguageService {
    /**
     * Language service calls LanguageTool to correct the amenities list by choosing the first suggested replacement.
     *
     */
    JLanguageTool langTool = new JLanguageTool(Languages.getLanguageForShortCode("en-US"));
    public String correction(String input) throws IOException {
        List<RuleMatch> matches = langTool.check(input);
        for (RuleMatch match : matches) {
           if (match.getSuggestedReplacements().size() > 0) {
               return WordUtils.uncapitalize(match.getSuggestedReplacements().get(0).replaceAll("[^a-zA-Z]",""));
           }
        }

        return WordUtils.uncapitalize(input.trim());
    }
}
