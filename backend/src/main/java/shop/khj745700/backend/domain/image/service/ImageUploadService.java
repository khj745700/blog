package shop.khj745700.backend.domain.image.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import shop.khj745700.backend.domain.image.domain.Image;
import shop.khj745700.backend.domain.image.domain.dao.ImageStorer;
import shop.khj745700.backend.domain.image.utils.ImageCompressor;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.time.LocalDate;
import java.util.UUID;

@RequiredArgsConstructor
@Service
public class ImageUploadService {
    private final ImageCompressor imageCompressor;
    private final ImageStorer imageStorer;

//    public Integer saveBoardImage(MultipartFile multipartFile, Integer boardId) {
//
//    }

    public Image saveImages(MultipartFile multipartFile, LocalDate today) {
        File newFile = new File("/resources/"+today.toString()+ "/" + getRandomUUID() + getExtension(multipartFile));
        try {
//            File compressFile = imageCompressor.resizeImage(multipartFile, newFile, getExtension(multipartFile), ImageSize.SMALL);

            newFile.getParentFile().mkdirs();
            try(FileOutputStream fos = new FileOutputStream(newFile)) {
                fos.write(multipartFile.getBytes());
            }

            Image newImage = new Image(newFile.getPath());

            return imageStorer.saveImage(newImage);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    private String getRandomUUID() {
        return UUID.randomUUID().toString();
    }

    private String getExtension(MultipartFile file) {

        return file.getOriginalFilename().substring(file.getOriginalFilename().lastIndexOf("."));
    }
}
