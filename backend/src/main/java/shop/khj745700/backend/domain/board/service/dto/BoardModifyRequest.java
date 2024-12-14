package shop.khj745700.backend.domain.board.service.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;


@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class BoardModifyRequest {
    private String description;
    private String title;
    private String thumbnailUrl;
    private Integer boardId;
    private List<Integer> hashTagIds;
}
