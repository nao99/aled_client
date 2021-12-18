package org.ndbs.aled.client.domain.model;

/**
 * DiodeRgb class
 *
 * @author  Nikolai Osipov <nao99.dev@gmail.com>
 * @version 1.0.0
 * @since   2021-12-18
 */
public class DiodeRgb {
    private final int id;
    private final Pixel pixel;

    private DiodeRgb(int id, Pixel pixel) {
        this.id = id;
        this.pixel = pixel;
    }

    public static DiodeRgb create(int id, Pixel pixel) {
        return new DiodeRgb(id, pixel);
    }

    public int getId() {
        return id;
    }

    public Pixel getPixel() {
        return pixel;
    }

    @Override
    public String toString() {
        return "DiodeRgb{" +
            "id=" + id +
            ", pixel=" + pixel +
            '}';
    }
}
