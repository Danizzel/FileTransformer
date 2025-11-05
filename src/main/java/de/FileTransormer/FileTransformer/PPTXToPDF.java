package de.FileTransormer.FileTransformer;

import com.spire.presentation.FileFormat;
import com.spire.presentation.Presentation;

public class PPTXToPDF {
	
	public static void pptxToPDF(String fileLocation, String fileOutputLocation) {
		
		Presentation presentation = new Presentation();
		
		try {
			presentation.loadFromFile(fileLocation);
			
			presentation.saveToFile(fileOutputLocation, FileFormat.PDF);
			presentation.dispose();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}

}
