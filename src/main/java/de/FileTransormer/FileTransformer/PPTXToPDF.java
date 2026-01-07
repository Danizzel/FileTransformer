package de.FileTransormer.FileTransformer;

import com.aspose.pdf.Document;
import com.aspose.pdf.PptxSaveOptions;
import com.spire.presentation.FileFormat;
import com.spire.presentation.Presentation;


public class PPTXToPDF {
	
	
	//TODO: Erster Test der Umwandlung der Powerpoint Datei in PDF war erfolgreich allerdings sehr langsam = weiter überprüfen
	public static boolean pptxToPDF(String fileLocation, String fileOutputLocation) {
		
		Presentation presentation = new Presentation();
		
		try {
			presentation.loadFromFile(fileLocation);
			
			presentation.saveToFile(fileOutputLocation, FileFormat.PDF);
			presentation.dispose();
			return true;
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return false;
	}
	
	public static boolean pdfToPPTX(String fileLocation, String fileOutputLocation) {

		try (Document doc = new Document(fileLocation)) {
			doc.setBackground(java.awt.Color.WHITE);
			doc.setFitWindow(true);
			PptxSaveOptions pptx_save = new PptxSaveOptions();
			pptx_save.setSlidesAsImages(true);
			pptx_save.setImageResolution(200);
			doc.save(fileOutputLocation, pptx_save);
		}catch(Exception e){
			System.err.println("Fehler aufgetreten in PDF zu PPTX umwandlung");
			return false;
		}
		
		return true;
		
		
	}

}
