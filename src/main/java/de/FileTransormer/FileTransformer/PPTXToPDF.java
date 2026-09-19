package de.FileTransormer.FileTransformer;

import org.apache.pdfbox.pdmodel.PDDocument;
import org.apache.pdfbox.pdmodel.PDPage;
import org.apache.pdfbox.pdmodel.PDPageContentStream;
import org.apache.pdfbox.pdmodel.common.PDRectangle;
import org.apache.pdfbox.pdmodel.graphics.image.LosslessFactory;
import org.apache.pdfbox.pdmodel.graphics.image.PDImageXObject;
import org.apache.pdfbox.rendering.ImageType;
import org.apache.pdfbox.rendering.PDFRenderer;
import org.apache.poi.sl.usermodel.PictureData;
import org.apache.poi.xslf.usermodel.XMLSlideShow;
import org.apache.poi.xslf.usermodel.XSLFPictureData;
import org.apache.poi.xslf.usermodel.XSLFPictureShape;
import org.apache.poi.xslf.usermodel.XSLFSlide;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.nio.file.Files;
import java.nio.file.Paths;


//Powerpoint File zu PDF
public class PPTXToPDF {
	
	
	//TODO: Erster Test der Umwandlung der Powerpoint Datei in PDF war erfolgreich allerdings sehr langsam = weiter überprüfen
	public static boolean pptxToPDF(String fileLocation, String fileOutputLocation) {
		
		float scale = 2.0f; //Damit die Schrift etwas schaerfer wird

		try(XMLSlideShow ppt = new XMLSlideShow(Files.newInputStream(Paths.get(fileLocation)));
		    PDDocument pdf = new PDDocument()){

			Dimension size = ppt.getPageSize();
			int width = (int) (size.width * scale);
			int heigth = (int) (size.height *scale);

			for(XSLFSlide slide : ppt.getSlides()){
				//Folie zeichnen
				BufferedImage img = new BufferedImage(width, heigth, BufferedImage.TYPE_INT_RGB);
				Graphics2D graph = img.createGraphics();
				graph.setPaint(Color.WHITE);
				graph.fillRect(0,0, width, heigth);
				graph.scale(scale, scale);
				slide.draw(graph);
				graph.dispose();

				//Direkt in die PDF schreiben
				PDPage page = new PDPage(new PDRectangle(size.width, size.height));
				pdf.addPage(page);

				PDImageXObject pdImg = LosslessFactory.createFromImage(pdf, img);

				try (PDPageContentStream contentStream = new PDPageContentStream(pdf, page)){
					contentStream.drawImage(pdImg,0, 0, size.width, size.height);
				}
			}

			pdf.save(new File(fileOutputLocation));
			return true;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return false;
	}
	
	//PDF zu PPTX
	public static boolean pdfToPPTX(String fileLocation, String fileOutputLocation) {



		//PDF mit weißen Seiten und dann PPTX Files als Images auf die Seiten abspeichern
		try (PDDocument pd = PDDocument.load(new File(fileLocation));

			XMLSlideShow xmlSlide = new XMLSlideShow()) {

			PDFRenderer renderer = new PDFRenderer(pd);

			int pageCount = pd.getNumberOfPages();

				for(int i=0; i<pageCount; i++) {
					//Hier zuerst PDF Seite als Bild renderen
					BufferedImage buffImage = renderer.renderImageWithDPI(i, 150, ImageType.RGB);

					//BufferedImage nun hier in ein Byte Array schreiben
					ByteArrayOutputStream byteOut = new ByteArrayOutputStream();
					ImageIO.write(buffImage, "png", byteOut);
					byte[] pictureBytes = byteOut.toByteArray();

					//Bilddaten in PPTX Datei registrieren
					XSLFPictureData picData = xmlSlide.addPicture(pictureBytes, PictureData.PictureType.PNG);

					//Neue leere Folie erstellen
					XSLFSlide slide = xmlSlide.createSlide();

					//Foliengroeße an Originalmaß der PDF Seite anpassen
					PDRectangle mediaBox = pd.getPage(i).getMediaBox();
					int width = (int) mediaBox.getWidth();
					int height = (int) mediaBox.getHeight();
					xmlSlide.setPageSize(new Dimension(width, height));

					//Bild als Form auf der Folie platzieren
					XSLFPictureShape picShape = slide.createPicture(picData);
					picShape.setAnchor(new Rectangle(0, 0, width, height));
				}

				//PPTX speichern
				try (FileOutputStream out = new FileOutputStream(fileOutputLocation)){
					xmlSlide.write(out);
				}

		}catch(Exception e){
			System.err.println("Fehler aufgetreten in PDF zu PPTX umwandlung");
			return false;
		}
		
		return true;
		
		
	}

}
