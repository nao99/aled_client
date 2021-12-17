package org.ndbs.aled.client.domain;

/**
 * DoScreenshotService interface
 *
 * @author  Nikolai Osipov <nao99.dev@gmail.com>
 * @version 1.0.0
 * @since   2021-12-16
 */
public interface DoScreenshotService {
    /**
     * Does a screenshot of a screen
     *
     * @return a screenshot
     * @throws ScreenshotException if something was wrong
     *                             (e.g. you have not any screen or the screen is disabled)
     */
    Screenshot doScreenshot() throws ScreenshotException;
}
