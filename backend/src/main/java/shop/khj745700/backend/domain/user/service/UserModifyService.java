package shop.khj745700.backend.domain.user.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;
import shop.khj745700.backend.domain.image.domain.dao.ImageFinder;
import shop.khj745700.backend.domain.image.domain.Image;
import shop.khj745700.backend.domain.image.service.ImageUploadService;
import shop.khj745700.backend.domain.user.domain.User;
import shop.khj745700.backend.domain.user.domain.dao.UserFinder;
import shop.khj745700.backend.domain.user.domain.dao.UserSaver;
import shop.khj745700.backend.domain.user.rest.dto.NicknameModifyRequest;

import java.time.LocalDate;

@RequiredArgsConstructor
@Service
public class UserModifyService {
    private final UserFinder userFinder;
    private final ImageUploadService imageUploadService;
    private final ImageFinder imageFinder;
    private final UserSaver userSaver;

    @Transactional
    public void nicknameModify(NicknameModifyRequest request, Integer userId) {
        User user = userFinder.surelyFindById(userId);
        user.updateNickname(request.getNickname());
    }


    public Integer updateProfileImage(MultipartFile file, Integer userId) {
        User user = userFinder.surelyFindById(userId);
        if(file == null || file.isEmpty()) {
            user.updateImage(null);
            userSaver.save(user);
            return null;
        }

        Image image = imageUploadService.saveImages(file, LocalDate.now());
        user.updateImage(image.getPath());
        userSaver.save(user);

        return image.getId();
    }
}
