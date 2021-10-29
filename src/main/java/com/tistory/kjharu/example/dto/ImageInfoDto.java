package com.tistory.kjharu.example.dto;

import java.net.URL;
import lombok.Builder;
import lombok.Data;

@Data
public class ImageInfoDto {
    private URL imagePath;
    private String imageFile;

    @Builder
    public ImageInfoDto(URL imagePath, String imageFile) {
        this.imagePath = imagePath;
        this.imageFile = imageFile;
    }
}
