package org.ndbs.aled.client.domain;

/**
 * Screenshot class
 *
 * @author  Nikolai Osipov <nao99.dev@gmail.com>
 * @version 1.0.0
 * @since   2021-12-16
 */
public class Screenshot {
    private final int width;
    private final int height;
    private final Pixel[][] pixels;

    private Screenshot(int width, int height, Pixel[][] pixels) {
        this.width = width;
        this.height = height;
        this.pixels = pixels;
    }

    public static Screenshot create(int width, int height, Pixel[][] pixels) {
        return new Screenshot(width, height, pixels);
    }

    public int getWidth() {
        return width;
    }

    public int getHeight() {
        return height;
    }

    public Pixel getPixel(int x, int y) {
        return pixels[x][y];
    }
}
