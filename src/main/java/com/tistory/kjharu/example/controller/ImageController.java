package com.tistory.kjharu.example.controller;


import com.tistory.kjharu.example.dto.ImageInfoDto;
import com.tistory.kjharu.example.service.ImageService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
@RequiredArgsConstructor
public class ImageController {

    private final ImageService imageService;

    @GetMapping({"","/"})
    public String getImagePath(final Model model){
        String view = "index.html";

        ImageInfoDto imageInfoDto = imageService.getImageInfo();
        model.addAttribute("imagePath", imageInfoDto.getImagePath());
        model.addAttribute("imageFile", imageInfoDto.getImageFile());

        return view;
    }
}
