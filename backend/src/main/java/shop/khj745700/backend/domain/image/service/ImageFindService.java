package shop.khj745700.backend.domain.image.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import shop.khj745700.backend.domain.image.domain.ImageRepository;

@RequiredArgsConstructor
@Service
public class ImageFindService {
    private final ImageRepository imageRepository;


}
