package de.FileTransormer.FileTransformer;


public class App {
	
	/*
	 * Conversions die schon funktionieren:
	 * -Word zu PDF
	 * -PDF zu Word (nicht perfekt, hier wird nur Text convertiert)
	 * -PDF zu PNG (nicht perfekt, hier wird nur die erste Seite der PNG konvertiert -> TODO:als Ordner mit mehreren PNGs)
	 */
    public static void main( String[] args ){
    	FileConverter gui = new FileConverter();
    	gui.start();
    }
}
