package org.ndbs.aled.client.domain;

/**
 * CalculateAverageOfPixelsService interface
 *
 * @author  Nikolai Osipov <nao99.dev@gmail.com>
 * @version 1.0.0
 * @since   2021-12-17
 */
public interface CalculateAverageOfPixelsService {
    /**
     * Calculates average of passed pixel matrix
     * And returns it as a new {@link Pixel}
     *
     * @param pixels a pixels matrix
     * @return a new averaged pixel
     */
    Pixel calculate(Pixel[][] pixels);
}
