package shop.khj745700.backend.domain.file.domain.dao;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import shop.khj745700.backend.domain.image.domain.Image;
import shop.khj745700.backend.domain.image.domain.ImageRepository;

@RequiredArgsConstructor
@Repository
public class ImageFinder {
    private final ImageRepository imageRepository;

    @Transactional(readOnly = true)
    public Image read(Integer id) {
        return imageRepository.getReferenceById(id);
    }
}
