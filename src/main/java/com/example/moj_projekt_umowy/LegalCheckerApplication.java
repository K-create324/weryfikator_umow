package com.example.moj_projekt_umowy;

import org.apache.tika.Tika;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.List;
import java.util.stream.Collectors;


import org.apache.poi.xwpf.usermodel.XWPFDocument;
import org.apache.poi.xwpf.usermodel.XWPFParagraph;
import org.springframework.web.multipart.MultipartFile;



@SpringBootApplication
public class LegalCheckerApplication  {


	public static void main(String[] args) throws IOException {



		SpringApplication.run(LegalCheckerApplication.class, args);

	}


}
