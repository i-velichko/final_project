package org.velichko.finalproject.logic.service.impl;

import org.velichko.finalproject.logic.pool.PropertyLoader;
import org.velichko.finalproject.logic.service.ImageService;

import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.StandardOpenOption;
import java.util.Properties;

import static java.nio.file.StandardOpenOption.*;

public class ImageServiceImpl implements ImageService {
    private final URL IMAGE_PROPERTIES_PATH = getClass().getClassLoader().getResource("image.properties");

    @Override
    public void upload(String imagePath, InputStream imageContent) throws IOException {
        Properties properties = null;
        if (IMAGE_PROPERTIES_PATH != null) {
            properties = PropertyLoader.loadPropertiesData(IMAGE_PROPERTIES_PATH);
            final String basePath = properties.getProperty("image.base.url");
            Path imageFullPath = Path.of(basePath, imagePath);
            try (imageContent) {
                Files.write(imageFullPath, imageContent.readAllBytes(), CREATE, TRUNCATE_EXISTING);
            }
        }

    }
}
