package shop.khj745700.backend.domain.board.rest;

import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Slice;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import shop.khj745700.backend.domain.board.rest.dto.BoardIdView;
import shop.khj745700.backend.domain.board.rest.dto.BoardView;
import shop.khj745700.backend.domain.board.service.BoardFindService;
import shop.khj745700.backend.domain.board.service.BoardModifyService;
import shop.khj745700.backend.domain.board.service.BoardTempGenerateService;
import shop.khj745700.backend.domain.board.service.dto.BoardModifyRequest;

import java.time.LocalDateTime;


@RequiredArgsConstructor
@RequestMapping("/board")
@RestController
public class BoardRestController {
    private final BoardTempGenerateService boardTempGenerateService;
    private final BoardModifyService boardModifyService;
    private final BoardFindService boardFindService;

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

    @PutMapping("/enroll")
    public ResponseEntity<Void> enrollBoard(@RequestBody BoardIdView boardIdView) {
        boardModifyService.tempBoardToBoard(boardIdView.getBoardId(), LocalDateTime.now());
        return ResponseEntity.ok().build();
    }

    @GetMapping("/{boardId}")
    public ResponseEntity<BoardView> getBoard(@PathVariable Integer boardId) {
        BoardView byId = boardFindService.findById(boardId);
        return ResponseEntity.ok(byId);
    }

    @GetMapping("/paging")
    public ResponseEntity<Slice<BoardView>> getAllBoards(Pageable pageable) {
        Slice<BoardView> all = boardFindService.findAll(pageable);
        return ResponseEntity.ok(all);
    }
}
