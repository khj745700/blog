package shop.khj745700.backend.domain.user.domain;

import jakarta.persistence.*;
import lombok.*;
import shop.khj745700.backend.domain.user.domain.password.Password;
import shop.khj745700.backend.domain.user.domain.password.PasswordConverter;

@Builder
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor
@Table(name = "user")
@Entity
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "user_id")
    private Integer id;

    @Column(name = "username")
    private String username;

    @Convert(converter = PasswordConverter.class)
    @Column(name = "password")
    private Password password;

    @Column(name = "nickname")
    private String nickname;

    @Column(name = "description")
    private String description;

    @Column(name = "image_url")
    private String imageUrl;

    public void updateNickname(String nickname) {
        this.nickname = nickname;
    }
    public void updateImage(String image) {
        this.imageUrl = image;
    }
}