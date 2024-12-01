package shop.khj745700.backend.domain.image.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import shop.khj745700.backend.domain.file.domain.DefaultDirectory;
import shop.khj745700.backend.domain.file.domain.Directory;
import shop.khj745700.backend.domain.file.domain.dao.DirectoryFinder;
import shop.khj745700.backend.domain.file.domain.dao.ImageStorer;
import shop.khj745700.backend.domain.image.domain.CompressedImage;
import shop.khj745700.backend.domain.image.domain.Image;
import shop.khj745700.backend.domain.image.domain.ImageSize;
import shop.khj745700.backend.domain.image.utils.ImageCompressor;

import java.io.File;
import java.io.IOException;
import java.util.UUID;

@RequiredArgsConstructor
@Service
public class ImageUploadService {
    private final ImageCompressor imageCompressor;
    private final DirectoryFinder directoryFinder;
    private final ImageStorer imageStorer;

    private static final String TEMP_IMAGE_DIRECTORY = "./temp/images/";

//    public Integer saveBoardImage(MultipartFile multipartFile, Integer boardId) {
//
//    }

    public Integer saveProfileImage(MultipartFile multipartFile) {
        return saveImages(multipartFile, directoryFinder.getDefaultDirectory(DefaultDirectory.PROFILE_COMPRESS), directoryFinder.getDefaultDirectory(DefaultDirectory.PROFILE_ORIGINAL)).getId();
    }

    private Image saveImages(MultipartFile multipartFile, Directory compressDir, Directory originalDir) {
        File newFile = new File(TEMP_IMAGE_DIRECTORY + getRandomUUID() + getExtension(multipartFile));
        try {
            File compressFile = imageCompressor.resizeImage(multipartFile, newFile, getExtension(multipartFile), ImageSize.SMALL);
            File originalFile = imageCompressor.resizeImage(multipartFile, newFile, getExtension(multipartFile), ImageSize.ORIGINAL);

            Image newImage = generateNewImage();
            CompressedImage smallImage = createCompressedImage(newImage, shop.khj745700.backend.domain.file.domain.File.createNewFile()
                    .name(compressFile.getName())
                    .extension(getExtension(multipartFile))
                    .directory(compressDir)
                    .build(), ImageSize.SMALL);

            CompressedImage originalImage = createCompressedImage(newImage, shop.khj745700.backend.domain.file.domain.File.createNewFile()
                    .name(originalFile.getName())
                    .extension(getExtension(multipartFile))
                    .directory(originalDir)
                    .build(), ImageSize.ORIGINAL);

            newImage.addImage(originalImage);
            newImage.addImage(smallImage);

            return imageStorer.saveImage(newImage);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    private Image generateNewImage() {
        return imageStorer.saveImage(new Image());
    }

    private CompressedImage createCompressedImage(Image image, shop.khj745700.backend.domain.file.domain.File file, ImageSize imageSize) {
        return CompressedImage.createCompressedImageBuilder()
                .size(imageSize)
                .image(image)
                .file(file)
                .build();
    }

    private String getRandomUUID() {
        return UUID.randomUUID().toString();
    }

    private String getExtension(MultipartFile file) {

        return file.getOriginalFilename().substring(file.getOriginalFilename().lastIndexOf("."));
    }
}
