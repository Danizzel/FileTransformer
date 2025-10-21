package de.FileTransormer.FileTransformer;

import java.io.FileOutputStream;
import java.io.IOException;

import org.apache.poi.xwpf.usermodel.BreakType;
import org.apache.poi.xwpf.usermodel.XWPFDocument;
import org.apache.poi.xwpf.usermodel.XWPFParagraph;
import org.apache.poi.xwpf.usermodel.XWPFRun;

import com.itextpdf.text.pdf.parser.PdfReaderContentParser;
import com.itextpdf.text.pdf.parser.SimpleTextExtractionStrategy;
import com.itextpdf.text.pdf.parser.TextExtractionStrategy;


public class PdfToWord {

	/*
	 * Konvertierung funktioniert allerdings verschlingt diese die Formatierung sowie Bilder und Zeichen
	 * PDF einfach als Block Text in Word angezeigt
	 * 		-> Optimierung
	 */
	
	public static void pdfToWord(String filename, String fileOutputLocation) {
		
		 XWPFDocument doc = new XWPFDocument();
		 try {
			com.itextpdf.text.pdf.PdfReader reader = new com.itextpdf.text.pdf.PdfReader(filename);
			PdfReaderContentParser parser = new PdfReaderContentParser(reader);
			
			for(int i=1; i<= reader.getNumberOfPages(); i++) {
				 TextExtractionStrategy strategy = parser.processContent(i, new SimpleTextExtractionStrategy());
				 String text = strategy.getResultantText();
				 XWPFParagraph para = doc.createParagraph();
				 XWPFRun run = para.createRun();
				 run.setText(text);
				 run.addBreak(BreakType.PAGE);
			}
			
			FileOutputStream out = new FileOutputStream(fileOutputLocation);
			doc.write(out);
			System.out.println("Done");
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
