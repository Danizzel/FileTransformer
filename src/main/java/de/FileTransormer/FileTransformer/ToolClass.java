package de.FileTransormer.FileTransformer;


//Hilfsklasse
public class ToolClass {

	//Hilfsmethode zum abschneiden der Fileendung (hier zb PNG bei bennenung des neuen Directorys in zb PDFToImage)
	public static String cutPNGTitel(String fileLocationName) {

		int lastDot = fileLocationName.lastIndexOf('.');
		if (lastDot > 0) {
			return fileLocationName.substring(0, lastDot);
		}
		return fileLocationName;
	}

}
