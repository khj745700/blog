package shop.khj745700.backend.domain.user.domain.dao;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import shop.khj745700.backend.domain.user.domain.User;
import shop.khj745700.backend.domain.user.domain.UserRepository;
import shop.khj745700.backend.domain.user.exception.UserNotFoundException;

@RequiredArgsConstructor
@Repository
public class UserFinder {
    private final UserRepository userRepository;

    @Transactional(readOnly = true)
    public User read(Integer userId) {
        return userRepository.getReferenceById(userId);
    }

    @Transactional(readOnly = true)
    public User surelyFindById(Integer userId) {
        return userRepository.findById(userId).orElseThrow(() -> new UserNotFoundException(userId));
    }
}
