package org.ndbs.aled.client.domain;

import org.ndbs.aled.client.config.AledConfigurationProperties;
import org.ndbs.aled.client.domain.model.Diode;
import org.ndbs.aled.client.domain.model.Pixel;
import org.ndbs.aled.client.domain.model.Screenshot;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * DefineDiodesForScreenshotServiceImpl class
 *
 * @author  Nikolai Osipov <nao99.dev@gmail.com>
 * @version 1.0.0
 * @since   2021-12-17
 */
@Service
public class DefineDiodesForScreenshotServiceImpl implements DefineDiodesForScreenshotService {
    private static final Logger logger = LoggerFactory.getLogger(DefineDiodesForScreenshotServiceImpl.class);

    private final AledConfigurationProperties aledConfigurationProperties;
    private final CalculateAveragePixelService calculateAverageOfPixelsService;

    @Autowired
    public DefineDiodesForScreenshotServiceImpl(
        AledConfigurationProperties aledConfigurationProperties,
        CalculateAveragePixelService calculateAverageOfPixelsService
    ) {
        this.aledConfigurationProperties = aledConfigurationProperties;
        this.calculateAverageOfPixelsService = calculateAverageOfPixelsService;
    }

    @Override
    public Diode[] define(Screenshot screenshot) throws DefinitionDiodesException {
        var screenshotWidth = screenshot.getWidth();
        var screenshotHeight = screenshot.getHeight();

        var diodesCountByHorizontal = aledConfigurationProperties.getCountByHorizontal();
        var diodesCountByVertical = aledConfigurationProperties.getCountByVertical();

        if (screenshotWidth < diodesCountByHorizontal) {
            var errorMessage = "Unable to define diode for screenshot with width less than horizontal diodes count";
            logger.error(errorMessage);

            throw new DefinitionDiodesException(errorMessage);
        }

        if (screenshotHeight < diodesCountByVertical) {
            var errorMessage = "Unable to define diode for screenshot with height less than vertical diodes count";
            logger.error(errorMessage);

            throw new DefinitionDiodesException(errorMessage);
        }

        var horizontalDiodeSidePixels = screenshotWidth / diodesCountByHorizontal;
        var verticalDiodeSidePixels = screenshotHeight / diodesCountByVertical;

        var x1Right = screenshotWidth - verticalDiodeSidePixels;
        var y1Top = screenshotHeight - horizontalDiodeSidePixels;

        var diodesCount = 2 * (diodesCountByHorizontal + diodesCountByVertical);
        var diodes = new Diode[diodesCount];

        var id = 1;

        id = defineForSide(diodes, id, screenshot, diodesCountByVertical, verticalDiodeSidePixels, x1Right, 0, true);
        id = defineForSide(diodes, id, screenshot, diodesCountByHorizontal, horizontalDiodeSidePixels, 0, y1Top, false);
        id = defineForSide(diodes, id, screenshot, diodesCountByVertical, verticalDiodeSidePixels, 0, 0, true);
        id = defineForSide(diodes, id, screenshot, diodesCountByHorizontal, horizontalDiodeSidePixels, 0, 0, false);

        return diodes;
    }

    private int defineForSide(
        Diode[] diodes,
        int firstDiodeId,
        Screenshot screenshot,
        int diodesCount,
        int diodePixels,
        int x1,
        int y1,
        boolean vertical
    ) {
        for (var i = 0; i < diodesCount; i++) {
            if (vertical) {
                y1 += i * diodePixels;
            } else {
                x1 += i * diodePixels;
            }

            var pixels = getPixels(screenshot, x1, y1, diodePixels);
            var averagedPixel = calculateAverageOfPixelsService.calculate(pixels);

            diodes[firstDiodeId - 1] = Diode.create(firstDiodeId, averagedPixel);
            firstDiodeId += 1;
        }

        return firstDiodeId;
    }

    private Pixel[][] getPixels(Screenshot screenshot, int x1, int y1, int pixelsNumberForSide) {
        var x2 = x1 + pixelsNumberForSide;
        var y2 = y1 + pixelsNumberForSide;

        var pixels = new Pixel[pixelsNumberForSide][pixelsNumberForSide];

        int i = 0;
        for (var x = x1; x < x2; x++) {
            int j = 0;
            for (var y = y1; y < y2; y++) {
                pixels[i][j] = screenshot.getPixel(x, y);
                j++;
            }

            i++;
        }

        return pixels;
    }
}
