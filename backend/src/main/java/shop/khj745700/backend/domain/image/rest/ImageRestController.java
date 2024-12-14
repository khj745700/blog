package shop.khj745700.backend.domain.image.rest;

import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;
import shop.khj745700.backend.domain.image.domain.Image;
import shop.khj745700.backend.domain.image.service.ImageUploadService;

import java.net.URI;
import java.time.LocalDate;

@RequestMapping("/images")
@RequiredArgsConstructor
@RestController
public class ImageRestController {
    private final ImageUploadService imageUploadService;

    @PostMapping("")
    public ResponseEntity<?> uploadImage(@RequestParam("file") MultipartFile file) {
        Image image = imageUploadService.saveImages(file, LocalDate.now());
        String path = image.getPath();
        String domain = "https://blog.khj745700.shop";
        String url = domain + path;
        return ResponseEntity.created(URI.create(url)).build();
    }
}
