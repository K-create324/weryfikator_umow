import com.example.moj_projekt_umowy.LegalCheckService;
import com.example.moj_projekt_umowy.LegalChecker;
import com.example.moj_projekt_umowy.TextExtractor;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import java.io.IOException;
import java.util.Arrays;

import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class LegalCheckServiceTest {

    @Mock
    TextExtractor textExtractor;

    @Mock
    LegalChecker legalChecker;

    @Test
    public void test2() throws IOException {

        Mockito.when(textExtractor.extractText(Mockito.any())).thenReturn("Tekst, Rodo, Wcześniejsze zakończenie");
        String result = textExtractor.extractText(null);
        System.out.println(result);

        Mockito.when(legalChecker.getRequiredClauses()).thenReturn(Arrays.asList("Rodo", "Kara umowna", "Wcześniejsze zakończenie"));
        System.out.println(legalChecker.getRequiredClauses());

        LegalCheckService legalCheckService = new LegalCheckService(legalChecker, textExtractor);

        String testPierwszejMetody = legalCheckService.missingClauseProcessor(null);
        System.out.println(testPierwszejMetody);

        String testDrugiejjMetody = legalCheckService.missingClause("Tekst, Rodo, Wcześniejsze zakończenie");
        System.out.println(testDrugiejjMetody);

        String testTrzeciejMetodyComment = legalCheckService.resultComment(50);
        System.out.println(testTrzeciejMetodyComment);

    }
    }

