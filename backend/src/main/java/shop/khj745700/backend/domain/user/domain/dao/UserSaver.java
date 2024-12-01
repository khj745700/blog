package shop.khj745700.backend.domain.user.domain.dao;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;
import shop.khj745700.backend.domain.user.domain.User;
import shop.khj745700.backend.domain.user.domain.UserRepository;

@RequiredArgsConstructor
@Repository
public class UserSaver {
    private final UserRepository userRepository;

    public User save(User user) {
        return userRepository.save(user);
    }
}
