package org.ndbs.aled.client.domain;

/**
 * ScreenshotException class
 *
 * @author  Nikolai Osipov <nao99.dev@gmail.com>
 * @version 1.0.0
 * @since   2021-12-16
 */
public class ScreenshotException extends Exception {
    public ScreenshotException(String message) {
        super(message);
    }

    public ScreenshotException(String message, Throwable previous) {
        super(message, previous);
    }
}
