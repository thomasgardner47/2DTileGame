package dev.codenmore.tilegame.gfx;

import java.awt.image.BufferedImage;

public class Assets {

    // these variables are representing the width and height of the drid spaces for each tile
    private static final int width = 32, height = 32;

    public static BufferedImage player, dirt, grass, stone, tree;

    public static void init() {
        SpriteSheet sheet = new SpriteSheet((ImageLoader.loadImage("/textures/sheet.png")));

        player = sheet.crop(0, 0, width, height);
        dirt = sheet.crop(width, 0, width, height);
        grass = sheet.crop(width * 2, 0, width, height);
        stone = sheet.crop(width * 3, 0, width, height);
        tree = sheet.crop(0, height, width, height);
    }

    // instead of hardcoding the co-ordinates for each tile , we have create and add variables which can do this more efficiently
}
