package shop.khj745700.backend.domain.board.domain.dao;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import shop.khj745700.backend.domain.board.domain.Board;
import shop.khj745700.backend.domain.board.domain.BoardRepository;
import shop.khj745700.backend.domain.board.exception.BoardNotFoundException;

@RequiredArgsConstructor
@Repository
public class BoardFinder {
    private final BoardRepository boardRepository;

    @Transactional(readOnly = true)
    public Board surelyFindById(Integer id) {
        return boardRepository.findById(id).orElseThrow(() -> new BoardNotFoundException(id));
    }
}
