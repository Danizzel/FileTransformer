package de.FileTransormer.FileTransformer;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import org.apache.pdfbox.pdmodel.PDDocument;
import org.apache.pdfbox.rendering.ImageType;
import org.apache.pdfbox.rendering.PDFRenderer;
import org.apache.pdfbox.tools.imageio.ImageIOUtil;

public class PdfToImage {

	public static void pdfToPNG(String fileLocation, String fileOutputLocation) {
		
		try {
			PDDocument doc = PDDocument.load(new File(fileLocation));
			PDFRenderer pdfRenderer = new PDFRenderer(doc);
			
			for(int page=0; page< doc.getNumberOfPages();page++) {
				BufferedImage bim = pdfRenderer.renderImageWithDPI(page, 300, ImageType.RGB);
				ImageIOUtil.writeImage(bim,String.format(fileOutputLocation, page+1,"png"), 300);
				doc.close();
			}
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
}
