package shop.khj745700.backend.domain.hashtag.domain;

import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.List;

@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)

@Table(name = "hashtag")
@Entity
public class HashTag {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "hashtag_id")
    private Integer id;

    @Column(name = "hashtag_name")
    private String name;

    @Builder
    public HashTag(String name) {
        this.name = name.toLowerCase();
    }

    @OneToMany(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    private List<HashTagList> hashTagList;

}
