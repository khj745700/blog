package shop.khj745700.backend.domain.user.rest;

import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;
import shop.khj745700.backend.domain.user.rest.dto.NicknameModifyRequest;
import shop.khj745700.backend.domain.user.service.LogoutService;
import shop.khj745700.backend.domain.user.service.UserModifyService;
import shop.khj745700.backend.global.security.service.UserLoginContext;

@RequiredArgsConstructor
@RequestMapping("/user")
@RestController
public class UserRestController {
    private final LogoutService logoutService;
    private final UserModifyService userModifyService;

    @DeleteMapping("/logout")
    public ResponseEntity<Void> logout() {
        logoutService.logout();
        return ResponseEntity.ok().build();
    }

    @PutMapping("/nickname")
    public ResponseEntity<Void> nicknameUpdate(@RequestBody NicknameModifyRequest request, @AuthenticationPrincipal UserLoginContext userLoginContext) {
        userModifyService.nicknameModify(request, userLoginContext.getUserId());
        return ResponseEntity.ok().build();
    }

}