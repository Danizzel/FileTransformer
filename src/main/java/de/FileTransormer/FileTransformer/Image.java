package de.FileTransormer.FileTransformer;

import java.awt.image.BufferedImage;

import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;



public class Image {
	
	//Image File zur TIFF File
	public static boolean ImageToTiff(String fileLocation, String fileOutputLocation) {
		BufferedImage image;
		try {
			//Image File einlesen
			image = ImageIO.read(new File(fileLocation));
			
			//Prüfung ob einlesen funktioniert hat -> sonst return false (im FileConverter dann Fehlermeldung ausgeben)
			if(image == null) {
				System.err.println("Konnte nicht als Bild gelesen werden");
				return false;
			}
		} catch (IOException e) {
			return false;
		}
		
		try {
			//Image als Tiff File abspeichern
			boolean result = ImageIO.write(image, "tiff", new File(fileOutputLocation));
			
			//Prüfung ob Abspeicherung funktioniert hat -> sonst wie oben false zurück geben
			if(!result) {
				System.err.println("Kein Writer fuer 'tiff' gefunden");
				return false;
			}
		} catch (IOException e) {
			return false;
		}
		
		return true;
	}
	
	//Tiff File zu Image File
	public static boolean tiffToImage(String fileLocation, String fileOutputLocation) {
		BufferedImage image;
		try {
			//einlesen
			image = ImageIO.read(new File(fileLocation));
			
			//check ob einlesen erfolgreich war
			if(image == null) {
				System.err.println("Konnte nicht als Bild gelesen werden");
				return false;
			}
		} catch (IOException e) {
			return false;
		}
		
		try {
			//neue Image Datei abspeichern
			boolean result = ImageIO.write(image, "png", new File(fileOutputLocation));
			
			//check ob Abspeicherung erfolgreich war
			if(!result) {
				System.err.println("Kein Writer fuer 'png' gefunden");
				return false;
			}
		} catch (IOException e) {
			return false;
		}
		
		return true;
	}

}
