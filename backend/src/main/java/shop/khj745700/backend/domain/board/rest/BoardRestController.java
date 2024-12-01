package shop.khj745700.backend.domain.board.rest;

import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import shop.khj745700.backend.domain.board.rest.dto.BoardIdView;
import shop.khj745700.backend.domain.board.service.BoardModifyService;
import shop.khj745700.backend.domain.board.service.BoardTempGenerateService;
import shop.khj745700.backend.domain.board.service.dto.BoardModifyRequest;


@RequiredArgsConstructor
@RequestMapping("/board")
@RestController
public class BoardRestController {
    private final BoardTempGenerateService boardTempGenerateService;
    private final BoardModifyService boardModifyService;

    @PostMapping("/create")
    public ResponseEntity<BoardIdView> createBoard() {
        Integer id = boardTempGenerateService.boardGenerate();
        BoardIdView boardIdView = new BoardIdView(id);
        return ResponseEntity.ok(boardIdView);
    }

    @PutMapping("/update")
    public ResponseEntity<Void> updateBoard(@RequestBody BoardModifyRequest request) {
        boardModifyService.modifyBoard(request);
        return ResponseEntity.ok().build();
    }


}
