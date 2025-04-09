package com.example.moj_projekt_umowy;
import com.itextpdf.kernel.pdf.PdfDocument;
import com.itextpdf.kernel.pdf.PdfReader;
import com.itextpdf.kernel.pdf.canvas.parser.PdfTextExtractor;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.util.ArrayList;

import java.util.List;
import java.io.InputStream;

@Component
public class PDFUtils {

    public PDFUtils() {
    }

    public static List<String> extractTextFromPDF(InputStream inputStream) throws IOException {

        List<String> textList = new ArrayList<>();
        try (PdfReader pdfReader = new PdfReader(inputStream);
             PdfDocument pdfDocument = new PdfDocument(pdfReader);) {
            int numberOfPages = pdfDocument.getNumberOfPages();
            for (int i = 1; i <= numberOfPages; i++) {
                String pageContent = PdfTextExtractor.getTextFromPage(pdfDocument.getPage(i));
                textList.add(pageContent);
            }
        }
        return textList;

    }
    }



