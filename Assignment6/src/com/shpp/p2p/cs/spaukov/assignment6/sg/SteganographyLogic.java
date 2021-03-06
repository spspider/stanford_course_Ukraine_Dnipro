package com.shpp.p2p.cs.spaukov.assignment6.sg;

import acm.graphics.*;

import java.awt.*;

public class SteganographyLogic {
    /**
     * Given a GImage containing a hidden message, finds the hidden message
     * contained within it and returns a boolean array containing that message.
     * <p/>
     * A message has been hidden in the input image as follows.  For each pixel
     * in the image, if that pixel has a red component that is an even number,
     * the message value at that pixel is false.  If the red component is an odd
     * number, the message value at that pixel is true.
     *
     * @param source The image containing the hidden message.
     * @return The hidden message, expressed as a boolean array.
     */
    public static boolean[][] findMessage(GImage source) {
        /* TODO: Implement this! */
        int[][] pixels = source.getPixelArray();

        boolean[][] pixelsForReturn = new boolean[pixels.length][pixels[0].length];
        for (int row = 0; row < pixels.length; row++) {
            for (int col = 0; col < pixels[row].length; col++) {
                /* Extract the green and blue components and write them back, leaving the
                 * red component out.
                 */
                int red = GImage.getRed(pixels[row][col]);
                pixelsForReturn[row][col] = red % 2 != 0;//this return pixel with message
            }
        }

        return pixelsForReturn;
    }

    /**
     * Hides the given message inside the specified image.
     * <p/>
     * The image will be given to you as a GImage of some size, and the message will
     * be specified as a boolean array of pixels, where each white pixel is denoted
     * false and each black pixel is denoted true.
     * <p/>
     * The message should be hidden in the image by adjusting the red channel of all
     * the pixels in the original image.  For each pixel in the original image, you
     * should make the red channel an even number if the message color is white at
     * that position, and odd otherwise.
     * <p/>
     * You can assume that the dimensions of the message and the image are the same.
     * <p/>
     *
     * @param message The message to hide.
     * @param source  The source image.
     * @return A GImage whose pixels have the message hidden within it.
     */
    public static GImage hideMessage(boolean[][] message, GImage source) {
        /* TODO: Implement this! */
        int[][] pixels = source.getPixelArray();
        int[][] imageInt = new int[pixels.length][pixels[0].length];
        for (int row = 0; row < pixels.length; row++) {
            for (int col = 0; col < pixels[row].length; col++) {
                /*divide RGB into components*/
                int green = GImage.getGreen(pixels[row][col]);
                int blue = GImage.getBlue(pixels[row][col]);
                int red = GImage.getRed(pixels[row][col]);
                /*if the pixel is marked*/
                if (message[row][col]) {
                    if (red % 2 == 0) {
                        /*add one(to mask it)*/
                        red++;
                    }
                }else{
                    if (red % 2 != 0) {
                        /*delete one pixel*/
                        red--;
                    }
                }
                /*create another GImage and draw picture*/
                imageInt[row][col] = GImage.createRGBPixel(red,green, blue);

            }
        }


        return new GImage(imageInt);
    }
}
