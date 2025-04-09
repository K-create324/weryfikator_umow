package com.example.moj_projekt_umowy;

import org.apache.poi.xwpf.usermodel.XWPFDocument;
import org.springframework.stereotype.Component;


import java.io.IOException;
import java.io.InputStream;
import java.util.List;
import java.util.stream.Collectors;
@Component
public class DocxUtils {

    public DocxUtils() {

    }

    public static List<String> extractTextFromDocx(InputStream inputStreame) throws IOException {

        try (XWPFDocument document = new XWPFDocument(inputStreame)) {

            List<String> content = document.getParagraphs().stream()
                    .map(par -> par.getText())
                    .collect(Collectors.toList());

            return content;
        }
    }
}
