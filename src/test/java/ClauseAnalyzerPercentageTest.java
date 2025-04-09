import com.example.moj_projekt_umowy.ClauseAnalyzerPercentage;
import com.example.moj_projekt_umowy.LegalChecker;
import com.example.moj_projekt_umowy.TextExtractor;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import java.io.IOException;
import java.util.Arrays;

import static org.mockito.Mockito.*;



@ExtendWith(MockitoExtension.class)
public class ClauseAnalyzerPercentageTest {

  @Mock
  TextExtractor textExtractor;
  @Mock
  LegalChecker legalChecker;


    @Test
    public void test() throws IOException {
        System.out.println("testujemy");

        when(textExtractor.extractText(any())).thenReturn("Tekst, Rodo, Wcześniejsze zakończenie");
        String result = textExtractor.extractText(null);
        System.out.println(result);

        when(legalChecker.getRequiredClauses()).thenReturn(Arrays.asList("Rodo", "Kara umowna", "Wcześniejsze zakończenie"));
        System.out.println(legalChecker.getRequiredClauses());


        ClauseAnalyzerPercentage clauseAnalyzerPercentage = new ClauseAnalyzerPercentage(textExtractor, legalChecker);
        double percentage = clauseAnalyzerPercentage.analyzeContract(null, "pdf");
        System.out.println(percentage);
    }


}