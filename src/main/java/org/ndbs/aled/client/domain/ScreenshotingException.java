package org.ndbs.aled.client.domain;

/**
 * ScreenshotingException class
 *
 * @author  Nikolai Osipov <nao99.dev@gmail.com>
 * @version 1.0.0
 * @since   2021-12-16
 */
public class ScreenshotingException extends Exception {
    public ScreenshotingException(String message) {
        super(message);
    }

    public ScreenshotingException(String message, Throwable previous) {
        super(message, previous);
    }
}
