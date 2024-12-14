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
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Integer id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "hashtag_id")
    private HashTag hashTag;

    @ManyToOne
    @JoinColumn(name = "board_id")
    private Board board;


    public HashTagList(HashTag hashTag, Board board) {
        this.hashTag = hashTag;
        this.board = board;
    }
}
