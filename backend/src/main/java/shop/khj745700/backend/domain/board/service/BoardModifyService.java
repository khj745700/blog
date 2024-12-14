package shop.khj745700.backend.domain.board.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import shop.khj745700.backend.domain.board.domain.Board;
import shop.khj745700.backend.domain.board.domain.dao.BoardFinder;
import shop.khj745700.backend.domain.board.domain.dao.BoardSaver;
import shop.khj745700.backend.domain.board.service.dto.BoardModifyRequest;
import shop.khj745700.backend.domain.hashtag.domain.dao.HashTagRemover;
import shop.khj745700.backend.domain.hashtag.domain.dao.HashTagSaver;

import java.time.LocalDateTime;

@RequiredArgsConstructor
@Service
public class BoardModifyService {
    private final BoardSaver boardSaver;
    private final BoardFinder boardFinder;
    private final HashTagRemover hashTagRemover;
    private final HashTagSaver hashTagSaver;

    public void modifyBoard(BoardModifyRequest request) {
        Board board = boardFinder.surelyFindById(request.getBoardId());
        hashTagRemover.relationship(board.getId());
        board.updateBoard(request, board.getThumbnailUrl());
        hashTagSaver.saveHashTagsByBoardId(request.getHashTagIds(), board);
        boardSaver.boardUpdate(board);
    }

    public void tempBoardToBoard(Integer boardId, LocalDateTime now) {
        Board board = boardFinder.surelyFindById(boardId);
        board.enroll(now);
        boardSaver.boardUpdate(board);
    }
}
