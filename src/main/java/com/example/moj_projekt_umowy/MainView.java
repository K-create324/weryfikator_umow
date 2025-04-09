package com.example.moj_projekt_umowy;


import com.vaadin.flow.component.button.Button;

import com.vaadin.flow.component.button.ButtonVariant;
import com.vaadin.flow.component.dependency.CssImport;
import com.vaadin.flow.component.html.Div;
import com.vaadin.flow.component.html.H3;
import com.vaadin.flow.component.html.Paragraph;
import com.vaadin.flow.component.icon.Icon;
import com.vaadin.flow.component.icon.VaadinIcon;
import com.vaadin.flow.component.orderedlayout.HorizontalLayout;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.component.progressbar.ProgressBar;
import com.vaadin.flow.component.upload.Upload;
import com.vaadin.flow.component.upload.receivers.MemoryBuffer;
import com.vaadin.flow.router.Route;
import com.vaadin.flow.component.html.H1;
import com.vaadin.flow.theme.Theme;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;




@Route("")

@CssImport("./themes/my-theme/styles.css")
public class MainView extends VerticalLayout
{

private File uploadedFileile;
private Paragraph resultText;
private Paragraph resultText2;
private MemoryBuffer buffer;
private LegalCheckService legalCheckService;
private ClauseAnalyzerPercentage clauseAnalyzerPercentage;
private ProgressBar progressBar;

    public MainView( LegalCheckService legalCheckService,ClauseAnalyzerPercentage clauseAnalyzerPercentage) {
        this.legalCheckService = legalCheckService;
        this.clauseAnalyzerPercentage=clauseAnalyzerPercentage;


//przycisk nagłówka
        Icon icon= VaadinIcon.FILE_TEXT.create();
        H1 headher = new H1("ANALIZA UMOWY");
        HorizontalLayout horizontalLayout = new HorizontalLayout(headher, icon);
        
        //przycisk ładowania dokuemntu
        buffer = new MemoryBuffer();
        Upload upload = new Upload(buffer);
        upload.setAcceptedFileTypes(".docx", ".pdf");

        //przycisk wgraj plik
        Div uploadSection= new Div();
        uploadSection.addClassName("custom-upload-section");
        uploadSection.add(new H3("Wgraj plik"),upload);

        //przycisk "Analiza"
        Div analyseHeader =new Div();
        analyseHeader.addClassNames("section-header");
        analyseHeader.add(new H3("Analiza"));



        //przycisk uruchamiający analize
        Button analyzeButton = new Button("analizuj umowę", event ->
        {
            try {
                analyzeContractPrivate();
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        });

//wyróżnienie- przycisk zielony
        analyzeButton.addClassName("analyze-button");
        analyzeButton.addThemeVariants(ButtonVariant.LUMO_PRIMARY,ButtonVariant.LUMO_SUCCESS);


//inicjalizacja progress bar
         progressBar = new ProgressBar();
        progressBar.setWidth("300px");
        progressBar.setValue(0.0);

        //miejsce gdzie pojawia się wynik analizy
        resultText= new Paragraph("wynik analizy pojawi sie tutaj" );
        resultText.addClassName("result-text");
        resultText.setWidthFull();

        resultText2= new Paragraph("tutaj pojawiają się brakujące klauzule" );
        resultText2.addClassName("result-text");
        resultText2.setWidthFull();

        Button missingClause = new Button("Pokaż brakujace klauzule", event->
        {
            try {
                missingClausePrivate();
            } catch (IOException e) {
                throw new RuntimeException(e);
            }


        });
        VerticalLayout analyzeSection = new VerticalLayout(analyzeButton, progressBar, resultText);
        add( headher,horizontalLayout, uploadSection, upload,analyseHeader, analyzeSection, missingClause, resultText2);
    }

    private void analyzeContractPrivate() throws IOException {
        InputStream inputStream= buffer.getInputStream();
        String fileName= buffer.getFileName();
String fileExtension=  fileName.substring(fileName.lastIndexOf(".") +1).toLowerCase();
        double percentageResult = clauseAnalyzerPercentage.analyzeContract(inputStream, fileExtension);
        String comment = legalCheckService.resultComment(percentageResult);
        progressBar.setValue(percentageResult/100);
        resultText.setText("Zgodność umowy z klauzulami to" +percentageResult + "% " + comment);

    }
    private void missingClausePrivate() throws IOException {
        InputStream inputStream2 = buffer.getInputStream();

       String missingClauseString= legalCheckService.missingClauseProcessor(inputStream2);
        resultText2.setText("Wynik analizy " +missingClauseString);
    }
    }
