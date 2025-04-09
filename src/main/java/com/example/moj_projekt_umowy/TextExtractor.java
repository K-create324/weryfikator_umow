package com.example.moj_projekt_umowy;

import org.apache.tika.Tika;
import org.springframework.stereotype.Component;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.List;
@Component
public class TextExtractor {
    private final Tika tika;
    public TextExtractor() {
        this.tika = new Tika();
    }

    public String extractText(InputStream inputStream) throws IOException {
        List<String> text;
        byte[] fileByte = inputStream.readAllBytes();
        String fileType = tika.detect(fileByte);
        try (InputStream processedStream = new ByteArrayInputStream(fileByte)) {
            if (fileType.equals("application/pdf") || fileType.equals("application/x-tika-pdf")) {
                text = PDFUtils.extractTextFromPDF(processedStream);
            } else if (fileType.equals("application/vnd.openxmlformats-officedocument.wordprocessingml.document") ||
                    fileType.equals("application/x-tika-ooxml")) {
                text = DocxUtils.extractTextFromDocx(processedStream);
            } else {
                throw new IOException(" nieobsługiwany format pliku" + fileType);
            }
            String joinedText2 = String.join("", text).toUpperCase();

            return joinedText2;
        }
    }
}



