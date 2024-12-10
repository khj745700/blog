package shop.khj745700.backend.domain.hashtag.domain;


import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import shop.khj745700.backend.domain.board.domain.Board;

@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor
@Table(name = "hashtag_list")
@Entity
public class HashTagList {
    @Id
    private Integer id;

    @MapsId
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "hashtag_id")
    private HashTag hashTag;

    @ManyToOne
    @JoinColumn(name = "board_id")
    private Board board;
}
