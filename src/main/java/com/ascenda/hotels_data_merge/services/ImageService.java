package com.ascenda.hotels_data_merge.services;

import lombok.extern.slf4j.Slf4j;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;

@Service
@Slf4j
public class ImageService {
    /**
     * Image service calls verifies if the image can be loaded from URL.
     *
     */
    private final Logger logger = LoggerFactory.getLogger(ImageService.class);

    public boolean isValid(String url) {
        try {
            BufferedImage image = ImageIO.read(new URL(url));
            return image != null;
        } catch (MalformedURLException e) {
            logger.error("URL error with image", e);
            return false;
        } catch (IOException e) {
            logger.error("IO error with image", e);
            return false;
        } catch (Exception e) {
            logger.error("Error with URL", e);
            return false;
        }
    }
}
