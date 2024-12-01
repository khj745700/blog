package shop.khj745700.backend.domain.board.service.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;


@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class BoardModifyRequest {
    private String description;
    private String title;
    private Integer thumbnailId;
    private Boolean isHidden;
    private Integer boardId;
}
