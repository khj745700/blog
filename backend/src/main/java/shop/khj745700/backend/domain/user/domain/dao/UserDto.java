package shop.khj745700.backend.domain.user.domain.dao;

import lombok.*;
import shop.khj745700.backend.domain.user.domain.User;

@Builder
@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class UserDto {
    private Integer id;
    private String description;
    private String nickname;
    private Integer imageId;

    public UserDto(User user) {
        this.id = user.getId();
        this.description = user.getDescription();
        this.nickname = user.getNickname();
        this.imageId = user.getImage().getId();
    }
}
