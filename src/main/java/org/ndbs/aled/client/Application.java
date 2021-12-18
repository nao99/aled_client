package org.ndbs.aled.client;

import org.ndbs.aled.client.api.LedTapeOuterClass;
import org.ndbs.aled.client.config.AledConfigurationProperties;
import org.ndbs.aled.client.domain.DefineDiodesForScreenshotService;
import org.ndbs.aled.client.domain.DefinitionDiodesException;
import org.ndbs.aled.client.domain.DoScreenshotService;
import org.ndbs.aled.client.domain.ScreenshotException;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.context.properties.EnableConfigurationProperties;

import java.io.DataOutputStream;
import java.io.IOException;
import java.net.Socket;

/**
 * Application class
 *
 * @author  Nikolai Osipov <nao99.dev@gmail.com>
 * @version 1.0.0
 * @since   2021-12-16
 */
@SpringBootApplication
@EnableConfigurationProperties(AledConfigurationProperties.class)
public class Application {
    /**
     * Project's entrypoint
     *
     * @param args an arguments array
     */
    public static void main(String[] args) throws ScreenshotException, DefinitionDiodesException, IOException {
        var springApplicationBuilder = new SpringApplicationBuilder(Application.class);
        springApplicationBuilder.headless(false);

        var applicationContext = springApplicationBuilder.run(args);

        var doScreenshotService = applicationContext.getBean(DoScreenshotService.class);
        var defineDiodesForScreenshotService = applicationContext.getBean(DefineDiodesForScreenshotService.class);

        var screenshot = doScreenshotService.doScreenshot();
        var diodes = defineDiodesForScreenshotService.define(screenshot);

        var socket = new Socket("192.168.0.39", 8215);

        try (var printStream = new DataOutputStream(socket.getOutputStream())) {
            var ledTapeBuilder = LedTapeOuterClass.LedTape.newBuilder();
            var ledTapeDiodeBuilder = LedTapeOuterClass.LedTape.Diode.newBuilder();

            for (var diode : diodes) {
                var diodePixel = diode.getPixel();
                var ledTapeDiode = ledTapeDiodeBuilder
                    .setId(diode.getId())
                    .setR(diodePixel.getR())
                    .setG(diodePixel.getG())
                    .setB(diodePixel.getB())
                    .build();

                ledTapeBuilder.addDiodes(ledTapeDiode);
            }

            var ledTape = ledTapeBuilder.build();
            var ledTapeBytes = ledTape.toByteArray();

            printStream.writeInt(ledTapeBytes.length);
            ledTape.writeTo(printStream);
        }
    }
}
