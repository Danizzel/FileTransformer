package de.FileTransormer.FileTransformer;

import com.aspose.words.Document;
import com.aspose.words.SaveFormat;

public class PDFToWord {

	
	public static void pdfToWord(String fileLocation, String fileOutputLocation) {
		
		try {
			Document pdfDocument = new Document(fileLocation);
			
			pdfDocument.save(fileOutputLocation, SaveFormat.DOCX);
			
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
