package org.ndbs.aled.client.domain;

import org.ndbs.aled.client.domain.model.Diode;
import org.ndbs.aled.client.domain.model.Screenshot;

/**
 * DefineDiodesForScreenshotService interface
 *
 * @author  Nikolai Osipov <nao99.dev@gmail.com>
 * @version 1.0.0
 * @since   2021-12-17
 */
public interface DefineDiodesForScreenshotService {
    /**
     * Defines {@link Diode}s for a {@link Screenshot}
     *
     * It means this method defines which diodes should be lighted
     * and with which color
     *
     * @param screenshot              a screenshot
     * @return a diodes array
     *
     * @throws DefinitionDiodesException if something was wrong
     */
    Diode[] define(Screenshot screenshot) throws DefinitionDiodesException;
}
