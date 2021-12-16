package org.ndbs.aled.client.domain;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.awt.*;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;

/**
 * ScreenshoterImplTest class
 *
 * @author  Nikolai Osipov <nao99.dev@gmail.com>
 * @version 1.0.0
 * @since   2021-12-16
 */
@DisplayName("ScreenshoterImpl test: Test for getting screenshots of a screen")
class ScreenshoterImplTest {
    private ScreenshoterImpl screenshoter;

    @BeforeEach
    void setUp() {
        screenshoter = new ScreenshoterImpl();
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
        var screenshot = screenshoter.doScreenshot();
        var pixel = screenshot.getPixel(0, 1);

        // then
        assertThat(screenshot.getHeight())
            .isEqualTo(expectedHeight);

        assertThat(screenshot.getWidth())
            .isEqualTo(expectedWidth);

        assertThat(pixel)
            .isEqualTo(expectedPixel);
    }


    @DisplayName("Should not do any screenshot if a screen is disabled")
    @Test
    void shouldNotDoAnyScreenshotIfScreenIsDisabled() throws Exception {
        // TODO: describe this test
    }
}
