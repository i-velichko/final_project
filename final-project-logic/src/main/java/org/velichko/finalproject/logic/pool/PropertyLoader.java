package org.velichko.finalproject.logic.pool;

import org.apache.logging.log4j.Level;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.io.IOException;
import java.net.URL;
import java.util.Properties;

/**
 * @author Ivan Velichko
 *
 * The type Property loader.
 */
public class PropertyLoader {
    private static final Logger LOGGER = LogManager.getLogger();

    /**
     * Load properties data properties.
     *
     * @param url the url
     * @return the properties
     */
    public static Properties loadPropertiesData(URL url) {
        Properties properties = new Properties();
        try {
            properties.load(url.openStream());
        } catch (IOException e) {
            LOGGER.log(Level.FATAL, "Error with loading properties from file. ", e);
            throw new RuntimeException("Error with loading properties from file: " + url, e);
        }
        return properties;
    }
}
