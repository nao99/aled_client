package org.ndbs.aled.client.domain;

import org.ndbs.aled.client.domain.model.Pixel;

/**
 * CalculateAveragePixelService interface
 *
 * @author  Nikolai Osipov <nao99.dev@gmail.com>
 * @version 1.0.0
 * @since   2021-12-17
 */
public interface CalculateAveragePixelService {
    /**
     * Calculates average pixel of passed pixel matrix
     * And returns it as a new {@link Pixel}
     *
     * @param pixels a pixels matrix
     * @return a new averaged pixel
     */
    Pixel calculate(Pixel[][] pixels);
}
