package com.example.moj_projekt_umowy;


import org.springframework.stereotype.Service;
import java.io.IOException;
import java.io.InputStream;
import java.util.List;

@Service
public  class LegalCheckService {
    private final LegalChecker legalChecker;
    private final TextExtractor textExtractor;

    public LegalCheckService(LegalChecker legalChecker, TextExtractor textExtractor) {
        this.legalChecker = legalChecker;
        this.textExtractor = textExtractor;

    }

//    public double analyzeContract(InputStream inputStream, String fileExtension) throws IOException {
//
//        String joinedText = textExtractor.extractText(inputStream);
//        List<String> requiredClauses = legalChecker.getRequiredClauses();
//        long counted = requiredClauses.stream()
//                .filter(t -> joinedText.contains(t))
//                .count();
//
//        double percentageResult = (double) counted / requiredClauses.size() * 100;
//        return percentageResult;
//    }

    public String missingClause(String joinedText) {
        StringBuilder missing = new StringBuilder();
        for (String clause : legalChecker.getRequiredClauses()) {
            if (!joinedText.contains(clause)) {
                missing.append(clause).append("\n").append(", ");
            }
        }

return  missing.length()>0 ? "Brakujace klauzule to " + missing.toString()  : "wszystkie klauzule sa zawarte";
        }

    public String missingClauseProcessor(InputStream inputStream) throws IOException {

        String joinedText2= textExtractor.extractText(inputStream);
        return missingClause(joinedText2);

    }
    public String resultComment(double percentageResult) {
        String comment;
        if (percentageResult >= 30) {
            comment = " Umowa poprawna";
        } else {
            comment = "Umowa do zmiany";
        }
        return comment;
    }

}


