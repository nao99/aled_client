package org.ndbs.aled.client.domain;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.ndbs.aled.client.config.AledConfigurationProperties;
import org.ndbs.aled.client.domain.model.Diode;
import org.ndbs.aled.client.domain.model.Pixel;
import org.ndbs.aled.client.domain.model.Screenshot;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatExceptionOfType;

/**
 * DefineDiodesForScreenshotServiceImplTest class
 *
 * @author  Nikolai Osipov <nao99.dev@gmail.com>
 * @version 1.0.0
 * @since   2021-12-17
 */
@DisplayName("DefineDiodesForScreenshotServiceImpl test: Test for defining diodes for a screenshot")
class DefineDiodesForScreenshotServiceImplTest {
    private DefineDiodesForScreenshotServiceImpl defineDiodesForScreenshotService;

    @BeforeEach
    void setUp() throws Exception {
        var calculateAverageOfPixelsService = new CalculateAveragePixelServiceImpl();

        var aledConfigurationPropertiesDiodes = new AledConfigurationProperties.Diodes(80, 2, 1, "255;255;255");
        var aledConfigurationProperties = new AledConfigurationProperties(aledConfigurationPropertiesDiodes);

        defineDiodesForScreenshotService = new DefineDiodesForScreenshotServiceImpl(
            aledConfigurationProperties,
            calculateAverageOfPixelsService
        );
    }

    @DisplayName("Should define 6 white diodes from white 3x2 screenshot")
    @Test
    void shouldDefine10WhiteDiodesFromWhite3X2Screenshot() throws Exception {
        // given
        var screenshotPixels = new Pixel[3][2];

        screenshotPixels[0][0] = Pixel.create(255, 255, 255, 255);
        screenshotPixels[0][1] = Pixel.create(255, 255, 255, 255);
        screenshotPixels[1][0] = Pixel.create(255, 255, 255, 255);
        screenshotPixels[1][1] = Pixel.create(255, 255, 255, 255);
        screenshotPixels[2][0] = Pixel.create(255, 255, 255, 255);
        screenshotPixels[2][1] = Pixel.create(255, 255, 255, 255);

        var screenshot = Screenshot.create(3, 2, screenshotPixels);
        var expectedPixel = Pixel.create(255, 255, 255, 255);

        var expectedDiode1 = Diode.create(1, expectedPixel);
        var expectedDiode2 = Diode.create(2, expectedPixel);
        var expectedDiode3 = Diode.create(3, expectedPixel);
        var expectedDiode4 = Diode.create(4, expectedPixel);
        var expectedDiode5 = Diode.create(5, expectedPixel);
        var expectedDiode6 = Diode.create(6, expectedPixel);

        // when
        var diodes = defineDiodesForScreenshotService.define(screenshot);

        // then
        assertThat(diodes)
            .hasSize(6);

        assertThat(diodes[0].getPixel())
            .isEqualTo(expectedDiode1.getPixel());

        assertThat(diodes[1].getPixel())
            .isEqualTo(expectedDiode2.getPixel());

        assertThat(diodes[2].getPixel())
            .isEqualTo(expectedDiode3.getPixel());

        assertThat(diodes[3].getPixel())
            .isEqualTo(expectedDiode4.getPixel());

        assertThat(diodes[4].getPixel())
            .isEqualTo(expectedDiode5.getPixel());

        assertThat(diodes[5].getPixel())
            .isEqualTo(expectedDiode6.getPixel());
    }

    @DisplayName("Should define 2 gray, 2 white and 2 black diodes from black and white 3x2 screenshot")
    @Test
    void shouldDefine2Gray2WhiteAnd2BlackDiodesFromBlackAndWhite3X2Screenshot() throws Exception {
        // given
        var screenshotPixels = new Pixel[3][2];

        screenshotPixels[0][0] = Pixel.create(255, 0, 0, 0);
        screenshotPixels[0][1] = Pixel.create(255, 255, 255, 255);
        screenshotPixels[1][0] = Pixel.create(255, 0, 0, 0);
        screenshotPixels[1][1] = Pixel.create(255, 255, 255, 255);
        screenshotPixels[2][0] = Pixel.create(255, 0, 0, 0);
        screenshotPixels[2][1] = Pixel.create(255, 255, 255, 255);

        var screenshot = Screenshot.create(3, 2, screenshotPixels);

        var expectedPixelBlack = Pixel.create(255, 0, 0, 0);
        var expectedPixelGray = Pixel.create(255, 128, 128, 128);
        var expectedPixelWhite = Pixel.create(255, 255, 255, 255);

        var expectedDiode1 = Diode.create(1, expectedPixelGray);
        var expectedDiode2 = Diode.create(2, expectedPixelWhite);
        var expectedDiode3 = Diode.create(3, expectedPixelWhite);
        var expectedDiode4 = Diode.create(4, expectedPixelGray);
        var expectedDiode5 = Diode.create(5, expectedPixelBlack);
        var expectedDiode6 = Diode.create(6, expectedPixelBlack);

        // when
        var diodes = defineDiodesForScreenshotService.define(screenshot);

        // then
        assertThat(diodes)
            .hasSize(6);

        assertThat(diodes[0].getPixel())
            .isEqualTo(expectedDiode1.getPixel());

        assertThat(diodes[1].getPixel())
            .isEqualTo(expectedDiode2.getPixel());

        assertThat(diodes[2].getPixel())
            .isEqualTo(expectedDiode3.getPixel());

        assertThat(diodes[3].getPixel())
            .isEqualTo(expectedDiode4.getPixel());

        assertThat(diodes[4].getPixel())
            .isEqualTo(expectedDiode5.getPixel());

        assertThat(diodes[5].getPixel())
            .isEqualTo(expectedDiode6.getPixel());
    }

    @DisplayName("Should throw an exception if screenshot width less than diodes count by horizontal")
    @Test
    void shouldThrowExceptionIfScreenshotWidthLessThanDiodesCountByHorizontal() throws Exception {
        // given
        var screenshotPixels = new Pixel[1][1];
        screenshotPixels[0][0] = Pixel.create(255, 0, 0, 0);

        var screenshot = Screenshot.create(1, 1, screenshotPixels);

        // when / then
        assertThatExceptionOfType(DefinitionDiodesException.class)
            .isThrownBy(() -> defineDiodesForScreenshotService.define(screenshot));
    }

    @DisplayName("Should throw an exception if screenshot height less than diodes count by vertical")
    @Test
    void shouldThrowExceptionIfScreenshotHeightLessThanDiodesCountByVertical() throws Exception {
        // given
        var calculateAverageOfPixelsService = new CalculateAveragePixelServiceImpl();

        var aledConfigurationPropertiesDiodes = new AledConfigurationProperties.Diodes(80, 2, 2, "255;255;255");
        var aledConfigurationProperties = new AledConfigurationProperties(aledConfigurationPropertiesDiodes);

        defineDiodesForScreenshotService = new DefineDiodesForScreenshotServiceImpl(
            aledConfigurationProperties,
            calculateAverageOfPixelsService
        );

        var screenshotPixels = new Pixel[2][1];

        screenshotPixels[0][0] = Pixel.create(255, 0, 0, 0);
        screenshotPixels[1][0] = Pixel.create(255, 255, 255, 255);

        var screenshot = Screenshot.create(2, 1, screenshotPixels);

        // when / then
        assertThatExceptionOfType(DefinitionDiodesException.class)
            .isThrownBy(() -> defineDiodesForScreenshotService.define(screenshot));
    }
}
