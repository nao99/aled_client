package org.ndbs.aled.client.domain;

/**
 * Screenshoter interface
 *
 * @author  Nikolai Osipov <nao99.dev@gmail.com>
 * @version 1.0.0
 * @since   2021-12-16
 */
public interface Screenshoter {
    /**
     * Does a screenshot of all screen
     *
     * @return a screenshot
     * @throws ScreenshotingException if something was wrong (e.g. you have not any screen or screen is disabled)
     */
    Screenshot doScreenshot() throws ScreenshotingException;
}
