package de.FileTransormer.FileTransformer;

import java.io.FileInputStream;
import java.io.FileOutputStream;

import org.apache.poi.xwpf.usermodel.XWPFDocument;

import fr.opensagres.poi.xwpf.converter.pdf.PdfConverter;
import fr.opensagres.poi.xwpf.converter.pdf.PdfOptions;

public class WordToPdf {
	
	public static void wordToPdf(String fileLocation, String fileOutputLocation) {
		
		try {
    		FileInputStream docFile = new FileInputStream(fileLocation);
    		XWPFDocument doc = new XWPFDocument(docFile);
    		PdfOptions pdfOptions = PdfOptions.create();
    		FileOutputStream out = new FileOutputStream(fileOutputLocation);
    		PdfConverter.getInstance().convert(doc, out, pdfOptions);
    		doc.close();
    		out.close();
    	}
    	catch(Exception e) {
    		e.printStackTrace();
    	}
        
	}

}
