package shop.khj745700.backend.domain.hashtag.service.dto;


import lombok.*;

@Builder
@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class HashTagRegisterRequest {
    private String hashTag;
}
