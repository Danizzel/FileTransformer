package de.FileTransormer.FileTransformer;

import com.aspose.pdf.Document;
import com.aspose.pdf.SaveFormat;

public class PDFToWord {

	
	public static void pdfToWord(String fileLocation, String fileOutputLocation) {
		
		// Open the source PDF document
	    Document document = new Document(fileLocation);
	    // Save the file into MS document format
	    document.save(fileOutputLocation, SaveFormat.Doc);
	    document.close();
	}
}
