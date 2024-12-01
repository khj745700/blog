package shop.khj745700.backend.domain.file.domain.dao;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import shop.khj745700.backend.domain.image.domain.ImageRepository;
import shop.khj745700.backend.domain.image.domain.Image;

@RequiredArgsConstructor
@Repository
public class ImageStorer {
    private final ImageRepository imageRepository;

    @Transactional
    public Image saveImage(Image image) {
        return imageRepository.save(image);
    }
}
