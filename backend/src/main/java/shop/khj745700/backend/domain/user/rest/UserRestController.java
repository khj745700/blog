package shop.khj745700.backend.domain.user.rest;

import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import shop.khj745700.backend.domain.user.service.LogoutService;
import shop.khj745700.backend.global.security.service.UserLoginContext;

@RequiredArgsConstructor
@RequestMapping("/user")
@RestController
public class UserRestController {
    private final LogoutService logoutService;

    @DeleteMapping("/logout")
    public ResponseEntity<Void> logout() {
        logoutService.logout();
        return ResponseEntity.ok().build();
    }
}
