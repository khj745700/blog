package shop.khj745700.backend.domain.user.service;

import jakarta.servlet.http.HttpSession;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor
@Service
public class LogoutService {
    private final HttpSession httpSession;

    public void logout() {
        httpSession.invalidate();
    }
}
