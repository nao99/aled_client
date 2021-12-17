package org.ndbs.aled.client.domain;

/**
 * Pixel class
 *
 * @author  Nikolai Osipov <nao99.dev@gmail.com>
 * @version 1.0.0
 * @since   2021-12-16
 */
public class Pixel {
    private static final int MIN_CHANNEL_VALUE = 0;
    private static final int MAX_CHANNEL_VALUE = 255;

    private final int a;
    private final int r;
    private final int g;
    private final int b;

    private Pixel(int a, int r, int g, int b) {
        if (a < MIN_CHANNEL_VALUE || a > MAX_CHANNEL_VALUE) {
            var errorMessagePattern = "A value should be between %d and %d, but %d given";
            var errorMessage = String.format(errorMessagePattern, MIN_CHANNEL_VALUE, MAX_CHANNEL_VALUE, a);

            throw new IllegalArgumentException(errorMessage);
        }

        if (r < MIN_CHANNEL_VALUE || r > MAX_CHANNEL_VALUE) {
            var errorMessagePattern = "R value should be between %d and %d, but %d given";
            var errorMessage = String.format(errorMessagePattern, MIN_CHANNEL_VALUE, MAX_CHANNEL_VALUE, r);

            throw new IllegalArgumentException(errorMessage);
        }

        if (g < MIN_CHANNEL_VALUE || g > MAX_CHANNEL_VALUE) {
            var errorMessagePattern = "G value should be between %d and %d, but %d given";
            var errorMessage = String.format(errorMessagePattern, MIN_CHANNEL_VALUE, MAX_CHANNEL_VALUE, g);

            throw new IllegalArgumentException(errorMessage);
        }

        if (b < MIN_CHANNEL_VALUE || b > MAX_CHANNEL_VALUE) {
            var errorMessagePattern = "B value should be between %d and %d, but %d given";
            var errorMessage = String.format(errorMessagePattern, MIN_CHANNEL_VALUE, MAX_CHANNEL_VALUE, b);

            throw new IllegalArgumentException(errorMessage);
        }

        this.a = a;
        this.r = r;
        this.g = g;
        this.b = b;
    }

    public static Pixel create(int a, int r, int g, int b) {
        return new Pixel(a, r, g, b);
    }

    public static Pixel createFromEncoded(int encodedPixel) {
        var decodedColor = decode(encodedPixel);

        var a = decodedColor[0];
        var r = decodedColor[1];
        var g = decodedColor[2];
        var b = decodedColor[3];

        return new Pixel(a, r, g, b);
    }

    public static int[] decode(int encodedColor) {
        return new int[] {
            (encodedColor >> 24) & 0xff,
            (encodedColor >> 16) & 0xff,
            (encodedColor >> 8) & 0xff,
            encodedColor & 0xff
        };
    }

    public int encode() {
        return (a & 0xff) << 24
            | (r & 0xff) << 16
            | (g & 0xff) << 8
            | (b & 0xff);
    }

    public int getA() {
        return a;
    }

    public int getR() {
        return r;
    }

    public int getG() {
        return g;
    }

    public int getB() {
        return b;
    }

    @Override
    public boolean equals(Object other) {
        if (this == other) {
            return true;
        }

        if (other == null || getClass() != other.getClass()) {
            return false;
        }

        var pixel = (Pixel) other;

        return a == pixel.a
            && r == pixel.r
            && g == pixel.g
            && b == pixel.b;
    }

    @Override
    public String toString() {
        return "Pixel{" +
            "a=" + a +
            ", r=" + r +
            ", g=" + g +
            ", b=" + b +
            '}';
    }
}
