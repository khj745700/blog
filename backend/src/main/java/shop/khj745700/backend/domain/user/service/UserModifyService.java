package shop.khj745700.backend.domain.user.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import shop.khj745700.backend.domain.user.domain.User;
import shop.khj745700.backend.domain.user.domain.dao.UserFinder;
import shop.khj745700.backend.domain.user.rest.dto.NicknameModifyRequest;

@RequiredArgsConstructor
@Service
public class UserModifyService {
    private final UserFinder userFinder;

    @Transactional
    public void nicknameModify(NicknameModifyRequest request, Integer userId) {
        User user = userFinder.surelyFindById(userId);
        user.updateNickname(request.getNickname());
    }
}
