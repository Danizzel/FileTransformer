package de.FileTransormer.FileTransformer;


//Hilfsklasse
public class ToolClass {

	public static String cutTitel(String fileLocationName) {

		int lastDot = fileLocationName.lastIndexOf('.');
		if (lastDot > 0) {
			return fileLocationName.substring(0, lastDot);
		}
		return fileLocationName;
	}


}
