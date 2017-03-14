package fr.ebiz.cdb.it.utils;

import fr.ebiz.cdb.persistence.utils.DAOConfigurationException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

/**
 * Created by ebiz on 15/02/17.
 */
public class ITHelper {

    public static final String ERROR_LOADING_FILE = "Error loading file";
    private static final String PROPERTIES = "dao.properties";
    private static Logger logger = LoggerFactory.getLogger(ITHelper.class);

    /**
     * Read the property file.
     */
    public static Properties readPropertiesFile(String filename) {

        Properties properties = new Properties();
        ClassLoader classLoader = Thread.currentThread().getContextClassLoader();

        try (InputStream propertiesFile = classLoader.getResourceAsStream(filename)) {
            properties.load(propertiesFile);
        } catch (IOException e) {
            logger.error(ERROR_LOADING_FILE);
        }

        try (InputStream input = new FileInputStream("/home/" + PROPERTIES)) {
            properties.load(input);
            logger.info("Found property file in /home , overriding properties");
        } catch (IOException e) {
            // do nothing
        }

        return properties;
    }

}
