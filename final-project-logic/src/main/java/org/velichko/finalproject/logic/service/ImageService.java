package org.velichko.finalproject.logic.service;

import java.io.IOException;
import java.io.InputStream;

public interface ImageService {
    public void upload(String imagePath, InputStream imageContent) throws IOException;
}
