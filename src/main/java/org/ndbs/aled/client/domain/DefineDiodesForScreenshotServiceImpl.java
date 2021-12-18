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

        var pixelsPerDiodeSideForHorizontal = (int) screenshotWidth / diodesCountByHorizontal;
        var pixelsPerDiodeSideForVertical = (int) screenshotHeight / diodesCountByVertical;

        var diodesCount = 2 * (diodesCountByHorizontal + diodesCountByVertical);
        var diodes = new Diode[diodesCount];

//        for (int i = 0; i < diodesCountByHorizontal; i++) {
//            var diodeStartPixelNumber = i * pixelsPerDiodeSideForHorizontal;
//            var diodeEndPixelNumber = diodeStartPixelNumber + pixelsPerDiodeSideForHorizontal;
//
//            var diodePixelsBottom = new Pixel[pixelsPerDiodeSideForHorizontal][pixelsPerDiodeSideForHorizontal];
//            var diodePixelsTop = new Pixel[pixelsPerDiodeSideForHorizontal][pixelsPerDiodeSideForHorizontal];
//
//            var jx = 0;
//            for (int j = diodeStartPixelNumber; j < diodeEndPixelNumber; j++) {
//                for (int k = 0; k < pixelsPerDiodeSideForHorizontal; k++) {
//                    diodePixelsBottom[jx][k] = screenshot.getPixel(j, k);
//                }
//
//                jx++;
//            }
//
//            var jxx = 0;
//            for (int j = diodeStartPixelNumber; j < diodeEndPixelNumber; j++) {
//                for (int k = 0; k < pixelsPerDiodeSideForHorizontal; k++) {
//                    diodePixelsTop[jx][k] = screenshot.getPixel(j, k);
//                }
//
//                jxx++;
//            }
//
////            var averagedPixel = calculateAverageOfPixelsService.calculate(diodePixels);
////            diodes[i] = Diode.createFromPixel(i + 1, averagedPixel);
//        }
//
//        int a = 123;

//
//        for (int i = 0; i < diodesCountByVertical; i++) {
//            var diodePixelsOnLeftSide = new Pixel[pixelsPerDiodeSideForVertical][pixelsPerDiodeSideForVertical];
//            var diodePixelsOnRightSide = new Pixel[pixelsPerDiodeSideForVertical][pixelsPerDiodeSideForVertical];
//
//
//        }



        return new Diode[0];
    }
}
