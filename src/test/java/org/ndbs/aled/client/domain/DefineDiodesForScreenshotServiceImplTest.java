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
@DisplayName("DefineDiodesForScreenshotServiceImpl test: Test for ...")
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

        var expectedDiode1 = Diode.create(1, 240, 0, 100);
        var expectedDiode2 = Diode.create(2, 240, 0, 100);
        var expectedDiode3 = Diode.create(3, 240, 0, 100);
        var expectedDiode4 = Diode.create(4, 240, 0, 100);
        var expectedDiode5 = Diode.create(5, 240, 0, 100);
        var expectedDiode6 = Diode.create(6, 240, 0, 100);

        // when
        var diodes = defineDiodesForScreenshotService.define(screenshot);

        // then
        assertThat(diodes)
            .hasSize(6);

        assertThat(diodes[0])
            .isEqualTo(expectedDiode1);

        assertThat(diodes[1])
            .isEqualTo(expectedDiode2);

        assertThat(diodes[2])
            .isEqualTo(expectedDiode3);

        assertThat(diodes[3])
            .isEqualTo(expectedDiode4);

        assertThat(diodes[4])
            .isEqualTo(expectedDiode5);

        assertThat(diodes[5])
            .isEqualTo(expectedDiode6);
    }

    // TODO: rename
    @DisplayName("Should define 3 white and 3 black diodes from black and white 3x2 screenshot")
    @Test
    void shouldDefine3WhiteAnd3BlackDiodesFromBlackAndWhite3X2Screenshot() throws Exception {
        // given
        var screenshotPixels = new Pixel[3][2];

        screenshotPixels[0][0] = Pixel.create(255, 0, 0, 0);
        screenshotPixels[0][1] = Pixel.create(255, 255, 255, 255);
        screenshotPixels[1][0] = Pixel.create(255, 0, 0, 0);
        screenshotPixels[1][1] = Pixel.create(255, 255, 255, 255);
        screenshotPixels[2][0] = Pixel.create(255, 0, 0, 0);
        screenshotPixels[2][1] = Pixel.create(255, 255, 255, 255);

        var screenshot = Screenshot.create(3, 2, screenshotPixels);

        var expectedDiode1 = Diode.create(1, 240, 0, 100);
        var expectedDiode2 = Diode.create(2, 240, 0, 100);
        var expectedDiode3 = Diode.create(3, 240, 0, 100);
        var expectedDiode4 = Diode.create(4, 240, 0, 100);
        var expectedDiode5 = Diode.create(5, 240, 0, 100);
        var expectedDiode6 = Diode.create(6, 240, 0, 100);

        // when
        var diodes = defineDiodesForScreenshotService.define(screenshot);

        // then
        assertThat(diodes)
            .hasSize(6);

        assertThat(diodes[0])
            .isEqualTo(expectedDiode1);

        assertThat(diodes[1])
            .isEqualTo(expectedDiode2);

        assertThat(diodes[2])
            .isEqualTo(expectedDiode3);

        assertThat(diodes[3])
            .isEqualTo(expectedDiode4);

        assertThat(diodes[4])
            .isEqualTo(expectedDiode5);

        assertThat(diodes[5])
            .isEqualTo(expectedDiode6);
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
        var screenshotPixels = new Pixel[2][1];

        screenshotPixels[0][0] = Pixel.create(255, 0, 0, 0);
        screenshotPixels[1][0] = Pixel.create(255, 255, 255, 255);

        var screenshot = Screenshot.create(2, 1, screenshotPixels);

        // when / then
        assertThatExceptionOfType(DefinitionDiodesException.class)
            .isThrownBy(() -> defineDiodesForScreenshotService.define(screenshot));
    }
}
