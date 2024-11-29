package shop.khj745700.backend.domain.user.rest.dto;

import lombok.*;

@Builder
@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class NicknameModifyRequest {
    private String nickname;
}
