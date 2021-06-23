package dev.codenmore.tilegame.gfx;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.Objects;

public class ImageLoader {

    // this class will simply load in the images


    // images will be stored inside a 'Buffered image' object
    public static BufferedImage loadImage(String path) {

        try {
            return ImageIO.read(Objects.requireNonNull(ImageLoader.class.getResource(path))); // this returns the buffered image object for the loaded image

        } catch (IOException e) {
            e.printStackTrace();
            System.exit(1);
        }
        return null;
    }



}
