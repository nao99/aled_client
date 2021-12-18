package org.ndbs.aled.client.domain.model;

/**
 * Diode class
 *
 * @author  Nikolai Osipov <nao99.dev@gmail.com>
 * @version 1.0.0
 * @since   2021-12-18
 */
public class Diode {
    private final int id;
    private final Pixel pixel;

    private Diode(int id, Pixel pixel) {
        this.id = id;
        this.pixel = pixel;
    }

    public static Diode create(int id, Pixel pixel) {
        return new Diode(id, pixel);
    }

    public int getId() {
        return id;
    }

    public Pixel getPixel() {
        return pixel;
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
            ", pixel=" + pixel +
            '}';
    }
}
