package org.ndbs.aled.client.util;

/**
 * PixelUtil class
 *
 * @author  Nikolai Osipov <nao99.dev@gmail.com>
 * @version 1.0.0
 * @since   2021-12-17
 */
public class PixelUtil {
    /**
     * Converts a color in RGB palette into HSV
     *
     * @param r an R channel
     * @param g a G channel
     * @param b a B channel
     *
     * @return the same color in HSV model
     */
    public static double[] rgbToHsv(double r, double g, double b) {
        double min = Math.min(Math.min(r, g), b);
        double max = Math.max(Math.max(r, g), b);

        double h = 0.0;
        double s = 0.0;
        double v = max;

        var delta = max - min;

        if (max == 0) {
            return new double[] {h, s, v};
        }

        s = delta / max;

        if (r == max) {
            h = (g - b) / delta;
        } else if (g == max) {
            h = 2 + (b - r) / delta;
        } else {
            h = 4 + (r - g) / delta;
        }

        h *= 60;

        if (h < 0) {
            h += 360;
        }

        s = s * 100.0;
        v = (v / 256.0) * 100.0;

        return new double[] {h, s, v};
    }
}
