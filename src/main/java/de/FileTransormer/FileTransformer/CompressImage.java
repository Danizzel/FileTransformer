package de.FileTransormer.FileTransformer;

import org.apache.commons.io.FilenameUtils;

import javax.imageio.IIOImage;
import javax.imageio.ImageIO;
import javax.imageio.ImageWriteParam;
import javax.imageio.ImageWriter;
import javax.imageio.stream.ImageOutputStream;
import java.awt.image.BufferedImage;
import java.io.File;
import java.util.Iterator;

public class CompressImage {

    public static boolean compress(String filelocation, String fileOutputLocation, int compressionValue){

        try{
            BufferedImage inputImage = ImageIO.read(new File(filelocation));
            String fileFormat = FilenameUtils.getExtension(filelocation);
            Iterator<ImageWriter> writers = ImageIO.getImageWritersByFormatName(fileFormat);
            ImageWriter writer = writers.next();

            File outputFile = new File(fileOutputLocation);
            ImageOutputStream outputStream = ImageIO.createImageOutputStream(outputFile);
            writer.setOutput(outputStream);

            ImageWriteParam params = writer.getDefaultWriteParam();
            params.setCompressionMode(ImageWriteParam.MODE_EXPLICIT);
            float value = (float) compressionValue / 100;
            params.setCompressionQuality(value);

            writer.write(null, new IIOImage(inputImage, null, null), params);

            outputStream.close();
            writer.dispose();

            return true;

        }catch(Exception ex) {
            return false;
        }
    }
}
