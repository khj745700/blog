package shop.khj745700.backend.domain.board.rest.dto;

import lombok.*;
import shop.khj745700.backend.domain.board.domain.Board;

import java.time.LocalDateTime;

@Builder
@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class BoardView {
    private Integer id;
    private String title;
    private String description;
    private LocalDateTime wroteDate;
    private Boolean isHidden;

    public BoardView(Board board) {
        this.id = board.getId();
        this.title = board.getTitle();
        this.description = board.getDescription();
        this.wroteDate = board.getWroteDate();
        this.isHidden = board.getIsHidden();
    }
}
