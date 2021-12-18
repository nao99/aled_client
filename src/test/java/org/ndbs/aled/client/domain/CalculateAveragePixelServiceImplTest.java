package org.ndbs.aled.client.domain;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.ndbs.aled.client.domain.model.Pixel;

import static org.assertj.core.api.Assertions.assertThat;

/**
 * CalculateAveragePixelServiceImplTest class
 *
 * @author  Nikolai Osipov <nao99.dev@gmail.com>
 * @version 1.0.0
 * @since   2021-12-17
 */
@DisplayName("CalculateAveragePixelServiceImpl test: Test for calculating average of pixels")
class CalculateAveragePixelServiceImplTest {
    private CalculateAveragePixelServiceImpl calculateAveragePixelService;

    @BeforeEach
    void setUp() throws Exception {
        calculateAveragePixelService = new CalculateAveragePixelServiceImpl();
    }

    @DisplayName("Should calculate average of squared pixels matrix")
    @Test
    void shouldCalculateAverageOfSquaredPixelsMatrix() throws Exception {
        // given
        var pixel11 = Pixel.create(255, 255, 255, 255);
        var pixel12 = Pixel.create(255, 255, 255, 255);
        var pixel13 = Pixel.create(255, 255, 255, 255);
        var pixel14 = Pixel.create(255, 255, 255, 255);

        var pixel21 = Pixel.create(0, 0, 0, 0);
        var pixel22 = Pixel.create(0, 0, 0, 0);
        var pixel23 = Pixel.create(0, 0, 0, 0);
        var pixel24 = Pixel.create(0, 0, 0, 0);

        var pixel31 = Pixel.create(10, 10, 10, 10);
        var pixel32 = Pixel.create(0, 0, 0, 0);
        var pixel33 = Pixel.create(10, 10, 10, 10);
        var pixel34 = Pixel.create(0, 0, 0, 0);

        var pixels1 = new Pixel[2][2];
        var expectedPixelAverage1 = Pixel.create(255, 255, 255, 255);

        pixels1[0][0] = pixel11;
        pixels1[0][1] = pixel12;
        pixels1[1][0] = pixel13;
        pixels1[1][1] = pixel14;

        var pixels2 = new Pixel[2][2];
        var expectedPixelAverage2 = Pixel.create(0, 0, 0, 0);

        pixels2[0][0] = pixel21;
        pixels2[0][1] = pixel22;
        pixels2[1][0] = pixel23;
        pixels2[1][1] = pixel24;

        var pixels3 = new Pixel[2][2];
        var expectedPixelAverage3 = Pixel.create(5, 5, 5, 5);

        pixels3[0][0] = pixel31;
        pixels3[0][1] = pixel32;
        pixels3[1][0] = pixel33;
        pixels3[1][1] = pixel34;

        var pixels4 = new Pixel[2][2];
        var expectedPixelAverage4 = Pixel.create(5, 5, 5, 5);

        pixels4[0][0] = pixel32;
        pixels4[0][1] = pixel34;
        pixels4[1][0] = pixel31;
        pixels4[1][1] = pixel33;

        // when
        var pixelAverage1 = calculateAveragePixelService.calculate(pixels1);
        var pixelAverage2 = calculateAveragePixelService.calculate(pixels2);
        var pixelAverage3 = calculateAveragePixelService.calculate(pixels3);
        var pixelAverage4 = calculateAveragePixelService.calculate(pixels4);

        // then
        assertThat(pixelAverage1)
            .isEqualTo(expectedPixelAverage1);

        assertThat(pixelAverage2)
            .isEqualTo(expectedPixelAverage2);

        assertThat(pixelAverage3)
            .isEqualTo(expectedPixelAverage3);

        assertThat(pixelAverage4)
            .isEqualTo(expectedPixelAverage4);
    }

    @DisplayName("Should calculate average of not squared pixels matrix")
    @Test
    void shouldCalculateAverageOfNotSquaredPixelsMatrix() throws Exception {
        // given
        var pixel11 = Pixel.create(255, 255, 255, 255);
        var pixel12 = Pixel.create(255, 255, 255, 255);

        var pixel21 = Pixel.create(0, 0, 0, 0);
        var pixel22 = Pixel.create(0, 0, 0, 0);

        var pixel31 = Pixel.create(10, 10, 10, 10);
        var pixel32 = Pixel.create(0, 0, 0, 0);

        var pixels1 = new Pixel[1][2];
        var expectedPixelAverage1 = Pixel.create(255, 255, 255, 255);

        pixels1[0][0] = pixel11;
        pixels1[0][1] = pixel12;

        var pixels2 = new Pixel[1][2];
        var expectedPixelAverage2 = Pixel.create(0, 0, 0, 0);

        pixels2[0][0] = pixel21;
        pixels2[0][1] = pixel22;

        var pixels3 = new Pixel[1][2];
        var expectedPixelAverage3 = Pixel.create(5, 5, 5, 5);

        pixels3[0][0] = pixel31;
        pixels3[0][1] = pixel32;

        var pixels4 = new Pixel[1][2];
        var expectedPixelAverage4 = Pixel.create(5, 5, 5, 5);

        pixels4[0][0] = pixel32;
        pixels4[0][1] = pixel31;

        var pixels5 = new Pixel[2][1];
        var expectedPixelAverage5 = Pixel.create(255, 255, 255, 255);

        pixels5[0][0] = pixel11;
        pixels5[1][0] = pixel12;

        var pixels6 = new Pixel[2][1];
        var expectedPixelAverage6 = Pixel.create(0, 0, 0, 0);

        pixels6[0][0] = pixel21;
        pixels6[1][0] = pixel22;

        var pixels7 = new Pixel[2][1];
        var expectedPixelAverage7 = Pixel.create(5, 5, 5, 5);

        pixels7[0][0] = pixel31;
        pixels7[1][0] = pixel32;

        var pixels8 = new Pixel[2][1];
        var expectedPixelAverage8 = Pixel.create(5, 5, 5, 5);

        pixels8[0][0] = pixel32;
        pixels8[1][0] = pixel31;

        // when
        var pixelAverage1 = calculateAveragePixelService.calculate(pixels1);
        var pixelAverage2 = calculateAveragePixelService.calculate(pixels2);
        var pixelAverage3 = calculateAveragePixelService.calculate(pixels3);
        var pixelAverage4 = calculateAveragePixelService.calculate(pixels4);

        var pixelAverage5 = calculateAveragePixelService.calculate(pixels5);
        var pixelAverage6 = calculateAveragePixelService.calculate(pixels6);
        var pixelAverage7 = calculateAveragePixelService.calculate(pixels7);
        var pixelAverage8 = calculateAveragePixelService.calculate(pixels8);

        // then
        assertThat(pixelAverage1)
            .isEqualTo(expectedPixelAverage1);

        assertThat(pixelAverage2)
            .isEqualTo(expectedPixelAverage2);

        assertThat(pixelAverage3)
            .isEqualTo(expectedPixelAverage3);

        assertThat(pixelAverage4)
            .isEqualTo(expectedPixelAverage4);

        assertThat(pixelAverage5)
            .isEqualTo(expectedPixelAverage5);

        assertThat(pixelAverage6)
            .isEqualTo(expectedPixelAverage6);

        assertThat(pixelAverage7)
            .isEqualTo(expectedPixelAverage7);

        assertThat(pixelAverage8)
            .isEqualTo(expectedPixelAverage8);
    }

    @DisplayName("Should round to greater side")
    @Test
    void shouldRoundToGreaterSide() throws Exception {
        // given
        var pixel1 = Pixel.create(254, 255, 255, 255);
        var pixel2 = Pixel.create(255, 254, 255, 255);
        var pixel3 = Pixel.create(255, 255, 254, 255);
        var pixel4 = Pixel.create(255, 255, 255, 254);

        var pixels = new Pixel[2][2];
        var expectedPixelAverage = Pixel.create(255, 255, 255, 255);

        pixels[0][0] = pixel1;
        pixels[0][1] = pixel2;
        pixels[1][0] = pixel3;
        pixels[1][1] = pixel4;

        // when
        var pixelAverage = calculateAveragePixelService.calculate(pixels);

        // then
        assertThat(pixelAverage)
            .isEqualTo(expectedPixelAverage);
    }
}
