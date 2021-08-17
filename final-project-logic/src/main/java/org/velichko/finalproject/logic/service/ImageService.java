package org.velichko.finalproject.logic.service;

import java.io.IOException;
import java.io.InputStream;

/**
 * @author Ivan Velichko
 *
 * The interface Image service.
 */
public interface ImageService {
    /**
     * Upload.
     *
     * @param imagePath    the image path
     * @param imageContent the image content
     * @throws IOException the io exception
     */
    public void upload(String imagePath, InputStream imageContent) throws IOException;
}
