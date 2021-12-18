package org.ndbs.aled.client;

import org.ndbs.aled.client.config.AledConfigurationProperties;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.EnableConfigurationProperties;

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
    public static void main(String[] args) {
        var configurableApplicationContext = SpringApplication.run(Application.class, args);
    }
}
