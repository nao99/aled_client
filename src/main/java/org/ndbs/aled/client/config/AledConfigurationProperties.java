package org.ndbs.aled.client.config;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.context.properties.ConstructorBinding;
import org.springframework.boot.context.properties.NestedConfigurationProperty;
import org.springframework.validation.annotation.Validated;

import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.Size;
import java.util.stream.Stream;

/**
 * AledConfigurationProperties class
 *
 * @author  Nikolai Osipov <nao99.dev@gmail.com>
 * @version 1.0.0
 * @since   2021-12-18
 */
@Validated
@ConfigurationProperties(prefix = "aled")
@ConstructorBinding
public class AledConfigurationProperties {
    @NestedConfigurationProperty
    private final Diodes diodes;

    public AledConfigurationProperties(Diodes diodes) {
        this.diodes = diodes;
    }

    public Diodes getDiodes() {
        return diodes;
    }

    public int getBrightness() {
        return diodes.brightness;
    }

    public int getCountByHorizontal() {
        return diodes.countByHorizontal;
    }

    public int getCountByVertical() {
        return diodes.countByVertical;
    }

    public int[] getDefaultColorRgb() {
        return diodes.defaultColorRgb;
    }

    @Validated
    @ConstructorBinding
    public final static class Diodes {
        @Min(value = 1, message = "Diodes brightness should be greater than 0")
        @Max(value = 100, message = "Diodes brightness should be less than or equal 100")
        private final int brightness;

        @Min(value = 1, message = "Diodes count by horizontal should be greater than 0")
        private final int countByHorizontal;

        @Min(value = 1, message = "Diodes count by vertical should be greater than 0")
        private final int countByVertical;

        @Size(min = 3, max = 3, message = "Diodes default color RGB should be specified in 3 channels (R, G, B)")
        // TODO: write validation for each channel's value
        private final int[] defaultColorRgb;

        public Diodes(int brightness, int countByHorizontal, int countByVertical, String defaultColorRgb) {
            this.brightness = brightness;
            this.countByHorizontal = countByHorizontal;
            this.countByVertical = countByVertical;

            var defaultColorRgbArray = defaultColorRgb.split(";");
            this.defaultColorRgb = Stream.of(defaultColorRgbArray)
                .mapToInt(Integer::parseInt)
                .toArray();
        }

        public int getBrightness() {
            return brightness;
        }

        public int getCountByHorizontal() {
            return countByHorizontal;
        }

        public int getCountByVertical() {
            return countByVertical;
        }

        public int[] getDefaultColorRgb() {
            return defaultColorRgb;
        }
    }
}
