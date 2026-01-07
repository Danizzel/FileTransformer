package de.FileTransormer.FileTransformer;



import javax.swing.*;
import javax.swing.border.EmptyBorder;

import com.formdev.flatlaf.FlatDarkLaf;
import com.formdev.flatlaf.FlatIntelliJLaf;
import com.formdev.flatlaf.FlatLaf;
import com.formdev.flatlaf.themes.FlatMacDarkLaf;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;

public class FileConverter extends JFrame {

    /**
	 * 
	 */
	private static final long serialVersionUID = 7652734068881376422L;
	
	// Globale Variablen für UI-Steuerung
    private JPanel cardsPanel;
    private CardLayout cardLayout;
    private JButton switchModeButton;
    
	private File selectedFile;
    private JLabel selectedFileLabel;
    private JComboBox<String> formatComboBox;

    public FileConverter() {
        // Fenstereinstellungen
    	
    	PrefsManager.loadAndApplySettings();
    	
        setTitle("Datei-Konverter");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(600, 300);
        setLocationRelativeTo(null); // Fenster zentrieren
        
        //Menu Bereich
        
        JPanel menuPanel = new JPanel(new FlowLayout(FlowLayout.LEFT));

        JMenuBar menuBar = new JMenuBar();
        JMenu themeMenue = new JMenu("Theme");
        
     // Button zum Wechseln des Modus
        switchModeButton = new JButton("Zum Komprimierer wechseln");
        switchModeButton.setFocusPainted(false);
        switchModeButton.setBorderPainted(false);
        switchModeButton.setContentAreaFilled(false);
        
        menuBar.add(themeMenue);
        menuBar.add(Box.createHorizontalGlue()); // Schiebt den Button nach rechts (optional)
        menuBar.add(switchModeButton);
      
     // Theme Items
        JMenuItem darkMode = new JMenuItem("Dark Theme");
        JMenuItem lightMode = new JMenuItem("Light Mode");
        JMenuItem midnightMode = new JMenuItem("Midnight Theme");
        themeMenue.add(darkMode);
        themeMenue.add(midnightMode);
        themeMenue.add(lightMode);
        setJMenuBar(menuBar);
        
        
     // --- 3. Haupt-Layout (CardLayout) ---
        cardLayout = new CardLayout();
        cardsPanel = new JPanel(cardLayout);
        
     // Wir erstellen zwei separate Panels
        JPanel converterPanel = createConverterPanel();
        JPanel compressionPanel = createCompressionPanel();
        
     // Hinzufügen zum "Stapel" (Cards)
        cardsPanel.add(converterPanel, "CONVERTER");
        cardsPanel.add(compressionPanel, "COMPRESSOR");
        
        add(cardsPanel);
        
        
     // --- 4. Logik für Modus-Wechsel ---
        switchModeButton.addActionListener(e -> {
            // Prüfen, welche Card gerade sichtbar ist? (Einfacher Toggle hier)
            if (switchModeButton.getText().equals("Zum Komprimierer wechseln")) {
                cardLayout.show(cardsPanel, "COMPRESSOR");
                switchModeButton.setText("Zurück zum Konverter");
            } else {
                cardLayout.show(cardsPanel, "CONVERTER");
                switchModeButton.setText("Zum Komprimierer wechseln");
            }
        });
        
     // --- 5. Theme Listener ---
        darkMode.addActionListener(e -> changeTheme("darklaf"));
        lightMode.addActionListener(e -> changeTheme("lightlaf"));
        midnightMode.addActionListener(e -> changeTheme("midnightlaf"));
        
    }
    
    /**
     * Erstellt das Panel für die normale Dateikonvertierung
     */
    private JPanel createConverterPanel() {
        JPanel panel = new JPanel(new BorderLayout(10, 10));
        panel.setBorder(new EmptyBorder(20, 20, 20, 20));

        // NORD: Dateiauswahl
        JPanel topPanel = new JPanel(new FlowLayout(FlowLayout.LEFT));
        JButton selectFileButton = new JButton("Datei auswählen...");
        selectedFileLabel = new JLabel("Keine Datei ausgewählt.");
        topPanel.add(selectFileButton);
        topPanel.add(selectedFileLabel);
        panel.add(topPanel, BorderLayout.NORTH);

        // ZENTRUM: Optionen
        JPanel centerPanel = new JPanel(new FlowLayout(FlowLayout.LEFT));
        JLabel formatLabel = new JLabel("Konvertieren nach:");
        String[] formats = {"DOCX->PDF","PDF->DOCX","PDF->PNG", "PDF->JPG","Image->PDF","PPTX->PDF","PDF->PPTX","Image->TIFF", "TIFF->PNG"};
        formatComboBox = new JComboBox<>(formats);
        centerPanel.add(formatLabel);
        centerPanel.add(formatComboBox);
        panel.add(centerPanel, BorderLayout.CENTER);

        // SÜD: Action Button
        JPanel bottomPanel = new JPanel(new FlowLayout(FlowLayout.CENTER));
        JButton convertButton = new JButton("Konvertieren und Speichern unter...");
        bottomPanel.add(convertButton);
        panel.add(bottomPanel, BorderLayout.SOUTH);

        // Listener für dieses Panel
        selectFileButton.addActionListener(e -> {
            JFileChooser fileChooser = new JFileChooser();
            if (fileChooser.showOpenDialog(FileConverter.this) == JFileChooser.APPROVE_OPTION) {
                selectedFile = fileChooser.getSelectedFile();
                selectedFileLabel.setText("Ausgewählt: " + selectedFile.getName());
            }
        });

        convertButton.addActionListener(e -> performConversion());

        return panel;
    }
    
    
    /**
     * Erstellt das Panel für die Bildkomprimierung (NEU)
     */
    private JPanel createCompressionPanel() {
        JPanel panel = new JPanel(new BorderLayout(10, 10));
        panel.setBorder(new EmptyBorder(20, 20, 20, 20));
        
        // Überschrift zur Orientierung
        JLabel header = new JLabel("Bild-Komprimierungs-Modus");
        header.setFont(header.getFont().deriveFont(Font.BOLD, 16f));
        panel.add(header, BorderLayout.NORTH);

        // Inhalt
        JPanel centerPanel = new JPanel(new GridLayout(3, 1, 10, 10));
        
        JPanel fileSelectPanel = new JPanel(new FlowLayout(FlowLayout.LEFT));
        JButton selectImgBtn = new JButton("Bild wählen...");
        JLabel imgLabel = new JLabel("Kein Bild.");
        fileSelectPanel.add(selectImgBtn);
        fileSelectPanel.add(imgLabel);
        
        JPanel sliderPanel = new JPanel(new FlowLayout(FlowLayout.LEFT));
        JLabel qualityLabel = new JLabel("Qualität (50%): ");
        JSlider qualitySlider = new JSlider(0, 100, 50);
        qualitySlider.addChangeListener(e -> qualityLabel.setText("Qualität (" + qualitySlider.getValue() + "%): "));
        sliderPanel.add(qualityLabel);
        sliderPanel.add(qualitySlider);

        centerPanel.add(fileSelectPanel);
        centerPanel.add(sliderPanel);
        
        panel.add(centerPanel, BorderLayout.CENTER);

        // Action Button
        JButton compressButton = new JButton("Komprimieren starten");
        JPanel bottomPanel = new JPanel();
        bottomPanel.add(compressButton);
        panel.add(bottomPanel, BorderLayout.SOUTH);
        
        // Dummy Listener für Komprimierung
        selectImgBtn.addActionListener(e -> {
            JFileChooser fc = new JFileChooser();
            if(fc.showOpenDialog(FileConverter.this) == JFileChooser.APPROVE_OPTION){
                imgLabel.setText(fc.getSelectedFile().getName());
            }
        });
        
        compressButton.addActionListener(e -> {
            JOptionPane.showMessageDialog(this, "Hier startet die Komprimierungs-Logik!");
        });

        return panel;
    }
    
    private void performConversion() {
    	
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
            if(selectedFormat.equals("PDF->DOCX")) {
            	checkIfFileConversion = WordToPdf.pdfToWord(selectedFile.getAbsolutePath(), fileToSave.getAbsolutePath());
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
            if(selectedFormat.equals("PDF->PPTX")) {
            	checkIfFileConversion = PPTXToPDF.pdfToPPTX(selectedFile.getAbsolutePath(), fileToSave.getAbsolutePath());
            }
            if(selectedFormat.equals("Image->TIFF")) {
            	checkIfFileConversion = Image.ImageToTiff(selectedFile.getAbsolutePath(), fileToSave.getAbsolutePath());
            }
            if(selectedFormat.equals("TIFF->PNG")) {
            	checkIfFileConversion = Image.tiffToImage(selectedFile.getAbsolutePath(), fileToSave.getAbsolutePath());
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
    
    
    private void changeTheme(String themeKey) {
        // 1. Speichern
        PrefsManager.saveSettings(themeKey);
        // 2. Anwenden (lädt das gerade Gespeicherte und aktualisiert die UI)
        PrefsManager.loadAndApplySettings();
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

    public void start() {
        // Swing-Anwendung im Event-Dispatch-Thread starten
        SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
                new FileConverter().setVisible(true);
            }
        });
    }
}