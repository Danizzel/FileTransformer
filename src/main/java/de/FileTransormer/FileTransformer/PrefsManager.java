package de.FileTransormer.FileTransformer;

import java.util.prefs.Preferences;

import javax.swing.JFrame;
import javax.swing.UIManager;
import javax.swing.UnsupportedLookAndFeelException;

import com.formdev.flatlaf.FlatDarkLaf;
import com.formdev.flatlaf.FlatIntelliJLaf;
import com.formdev.flatlaf.FlatLaf;
import com.formdev.flatlaf.themes.FlatMacDarkLaf;

public class PrefsManager {

	private static Preferences prefs = Preferences.userNodeForPackage(PrefsManager.class);
	
	public static void saveSettings(String themChange) {
		prefs.put("theme", themChange);
	}
	
	public static void loadAndApplySettings() {
		
		String themeSetting = prefs.get("theme", "darklaf");
		
		
		switch(themeSetting) {
		case "darklaf" :
			try {
				UIManager.setLookAndFeel(new FlatDarkLaf());
			} catch (UnsupportedLookAndFeelException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			break;
		case "lightlaf" :
			try {
				UIManager.setLookAndFeel(new FlatIntelliJLaf());
			} catch (UnsupportedLookAndFeelException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			break;
			
		case "midnightlaf":
			try {
				UIManager.setLookAndFeel(new FlatMacDarkLaf());
			} catch (UnsupportedLookAndFeelException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			break;
		}
	}
}
