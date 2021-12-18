package org.ndbs.aled.client.domain.model;

import org.ndbs.aled.client.util.PixelUtil;

/**
 * Diode class
 *
 * @author  Nikolai Osipov <nao99.dev@gmail.com>
 * @version 1.0.0
 * @since   2021-12-17
 */
public class Diode {
    private final int id;
    private final int h;
    private final int s;
    private final int v;

    private Diode(int id, int h, int s, int v) {
        this.id = id;
        this.h = h;
        this.s = s;
        this.v = v;
    }

    public static Diode create(int id, int h, int s, int v) {
        return new Diode(id, h, s, v);
    }

    public static Diode createFromPixel(int id, Pixel pixel) {
        var pixelHsv = PixelUtil.rgbToHsv(pixel.getR(), pixel.getG(), pixel.getB());

        var h = (int) Math.ceil(pixelHsv[0]);
        var s = (int) Math.ceil(pixelHsv[1]);
        var v = (int) Math.ceil(pixelHsv[2]);

        return new Diode(id, h, s, v);
    }

    public int getId() {
        return id;
    }

    public int getH() {
        return h;
    }

    public int getS() {
        return s;
    }

    public int getV() {
        return v;
    }

    @Override
    public boolean equals(Object other) {
        if (this == other) {
            return true;
        }

        if (other == null || getClass() != other.getClass()) {
            return false;
        }

        var diode = (Diode) other;

        return id == diode.id;
    }

    @Override
    public String toString() {
        return "Diode{" +
            "id=" + id +
            ", h=" + h +
            ", s=" + s +
            ", v=" + v +
            '}';
    }
}
