package org.ndbs.aled.client.api;

import org.ndbs.aled.client.domain.model.Diode;

/**
 * MapDiodeToProtobufDiodeService interface
 *
 * @author  Nikolai Osipov <nao99.dev@gmail.com>
 * @version 1.0.0
 * @since   2021-12-17
 */
public interface MapDiodeToProtobufDiodeService {
    /**
     * Maps a {@link Diode} to protobuf diode
     *
     * @param diode a diode
     * @return a protobuf diode
     */
    LedTapeOuterClass.LedTape.Diode map(Diode diode);
}
