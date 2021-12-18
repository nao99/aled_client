package org.ndbs.aled.client.domain;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.ndbs.aled.client.domain.model.Pixel;

import java.awt.*;

import static org.assertj.core.api.Assertions.assertThat;

/**
 * DoScreenshotServiceImplTest class
 *
 * @author  Nikolai Osipov <nao99.dev@gmail.com>
 * @version 1.0.0
 * @since   2021-12-16
 */
@DisplayName("DoScreenshotServiceImpl test: Test for getting screenshots of a screen")
class DoScreenshotServiceImplTest {
    private DoScreenshotServiceImpl doScreenshotService;

    @BeforeEach
    void setUp() {
        doScreenshotService = new DoScreenshotServiceImpl();
    }

    @DisplayName("Should do a screenshot with dimensions of current screen")
    @Test
    void shouldDoScreenshotWithDimensionsOfCurrentScreen() throws Exception {
        // given
        var robot = new Robot();

        var screenRectangle = new Rectangle(Toolkit.getDefaultToolkit().getScreenSize());
        var bufferedImage = robot.createScreenCapture(screenRectangle);

        var expectedHeight = bufferedImage.getHeight();
        var expectedWidth = bufferedImage.getWidth();

        var expectedPixel = Pixel.createFromEncoded(bufferedImage.getRGB(0, 1));

        // when
        var screenshot = doScreenshotService.doScreenshot();
        var pixel = screenshot.getPixel(0, 1);

        // then
        assertThat(screenshot.getHeight())
            .isEqualTo(expectedHeight);

        assertThat(screenshot.getWidth())
            .isEqualTo(expectedWidth);

        assertThat(pixel)
            .isEqualTo(expectedPixel);
    }


    @DisplayName("Should do not any screenshot if a screen is disabled")
    @Test
    void shouldDoNotAnyScreenshotIfScreenIsDisabled() throws Exception {
        // TODO: describe this test
    }
}
