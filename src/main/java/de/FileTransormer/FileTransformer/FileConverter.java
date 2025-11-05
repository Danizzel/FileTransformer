package de.FileTransormer.FileTransformer;



import javax.swing.*;
import javax.swing.border.EmptyBorder;

import com.formdev.flatlaf.FlatDarculaLaf;
import com.formdev.flatlaf.FlatDarkLaf;
import com.formdev.flatlaf.FlatIntelliJLaf;
import com.formdev.flatlaf.FlatLaf;
import com.formdev.flatlaf.themes.FlatMacDarkLaf;
import com.formdev.flatlaf.themes.FlatMacLightLaf;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;

public class FileConverter extends JFrame {

    private File selectedFile;
    private JLabel selectedFileLabel;
    private JComboBox<String> formatComboBox;

    public FileConverter() {
        // Fenstereinstellungen
    	
    	PrefsManager.loadAndApplySettings();
    	
        setTitle("Datei-Konverter");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(500, 250);
        setLocationRelativeTo(null); // Fenster zentrieren

        // Haupt-Panel erstellen
        JPanel mainPanel = new JPanel(new BorderLayout(10, 10));
        mainPanel.setBorder(new EmptyBorder(20, 20, 20, 20));
        add(mainPanel);
        
        //Menu Bereich
        
        JPanel menuPanel = new JPanel(new FlowLayout(FlowLayout.LEFT));

        JMenuBar menuBar = new JMenuBar();
        JMenu themeMenue = new JMenu("Theme");
        menuBar.add(themeMenue);
        
        JMenuItem darkMode = new JMenuItem("Dark Theme");
        JMenuItem lightMode = new JMenuItem("Light Mode");
        JMenuItem midnightMode = new JMenuItem("Midnight Theme");
        
        themeMenue.add(darkMode);
        themeMenue.add(midnightMode);
        themeMenue.add(lightMode);
        
        menuPanel.add(menuBar);
        
        setJMenuBar(menuBar);

        // -- NORD-Bereich: Dateiauswahl --
        JPanel topPanel = new JPanel(new FlowLayout(FlowLayout.LEFT));
        
        
        JButton selectFileButton = new JButton("Datei auswählen...");
        selectedFileLabel = new JLabel("Keine Datei ausgewählt.");
        
        topPanel.add(selectFileButton);
        topPanel.add(selectedFileLabel);
        
        mainPanel.add(topPanel, BorderLayout.NORTH);

        // -- ZENTRUM: Konvertierungsoptionen --
        JPanel centerPanel = new JPanel(new FlowLayout(FlowLayout.LEFT));
        JLabel formatLabel = new JLabel("Konvertieren nach:");
        String[] formats = {"DOCX->PDF","PDF->PNG", "PDF->JPG","Image->PDF"};
        formatComboBox = new JComboBox<>(formats);
        centerPanel.add(formatLabel);
        centerPanel.add(formatComboBox);
        mainPanel.add(centerPanel, BorderLayout.CENTER);

        // -- SÜD-Bereich: Konvertierungs-Button --
        JPanel bottomPanel = new JPanel(new FlowLayout(FlowLayout.CENTER));
        JButton convertButton = new JButton("Konvertieren und Speichern unter...");
        bottomPanel.add(convertButton);
        mainPanel.add(bottomPanel, BorderLayout.SOUTH);

        // --- Action Listeners ---

        // Aktion für den "Datei auswählen"-Button
        selectFileButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                JFileChooser fileChooser = new JFileChooser();
                int result = fileChooser.showOpenDialog(FileConverter.this);
                if (result == JFileChooser.APPROVE_OPTION) {
                    selectedFile = fileChooser.getSelectedFile();
                    selectedFileLabel.setText("Ausgewählt: " + selectedFile.getName());
                }
            }
        });
        
        
        darkMode.addActionListener(e -> {
        	FlatDarkLaf.setup();
        	FlatLaf.updateUI();
        	PrefsManager.saveSettings("darklaf");
        });
        
        lightMode.addActionListener(e -> {
        	FlatIntelliJLaf.setup();
        	FlatLaf.updateUI();
        	PrefsManager.saveSettings("lightlaf");
        });
        midnightMode.addActionListener(e -> {
        	FlatMacDarkLaf.setup();
        	FlatLaf.updateUI();
        	PrefsManager.saveSettings("midnightlaf");
        });

        // Aktion für den "Konvertieren"-Button
        convertButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (selectedFile == null) {
                    JOptionPane.showMessageDialog(FileConverter.this,
                            "Bitte wählen Sie zuerst eine Datei aus.",
                            "Fehler",
                            JOptionPane.ERROR_MESSAGE);
                    return;
                }

                String selectedFormat = (String) formatComboBox.getSelectedItem();

                JFileChooser saveFileChooser = new JFileChooser();
                saveFileChooser.setDialogTitle("Speicherort auswählen");
                // Dateinamen vorschlagen
                saveFileChooser.setSelectedFile(new File(removeFileExtension(selectedFile.getName()) + "." + removePfeil(selectedFormat.toLowerCase())));

                int userSelection = saveFileChooser.showSaveDialog(FileConverter.this);

                if (userSelection == JFileChooser.APPROVE_OPTION) {
                    File fileToSave = saveFileChooser.getSelectedFile();
                    
                    boolean checkIfFileConversion = false;
                  
                    
                    if(selectedFormat.equals("DOCX->PDF")) {
                    	checkIfFileConversion = WordToPdf.wordToPdf(selectedFile.getAbsolutePath(), fileToSave.getAbsolutePath());
                    }
                    if(selectedFormat.equals("PDF->PNG")) {
                    	checkIfFileConversion = PdfToImage.pdfToPNG(selectedFile.getAbsolutePath(), fileToSave.getAbsolutePath());
                    }
                    if(selectedFormat.equals("PDF->JPG")) {
                    	checkIfFileConversion = PdfToImage.pdfToJPG(selectedFile.getAbsolutePath(), fileToSave.getAbsolutePath());
                    }
                    if(selectedFormat.equals("Image->PDF")) {
                    	checkIfFileConversion = ImageToPDF.imageToPdf(selectedFile.getAbsolutePath(), fileToSave.getAbsolutePath());
                    }
                    if(selectedFormat.equals("PPTX->PDF")){
                    	checkIfFileConversion = PPTXToPDF.pptxToPDF(selectedFile.getAbsolutePath(), fileToSave.getAbsolutePath());
                    }
                   
                    
                    if(checkIfFileConversion) {
                    	// Erfolgsmeldung anzeigen
                        JOptionPane.showMessageDialog(FileConverter.this,
                                "Datei erfolgreich nach " + fileToSave.getAbsolutePath() + " konvertiert!",
                                "Konvertierung erfolgreich",
                                JOptionPane.INFORMATION_MESSAGE);
                    }else {
                    	JOptionPane.showMessageDialog(FileConverter.this,
                    			"Da ist was schief gegangen. Prüfe ob das Dateiformat der Datei entspricht",
                    			"Konvertierung nicht erfolgreich",JOptionPane.INFORMATION_MESSAGE);
                    }
                }
            }
        });
    }

    /**
     * Hilfsmethode, um die Dateiendung eines Namens zu entfernen.
     */
    private String removeFileExtension(String filename) {
        int lastDot = filename.lastIndexOf('.');
        if (lastDot > 0) {
            return filename.substring(0, lastDot);
        }
        return filename;
    }
    
    private String removePfeil(String filename) {
        int lastDot = filename.lastIndexOf('>');
        if (lastDot > 0) {
            return filename.substring(lastDot+1, filename.length());
        }
        return filename;
    }

    public static void start() {
    	
    	/*
    	 * TODO:FlatLaf nutzen und das Design anpassen sowie dem Nutzer die Möglichkeit geben dieses zu ändern
    	 */
    	
        // Swing-Anwendung im Event-Dispatch-Thread starten
        SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
                new FileConverter().setVisible(true);
            }
        });
    }
}