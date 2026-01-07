package de.FileTransormer.FileTransformer;

import java.awt.image.BufferedImage;

import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;



public class Image {
	
	
	public static boolean ImageToTiff(String fileLocation, String fileOutputLocation) {
		BufferedImage image;
		try {
			image = ImageIO.read(new File(fileLocation));
			
			if(image == null) {
				System.err.println("Konnte nicht als Bild gelesen werden");
				return false;
			}
		} catch (IOException e) {
			return false;
		}
		
		try {
			boolean result = ImageIO.write(image, "tiff", new File(fileOutputLocation));
			
			if(!result) {
				System.err.println("Kein Writer fuer 'tiff' gefunden");
				return false;
			}
		} catch (IOException e) {
			return false;
		}
		
		return true;
	}
	
	public static boolean tiffToImage(String fileLocation, String fileOutputLocation) {
		BufferedImage image;
		try {
			image = ImageIO.read(new File(fileLocation));
			
			if(image == null) {
				System.err.println("Konnte nicht als Bild gelesen werden");
				return false;
			}
		} catch (IOException e) {
			return false;
		}
		
		try {
			boolean result = ImageIO.write(image, "png", new File(fileOutputLocation));
			
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
