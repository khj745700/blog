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
    private String imageUrl;

    public UserDto(User user) {
        this.id = user.getId();
        this.description = user.getDescription();
        this.nickname = user.getNickname();
        this.imageUrl = user.getImageUrl();
    }
}
