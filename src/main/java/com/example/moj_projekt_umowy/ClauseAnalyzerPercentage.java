package com.example.moj_projekt_umowy;

import org.springframework.stereotype.Component;

import java.io.IOException;
import java.io.InputStream;
import java.util.List;
@Component
public class ClauseAnalyzerPercentage {
    private final TextExtractor textExtractor;
    private final LegalChecker legalChecker;

     public ClauseAnalyzerPercentage(TextExtractor textExtractor, LegalChecker legalChecker){
         this.textExtractor = textExtractor;
         this.legalChecker = legalChecker;
     }
    public double analyzeContract(InputStream inputStream, String fileExtension) throws IOException {

        String joinedText = textExtractor.extractText(inputStream);
        List<String> requiredClauses = legalChecker.getRequiredClauses();
        long counted = requiredClauses.stream()
                .filter(t -> joinedText.contains(t))
                .count();

        double percentageResult = (double) counted / requiredClauses.size() * 100;
        return percentageResult;
    }
}
