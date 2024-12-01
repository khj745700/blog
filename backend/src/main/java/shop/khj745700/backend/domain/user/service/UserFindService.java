package shop.khj745700.backend.domain.user.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import shop.khj745700.backend.domain.user.domain.dao.UserDto;
import shop.khj745700.backend.domain.user.domain.dao.UserFinder;

@RequiredArgsConstructor
@Service
public class UserFindService {
    private final UserFinder userFinder;

    public UserDto userFind(Integer id) {
        return userFinder.surelyFindUserDto(id);
    }
}
