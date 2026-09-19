package de.FileTransormer.FileTransformer;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;

import org.apache.poi.xwpf.usermodel.BreakType;
import org.apache.poi.xwpf.usermodel.XWPFDocument;
import org.apache.poi.xwpf.usermodel.XWPFParagraph;
import org.apache.poi.xwpf.usermodel.XWPFRun;

import com.itextpdf.text.pdf.PdfReader;
import com.itextpdf.text.pdf.parser.PdfReaderContentParser;
import com.itextpdf.text.pdf.parser.SimpleTextExtractionStrategy;
import com.itextpdf.text.pdf.parser.TextExtractionStrategy;

import fr.opensagres.poi.xwpf.converter.pdf.PdfConverter;
import fr.opensagres.poi.xwpf.converter.pdf.PdfOptions;

public class WordToPdf {
	
	//Word zu PDF
	public static boolean wordToPdf(String fileLocation, String fileOutputLocation) {
		
		try {
		//Word File einlesen
    		FileInputStream docFile = new FileInputStream(fileLocation);
    		XWPFDocument doc = new XWPFDocument(docFile);
    		PdfOptions pdfOptions = PdfOptions.create();
    		
    		//PDF Speicherort einlesen
    		FileOutputStream out = new FileOutputStream(fileOutputLocation);
    		//Word File zu PDF umwandeln und abspeichern
    		PdfConverter.getInstance().convert(doc, out, pdfOptions);
    		doc.close();
    		out.close();
    		return true;
    	}
    	catch(Exception e) {
    		e.printStackTrace();
    		return false;
    	}
        
	}
	
	//PDF zu Word
	public static boolean pdfToWord(String fileLocation, String fileOutputLocation) {
		
		//TODO: pdf to word kopiert bis jetzt ohne formatierung nur den text
		
		try {
			XWPFDocument doc = new XWPFDocument();
			PdfReader reader = new PdfReader(fileLocation);
			PdfReaderContentParser parser = new PdfReaderContentParser(reader);
			
			for(int i = 1; i <= reader.getNumberOfPages(); i++) {
				TextExtractionStrategy strategy = parser.processContent(i, new SimpleTextExtractionStrategy());
				String text = strategy.getResultantText();
				XWPFParagraph p = doc.createParagraph();
				XWPFRun run = p.createRun();
				run.setText(text);
				run.addBreak(BreakType.PAGE);
			}
			
			FileOutputStream out = new FileOutputStream(fileOutputLocation);
			doc.write(out);
			doc.close();
			reader.close();
			return true;
		} catch (IOException e) {
			System.err.println("Fehler in der PDF to Word umwandlung");
			return false;
		}
	}

}
