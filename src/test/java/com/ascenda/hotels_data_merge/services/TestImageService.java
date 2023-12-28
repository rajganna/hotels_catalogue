package com.ascenda.hotels_data_merge.services;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.MockedStatic;
import org.mockito.Mockito;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.net.URL;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.mockStatic;

@ExtendWith(SpringExtension.class)
public class TestImageService {

    ImageService imageService = new ImageService();
    String URL = "http://www.test.com/1.jpg";

    @Test
    public void verifyImageURLIsValid() {
        try (MockedStatic<ImageIO> ignored = mockStatic(ImageIO.class)) {
            given(ImageIO.read(new URL(URL))).willAnswer(invocation -> {
                BufferedImage result = new BufferedImage(600, 400, BufferedImage.TYPE_INT_RGB); // create a BufferedImage object
                 return result;
            });

            boolean isValid = imageService.isValid(URL);
            assertTrue(isValid);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }


    }

    @Test
    public void verifyImageURLIsInvalid() {
        try (MockedStatic<ImageIO> ignored = mockStatic(ImageIO.class)) {
            given(ImageIO.read(new URL(URL))).willThrow();

            boolean isValid = imageService.isValid(URL);
            assertFalse(isValid);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    @AfterEach
    public void clear() {
        Mockito.framework().clearInlineMocks();
    }
}
