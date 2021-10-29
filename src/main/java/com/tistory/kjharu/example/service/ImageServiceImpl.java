package com.tistory.kjharu.example.service;

import com.tistory.kjharu.example.dto.ImageInfoDto;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import java.net.URLConnection;
import java.util.Base64;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor
@Service
@Slf4j
public class ImageServiceImpl implements ImageService {

    private String getByteArrayFromImageURL(final URL imageUrl) {
        String out = null;
        try {
            final URLConnection ucon = imageUrl.openConnection();
            final InputStream is = ucon.getInputStream();
            final ByteArrayOutputStream baos = new ByteArrayOutputStream();
            final byte[] buffer = new byte[1024];
            int read;
            while ((read = is.read(buffer, 0, buffer.length)) != -1) {
                baos.write(buffer, 0, read);
            }
            baos.flush();
            out = new String(Base64.getEncoder().encode(baos.toByteArray()));
        } catch (IOException e) {
            if (log.isErrorEnabled()) {
                log.error("File Read Exception: {}", e.getMessage());
            }
        }
        return out;
    }

    @Override
    public ImageInfoDto getImageInfo(){
        URL imagePath = this.getClass().getClassLoader().getResource("static/images/handsome_cat.jpg");
        final String binaryFile = getByteArrayFromImageURL(imagePath);
        final String imageFile = "data:image/jpeg;base64," + binaryFile;
        return ImageInfoDto.builder().imagePath(imagePath).imageFile(imageFile).build();
    }
}