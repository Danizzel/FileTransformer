package de.FileTransormer.FileTransformer;

import java.util.prefs.Preferences;

import javax.swing.UIManager;
import javax.swing.UnsupportedLookAndFeelException;

import com.formdev.flatlaf.FlatDarkLaf;
import com.formdev.flatlaf.FlatIntelliJLaf;
import com.formdev.flatlaf.themes.FlatMacDarkLaf;

//Klasse zum Abspeichern der Themes (sowie das letzte Theme beim öffnen der App wieder reinalden)
public class PrefsManager {

	private static Preferences prefs = Preferences.userNodeForPackage(PrefsManager.class);
	
	//aktuelle Theme abspeichern -> wenn User ein neues Theme auswählt
	public static void saveSettings(String themChange) {
		prefs.put("theme", themChange);
	}
	
	
	//Wird zum start des Programms (FileConverter) gecallt
	public static void loadAndApplySettings() {
		
		//falls ein Theme abgespeichert wurde hole dieses "theme" -> siehe oben saveSettings
		//sonst lade als Standard "darklaf" rein
		String themeSetting = prefs.get("theme", "darklaf");
		
		
		switch(themeSetting) {
		case "darklaf" :
			try {
				UIManager.setLookAndFeel(new FlatDarkLaf());
			} catch (UnsupportedLookAndFeelException e) {
				e.printStackTrace();
			}
			break;
		case "lightlaf" :
			try {
				UIManager.setLookAndFeel(new FlatIntelliJLaf());
			} catch (UnsupportedLookAndFeelException e) {
				e.printStackTrace();
			}
			break;
			
		case "midnightlaf":
			try {
				UIManager.setLookAndFeel(new FlatMacDarkLaf());
			} catch (UnsupportedLookAndFeelException e) {
				e.printStackTrace();
			}
			break;
		}
	}
}
