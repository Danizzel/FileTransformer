# FileTransformer

Eine kleine Java-Swing-Anwendung zur Konvertierung von Dateien zwischen verschiedenen Formaten – mit einfacher GUI und mehreren Themes (Dark / Light / Midnight via [FlatLaf](https://www.formdev.com/flatlaf/)).

## Features

Unterstützte Konvertierungen:

- DOCX → PDF
- PDF → DOCX
- PDF → PNG
- PDF → JPG
- Image → PDF
- PPTX → PDF
- PDF → PPTX
- Image → TIFF
- TIFF → PNG

Zusätzlich:

- Persistente Theme-Auswahl (Dark / Light / Midnight)
- Datei-Auswahl und Speicherort über native Dialoge
- Automatischer Vorschlag des Dateinamens beim Speichern

## Tech-Stack

- Java 8
- Maven (Shade-Plugin für Fat-JAR)
- Apache POI, PDFBox, iText, Aspose PDF, Spire Presentation, TwelveMonkeys ImageIO
- FlatLaf für das UI-Theming

## Build

```bash
mvn clean package
```

Das ausführbare Fat-JAR liegt anschließend unter `target/`.

## Ausführen

```bash
java -jar target/FileTransformer-0.0.1-SNAPSHOT.jar
```

Oder direkt die Klasse `de.FileTransormer.FileTransformer.App` aus der IDE starten.

## Projektstruktur

```
src/main/java/de/FileTransormer/FileTransformer/
├── App.java            # Einstiegspunkt
├── FileConverter.java  # Haupt-GUI
├── WordToPdf.java      # DOCX <-> PDF
├── PdfToImage.java     # PDF -> PNG/JPG
├── ImageToPDF.java     # Bild -> PDF
├── PPTXToPDF.java      # PPTX <-> PDF
├── Image.java          # Image <-> TIFF
├── PrefsManager.java   # Theme-Persistenz
└── ToolClass.java      # Hilfsfunktionen
```
