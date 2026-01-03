package de.FileTransormer.FileTransformer;

public class ToolClass {

	public static String cutPNGTitel(String fileLocationName) {

		int lastDot = fileLocationName.lastIndexOf('.');
		if (lastDot > 0) {
			return fileLocationName.substring(0, lastDot);
		}
		return fileLocationName;
	}

}
