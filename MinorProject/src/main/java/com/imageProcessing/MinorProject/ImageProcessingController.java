package com.imageProcessing.MinorProject;

import milchreis.imageprocessing.*;
import org.apache.tomcat.util.http.fileupload.ByteArrayOutputStream;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;
import processing.core.PApplet;
import processing.core.PImage;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;



@RestController
public class ImageProcessingController {
    @PostMapping(value = "/convertToGrayscale", consumes = MediaType.MULTIPART_FORM_DATA_VALUE,produces = MediaType.IMAGE_PNG_VALUE)
    public byte[] convertToGrayscale(@RequestParam("imageFile") MultipartFile imageFile) throws IOException {

        // Load image into PImage object using loadImage() method
        PImage inputPImage = loadImage(imageFile.getInputStream());

        // Convert PImage to grayscale using grayscale() method from processing-imageprocessing library
        PImage outputPImage = Grayscale.apply(inputPImage);

        // Convert output PImage to BufferedImage object using getNative() method
        BufferedImage outputImage = (BufferedImage) outputPImage.getNative();

        // Write the output BufferedImage to ByteArrayOutputStream
        ByteArrayOutputStream bos = new ByteArrayOutputStream();
        ImageIO.write(outputImage, "png", bos);

        // Return the ByteArrayOutputStream as byte[]
        return bos.toByteArray();
    }
    @PostMapping(value = "/cannyEdgeDetector", consumes = MediaType.MULTIPART_FORM_DATA_VALUE,produces = MediaType.IMAGE_PNG_VALUE)
    public byte[] cannyEdgeDetector(@RequestParam("imageFile") MultipartFile imageFile) throws IOException {

        // Load image into PImage object using loadImage() method
        PImage inputPImage = loadImage(imageFile.getInputStream());

        // Convert PImage to grayscale using grayscale() method from processing-imageprocessing library
        PImage outputPImage = CannyEdgeDetector.apply(inputPImage);

        // Convert output PImage to BufferedImage object using getNative() method
        BufferedImage outputImage = (BufferedImage) outputPImage.getNative();

        // Write the output BufferedImage to ByteArrayOutputStream
        ByteArrayOutputStream bos = new ByteArrayOutputStream();
        ImageIO.write(outputImage, "png", bos);

        // Return the ByteArrayOutputStream as byte[]
        return bos.toByteArray();
    }
    @PostMapping(value = "/sobelEdgeDetector", consumes = MediaType.MULTIPART_FORM_DATA_VALUE,produces = MediaType.IMAGE_PNG_VALUE)
    public byte[] sobelEdgeDetector(@RequestParam("imageFile") MultipartFile imageFile) throws IOException {

        // Load image into PImage object using loadImage() method
        PImage inputPImage = loadImage(imageFile.getInputStream());

        // Convert PImage to grayscale using grayscale() method from processing-imageprocessing library
        PImage outputPImage = SobelEdgeDetector.apply(inputPImage);

        // Convert output PImage to BufferedImage object using getNative() method
        BufferedImage outputImage = (BufferedImage) outputPImage.getNative();

        // Write the output BufferedImage to ByteArrayOutputStream
        ByteArrayOutputStream bos = new ByteArrayOutputStream();
        ImageIO.write(outputImage, "png", bos);

        // Return the ByteArrayOutputStream as byte[]
        return bos.toByteArray();
    }
    @PostMapping(value = "/Erosion",consumes = MediaType.MULTIPART_FORM_DATA_VALUE,produces = MediaType.IMAGE_PNG_VALUE)
    public byte[] Erosion(@RequestParam("imageFile") MultipartFile imageFile,@RequestParam("myInteger") int myInteger) throws IOException {

        // Load image into PImage object using loadImage() method
        PImage inputPImage = loadImage(imageFile.getInputStream());

        // Convert PImage to grayscale using grayscale() method from processing-imageprocessing library
        PImage outputPImage = Erosion.apply(inputPImage,myInteger);

        // Convert output PImage to BufferedImage object using getNative() method
        BufferedImage outputImage = (BufferedImage) outputPImage.getNative();

        // Write the output BufferedImage to ByteArrayOutputStream
        ByteArrayOutputStream bos = new ByteArrayOutputStream();
        ImageIO.write(outputImage, "png", bos);

        // Return the ByteArrayOutputStream as byte[]
        return bos.toByteArray();
    }
    @PostMapping(value = "/Dilation",consumes = MediaType.MULTIPART_FORM_DATA_VALUE,produces = MediaType.IMAGE_PNG_VALUE)
    public byte[] Dilation(@RequestParam("imageFile") MultipartFile imageFile,@RequestParam("myInteger") int myInteger) throws IOException {

        // Load image into PImage object using loadImage() method
        PImage inputPImage = loadImage(imageFile.getInputStream());

        // Convert PImage to grayscale using grayscale() method from processing-imageprocessing library
        PImage outputPImage = Dilation.apply(inputPImage,myInteger);

        // Convert output PImage to BufferedImage object using getNative() method
        BufferedImage outputImage = (BufferedImage) outputPImage.getNative();

        // Write the output BufferedImage to ByteArrayOutputStream
        ByteArrayOutputStream bos = new ByteArrayOutputStream();
        ImageIO.write(outputImage, "png", bos);

        // Return the ByteArrayOutputStream as byte[]
        return bos.toByteArray();
    }
    private PImage loadImage(InputStream inputStream) throws IOException {
        BufferedImage image = ImageIO.read(inputStream);
        PImage pImage = new PImage(image.getWidth(), image.getHeight(), PApplet.RGB);
        pImage.loadPixels();
        image.getRGB(0, 0, pImage.width, pImage.height, pImage.pixels, 0, pImage.width);
        pImage.updatePixels();
        return pImage;
    }






}
