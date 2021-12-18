package org.ndbs.aled.client.domain;

import org.ndbs.aled.client.domain.model.Pixel;
import org.springframework.stereotype.Service;

/**
 * CalculateAveragePixelServiceImpl class
 *
 * @author  Nikolai Osipov <nao99.dev@gmail.com>
 * @version 1.0.0
 * @since   2021-12-17
 */
@Service
public class CalculateAveragePixelServiceImpl implements CalculateAveragePixelService {
    @Override
    public Pixel calculate(Pixel[][] pixels) {
        var a = 0;
        var r = 0;
        var g = 0;
        var b = 0;

        var pixelsCount = 0;
        for (var pixelsColumn : pixels) {
            for (var pixel : pixelsColumn) {
                a += pixel.getA();
                r += pixel.getR();
                g += pixel.getG();
                b += pixel.getB();

                pixelsCount++;
            }
        }

        a = (int) (Math.ceil((double) a / pixelsCount));
        r = (int) (Math.ceil((double) r / pixelsCount));
        g = (int) (Math.ceil((double) g / pixelsCount));
        b = (int) (Math.ceil((double) b / pixelsCount));

        return Pixel.create(a, r, g, b);
    }
}
