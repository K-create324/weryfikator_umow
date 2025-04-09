package com.example.moj_projekt_umowy;

import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class LegalChecker {


   private final List<String> requiredClauses;

    public LegalChecker() {
        this.requiredClauses= new ArrayList<>();
        this.requiredClauses.add("klauzula RODO".toUpperCase());
        this.requiredClauses.add("KARA UMOWNA".toUpperCase());
        this.requiredClauses.add("WCZEśNIEJSZE ZAKOŃCZENIE".toUpperCase());
        this.requiredClauses.add("Struktura czasowych stóp procentowych".toUpperCase());
        this.requiredClauses.add("Wypowiedzenie umowy".toUpperCase());

    }

    public List<String> getRequiredClauses() {
        return requiredClauses;
    }

    public void addRequiredClauses(String clause) {
        this.requiredClauses.add(clause);
    }
}
