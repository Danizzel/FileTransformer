package de.FileTransormer.FileTransformer;

import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.net.MalformedURLException;

import com.itextpdf.text.Document;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.pdf.PdfWriter;

//Image zu PDF File
public class ImageToPDF {

	public static boolean imageToPdf(String filelocation, String fileOutputLocation) {
		Document doc = new Document();
		
		FileOutputStream out;
		try {
			
			//wo PDF File abspeichert werden soll
			out = new FileOutputStream(fileOutputLocation);
			PdfWriter writer = PdfWriter.getInstance(doc, out);
			
			writer.open();
			doc.open();
			
			//Image File einlesen
			com.itextpdf.text.Image image = com.itextpdf.text.Image.getInstance(filelocation);
			
			//Image File zu PDF File umwandeln
			doc.setPageSize(image);
            doc.newPage();
            image.setAbsolutePosition(0, 0);
            
            //und abspeichern/ writer sowie doc schließen
            doc.add(image);
			doc.close();
			writer.close();
			out.close();
			
			return true;
			
		} catch (FileNotFoundException e) {
			e.printStackTrace();
			return false;
		} catch (DocumentException e) {
			e.printStackTrace();
			return false;
		} catch (MalformedURLException e) {
			e.printStackTrace();
			return false;
		} catch (IOException e) {
			e.printStackTrace();
			return false;
		}
	}
}
