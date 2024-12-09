package shop.khj745700.backend.domain.hashtag.rest.dto;

import lombok.*;
import shop.khj745700.backend.domain.hashtag.domain.HashTag;

@Builder
@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class HashTagCountView {
    private Integer hashtagId;
    private String hashtag;
    private Integer count;

    public HashTagCountView(HashTag hashTag, Long hashtagCount) {
        this.hashtagId = hashTag.getId();
        this.hashtag = hashTag.getName();
    }
}
