package org.ndbs.aled.client;

import org.ndbs.aled.client.config.AledConfigurationProperties;
import org.ndbs.aled.client.domain.DefineDiodesForScreenshotService;
import org.ndbs.aled.client.domain.DefinitionDiodesException;
import org.ndbs.aled.client.domain.DoScreenshotService;
import org.ndbs.aled.client.domain.ScreenshotException;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.context.properties.EnableConfigurationProperties;

import java.io.IOException;

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
    public static void main(String[] args) throws ScreenshotException, DefinitionDiodesException, IOException, InterruptedException {
        var springApplicationBuilder = new SpringApplicationBuilder(Application.class);
        springApplicationBuilder.headless(false);

        var applicationContext = springApplicationBuilder.run(args);

        var doScreenshotService = applicationContext.getBean(DoScreenshotService.class);
        var defineDiodesForScreenshotService = applicationContext.getBean(DefineDiodesForScreenshotService.class);
    }
}
