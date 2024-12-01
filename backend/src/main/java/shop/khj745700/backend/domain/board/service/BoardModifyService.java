package shop.khj745700.backend.domain.board.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import shop.khj745700.backend.domain.board.domain.Board;
import shop.khj745700.backend.domain.board.domain.dao.BoardFinder;
import shop.khj745700.backend.domain.board.domain.dao.BoardSaver;
import shop.khj745700.backend.domain.board.service.dto.BoardModifyRequest;
import shop.khj745700.backend.domain.file.domain.dao.ImageFinder;
import shop.khj745700.backend.domain.image.domain.Image;

import java.time.LocalDateTime;

@RequiredArgsConstructor
@Service
public class BoardModifyService {
    private final BoardSaver boardSaver;
    private final BoardFinder boardFinder;
    private final ImageFinder imageFinder;

    public void modifyBoard(BoardModifyRequest request) {
        Board board = boardFinder.surelyFindById(request.getBoardId());
        Image read = null;
        if(request.getThumbnailId() != null) {
            read = imageFinder.read(request.getThumbnailId());
        }
        board.updateBoard(request, read);
        boardSaver.boardUpdate(board);
    }

    public void tempBoardToBoard(Integer boardId, LocalDateTime now) {
        Board board = boardFinder.surelyFindById(boardId);
        board.enroll(now);
        boardSaver.boardUpdate(board);
    }
}
