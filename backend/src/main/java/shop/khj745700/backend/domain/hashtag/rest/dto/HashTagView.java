package shop.khj745700.backend.domain.hashtag.rest.dto;

import lombok.*;
import shop.khj745700.backend.domain.hashtag.domain.HashTag;

@Builder
@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class HashTagView {
    private Integer hashtagId;
    private String hashtag;

    public HashTagView(HashTag hashTag) {
        this.hashtagId = hashTag.getId();
        this.hashtag = hashTag.getName();
    }
}
