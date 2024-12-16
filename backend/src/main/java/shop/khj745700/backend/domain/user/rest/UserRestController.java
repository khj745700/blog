package shop.khj745700.backend.domain.user.rest;

import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import shop.khj745700.backend.domain.user.domain.dao.UserDto;
import shop.khj745700.backend.domain.user.rest.dto.NicknameModifyRequest;
import shop.khj745700.backend.domain.user.service.LogoutService;
import shop.khj745700.backend.domain.user.service.UserFindService;
import shop.khj745700.backend.domain.user.service.UserModifyService;
import shop.khj745700.backend.global.security.service.UserLoginContext;

@RequiredArgsConstructor
@RequestMapping("/user")
@RestController
public class UserRestController {
    private final LogoutService logoutService;
    private final UserModifyService userModifyService;
    private final UserFindService userFindService;

    @GetMapping("/check")
    public ResponseEntity<Void> sessionCheck() {
        return ResponseEntity.ok().build();
    }

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

    @PutMapping("/profile")
    public ResponseEntity<Integer> updateProfile(@RequestPart(required = false)MultipartFile image, @AuthenticationPrincipal UserLoginContext userLoginContext) {
        Integer imageId = userModifyService.updateProfileImage(image, userLoginContext.getUserId());
        return ResponseEntity.ok(imageId);
    }

    @GetMapping("/profile/{id}")
    public ResponseEntity<UserDto> getProfile(@PathVariable Integer id) {
        UserDto userDto = userFindService.userFind(id);
        return ResponseEntity.ok(userDto);
    }
}
