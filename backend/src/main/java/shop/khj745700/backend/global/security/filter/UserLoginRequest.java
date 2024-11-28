package shop.khj745700.backend.global.security.filter;

import lombok.*;

@Builder
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class UserLoginRequest {
    private String username;
    private String password;
}
