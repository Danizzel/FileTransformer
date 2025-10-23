package de.FileTransormer.FileTransformer;

import java.awt.Image;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;

import com.itextpdf.text.Document;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.pdf.PdfWriter;

public class ImageToPDF {

	public static void imageToPdf(String filelocation, String fileOutputLocation) {
		Document doc = new Document();
		
		FileOutputStream out;
		try {
			out = new FileOutputStream(fileOutputLocation);
			PdfWriter writer = PdfWriter.getInstance(doc, out);
			
			writer.open();
			doc.open();
			
			com.itextpdf.text.Image image = com.itextpdf.text.Image.getInstance(filelocation);
			
			doc.setPageSize(image);
            doc.newPage();
            image.setAbsolutePosition(0, 0);
            
            doc.add(image);
			doc.close();
			writer.close();
			out.close();
			
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (DocumentException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (MalformedURLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
