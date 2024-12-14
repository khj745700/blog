package shop.khj745700.backend.domain.board.domain;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import shop.khj745700.backend.domain.board.service.dto.BoardModifyRequest;

import java.time.LocalDateTime;


@Getter
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "board", indexes = {@Index(name = "wrote_date_reverse__idx_1", columnList = "wroteDate DESC")})
@Entity
public class Board {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "board_id")
    private Integer id;

    @Column(name = "title")
    private String title;

    @Column(name = "description", columnDefinition = "text")
    private String description;

    @Column(name = "thumbnailUrl")
    private String thumbnailUrl;

    @Column(name = "wrote_date")
    private LocalDateTime wroteDate;

    @Column(name = "is_hidden")
    private Boolean isHidden;

    @PrePersist
    private void prePersist() {
        this.isHidden = Boolean.FALSE;
    }


    public void updateBoard(BoardModifyRequest request, String thumbnailUrl) {
        this.title = request.getTitle();
        this.description = request.getDescription();
        this.thumbnailUrl = thumbnailUrl;
    }

    public void enroll(LocalDateTime localDateTime) {
        this.wroteDate = localDateTime;
    }
}
