package de.FileTransormer.FileTransformer;

import java.io.File;
import java.io.IOException;

import org.apache.pdfbox.pdmodel.PDDocument;
import org.apache.pdfbox.rendering.PDFRenderer;

import com.spire.presentation.FileFormat;
import com.spire.presentation.Presentation;

public class PPTXToPDF {
	
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
		
		/*
		 * TODO:PDF Datei in Bilder konvertieren und in einen Ordner speichern
		 * 		Diese Bilder dann per Apache POI als Bilder in die Powerpoints slides einfügen
		 * 			-> zwei Methode nötig 
		 * 				-> create Image (wiederverwendung von pdfToImage Methode
		 * 				-> Methode um diese Bilder zu einer Powerpoint Datei einzufügen
		 * 					->den Ordner mit den Bildern am Ende löschen
		 */
	}

}
