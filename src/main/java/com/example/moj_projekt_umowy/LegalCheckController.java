//package com.example.moj_projekt_umowy;
//
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.http.ResponseEntity;
//import org.springframework.web.bind.annotation.*;
//import org.springframework.web.multipart.MultipartFile;
//
//import java.io.IOException;
//import java.io.InputStream;
//
//@RestController
//@RequestMapping("/legal")
//public class LegalCheckController {
//     public final LegalCheckService legalCheckService;
//
//   @Autowired
//    public LegalCheckController(LegalCheckService legalCheckService) {
//        this.legalCheckService = legalCheckService;
//    }
//@GetMapping
//public ResponseEntity <String> wszystkoOK(){
//    String answer = "wszystko gra";
//    return ResponseEntity.ok(answer);
//}
//    @PostMapping
//    public ResponseEntity <String> analizeDokuemnt (@RequestParam("file")MultipartFile file) throws IOException {
//        InputStream inputStream = file.getInputStream();
////        String fileextension = file.toString();
//
//        double compilancePercentage = legalCheckService.analyzeContract(inputStream );
//        String comment = legalCheckService.resultComment(compilancePercentage);
//        return ResponseEntity.ok("Zgodność umowy z wymaganymi klauzulami " + compilancePercentage + "%" + comment);
//
//    }
//
//}
