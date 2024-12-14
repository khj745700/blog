package shop.khj745700.backend.domain.image.rest;

import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;
import shop.khj745700.backend.domain.image.service.ImageUploadService;

import java.time.LocalDate;

@RequestMapping("/images")
@RequiredArgsConstructor
@RestController
public class ImageRestController {
    private final ImageUploadService imageUploadService;

    @PostMapping("")
    public ResponseEntity<?> uploadImage(@RequestParam("file") MultipartFile file) {
        imageUploadService.saveImages(file, LocalDate.now());
        return ResponseEntity.ok().build();
    }
}
