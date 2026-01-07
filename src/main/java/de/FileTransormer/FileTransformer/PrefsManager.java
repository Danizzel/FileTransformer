package de.FileTransormer.FileTransformer;

import java.util.prefs.Preferences;

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

        switch (themeSetting) {
        case "darklaf":
            try {
                UIManager.setLookAndFeel(new FlatDarkLaf());
            } catch (UnsupportedLookAndFeelException e) {
                e.printStackTrace();
            }
            break;
        case "lightlaf":
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
        
        // NEU: Damit sich das offene Fenster sofort aktualisiert!
        FlatLaf.updateUI(); 
    }
}
