package org.ndbs.aled.client.api;

import org.ndbs.aled.client.domain.Diode;

/**
 * MapDiodeToProtobufDiodeServiceImpl class
 *
 * @author  Nikolai Osipov <nao99.dev@gmail.com>
 * @version 1.0.0
 * @since   2021-12-17
 */
public class MapDiodeToProtobufDiodeServiceImpl implements MapDiodeToProtobufDiodeService {
    @Override
    public LedTapeOuterClass.LedTape.Diode map(Diode diode) {
        var protobufDiodeBuilder = LedTapeOuterClass.LedTape.Diode.newBuilder();
        return protobufDiodeBuilder
            .setId(diode.getId())
            .setHue(diode.getH())
            .build();
    }
}
