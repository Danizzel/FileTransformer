package de.FileTransormer.FileTransformer;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import org.apache.pdfbox.pdmodel.PDDocument;
import org.apache.pdfbox.rendering.ImageType;
import org.apache.pdfbox.rendering.PDFRenderer;
import org.apache.pdfbox.tools.imageio.ImageIOUtil;

//PDF zu PNG
public class PdfToImage {

	public static boolean pdfToPNG(String fileLocation, String fileOutputLocation) {

		try {
			//PDF File einlesen
			PDDocument doc = PDDocument.load(new File(fileLocation));
			PDFRenderer pdfRenderer = new PDFRenderer(doc);

			//Einen neuen Ordner erstellen für die Bilder (falls keiner schon existiert mit dem Namen der Datei)
			File outputDir = new File(ToolClass.cutPNGTitel(fileOutputLocation));
			if (!outputDir.exists()) {
				outputDir.mkdirs(); // mkdirs() erstellt auch übergeordnete Verzeichnisse, falls nötig
			}

			//Hier nun durch die PDF pages gehen und jede Seite als Bild im Ordner abspeichern
			for (int page = 0; page < doc.getNumberOfPages(); page++) {
				BufferedImage bim = pdfRenderer.renderImageWithDPI(page, 300, ImageType.RGB);

				String fileName = String.format("seite_%d.png", page + 1);
				String outputPath = ToolClass.cutPNGTitel(fileOutputLocation) + File.separator + fileName;

				ImageIOUtil.writeImage(bim, outputPath, 300);
			}

			doc.close();
			
			return true;
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return false;
		}

	}

	
	//wie oben nur mit JPG Files
	public static boolean pdfToJPG(String fileLocation, String fileOutputLocation) {

		try {
			PDDocument doc = PDDocument.load(new File(fileLocation));
			PDFRenderer pdfRenderer = new PDFRenderer(doc);

			File outputDir = new File(ToolClass.cutPNGTitel(fileOutputLocation));
			if (!outputDir.exists()) {
				outputDir.mkdirs();
			}

			for (int page = 0; page < doc.getNumberOfPages(); page++) {
				BufferedImage bim = pdfRenderer.renderImageWithDPI(page, 300, ImageType.RGB);

				String fileName = String.format("seite_%d.jpg", page + 1);
				String outputPath = ToolClass.cutPNGTitel(fileOutputLocation) + File.separator + fileName;

				ImageIOUtil.writeImage(bim, outputPath, 300);
			}

			doc.close();
			return true;
		} catch (IOException e) {
			e.printStackTrace();
			return false;
		}

	}

	
}
