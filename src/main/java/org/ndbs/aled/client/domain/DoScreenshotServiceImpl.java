package org.ndbs.aled.client.domain;

import org.ndbs.aled.client.domain.model.Pixel;
import org.ndbs.aled.client.domain.model.Screenshot;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.awt.*;

/**
 * DoScreenshotServiceImpl class
 */
@Service
public class DoScreenshotServiceImpl implements DoScreenshotService {
    private final static Logger logger = LoggerFactory.getLogger(DoScreenshotServiceImpl.class);

    @Override
    public Screenshot doScreenshot() throws ScreenshotException {
        Robot robot;
        try {
            robot = new Robot();
        } catch (AWTException e) {
            var errorMessage = String.format("Unable to do any screenshot: \"%s\"", e.getMessage());
            logger.error(errorMessage);

            throw new ScreenshotException(errorMessage, e);
        }

        var defaultToolkit = Toolkit.getDefaultToolkit();
        var screenSize = defaultToolkit.getScreenSize();

        var screenRectangle = new Rectangle(screenSize);
        var bufferedImage = robot.createScreenCapture(screenRectangle);

        var width = bufferedImage.getWidth();
        var height = bufferedImage.getHeight();

        var pixels = new Pixel[width][height];

        for (int x = 0; x < width; x++) {
            for (int y = 0; y < height; y++) {
                int encodedPixel = bufferedImage.getRGB(x, y);
                pixels[x][y] = Pixel.createFromEncoded(encodedPixel);
            }
        }

        return Screenshot.create(width, height, pixels);
    }
}
