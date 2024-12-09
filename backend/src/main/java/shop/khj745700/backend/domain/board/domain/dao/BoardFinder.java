package shop.khj745700.backend.domain.board.domain.dao;

import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Slice;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import shop.khj745700.backend.domain.board.domain.Board;
import shop.khj745700.backend.domain.board.domain.BoardRepository;
import shop.khj745700.backend.domain.board.exception.BoardNotFoundException;
import shop.khj745700.backend.domain.board.rest.dto.BoardView;
import shop.khj745700.backend.domain.hashtag.domain.HashTag;
import shop.khj745700.backend.domain.hashtag.domain.HashTagRepository;

import java.util.List;

@RequiredArgsConstructor
@Repository
public class BoardFinder {
    private final BoardRepository boardRepository;
    private final HashTagRepository hashTagRepository;

    @Transactional(readOnly = true)
    public Board surelyFindById(Integer id) {
        return boardRepository.findById(id).orElseThrow(() -> new BoardNotFoundException(id));
    }

    @Transactional(readOnly = true)
    public BoardView surelyFindBoardViewById(Integer id) {
        Board board = surelyFindById(id);
        List<HashTag> allByHashTagListBoardId = hashTagRepository.findAllByHashTagList_Board_Id(id);
        return new BoardView(board);
    }

    @Transactional(readOnly = true)
    public Slice<BoardView> surelyFindAllBoards(Pageable pageable) {
        return boardRepository.findAllBoards(pageable);
    }

    @Transactional(readOnly = true)
    public Slice<BoardView> findAllByHashTagId(Integer hashTagId, Pageable pageable) {
        return boardRepository.findAllBoardsByHashTagId(hashTagId, pageable);
    }
}
