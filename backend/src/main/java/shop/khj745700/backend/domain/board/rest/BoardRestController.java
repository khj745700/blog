package shop.khj745700.backend.domain.board.rest;

import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import shop.khj745700.backend.domain.board.rest.dto.BoardIdView;
import shop.khj745700.backend.domain.board.service.BoardTempGenerateService;


@RequiredArgsConstructor
@RequestMapping("/board")
@RestController
public class BoardRestController {
    private final BoardTempGenerateService boardTempGenerateService;

    @PostMapping("/create")
    public ResponseEntity<BoardIdView> createBoard() {
        Integer id = boardTempGenerateService.boardGenerate();
        BoardIdView boardIdView = new BoardIdView(id);
        return ResponseEntity.ok(boardIdView);
    }
}
