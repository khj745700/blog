package shop.khj745700.backend.domain.board.domain.dao;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;
import shop.khj745700.backend.domain.board.domain.Board;
import shop.khj745700.backend.domain.board.domain.BoardRepository;

@RequiredArgsConstructor
@Repository
public class BoardSaver {
    private final BoardRepository boardRepository;

    public Integer tempBoardCreate() {
        return boardRepository.save(new Board()).getId();
    }

    public Integer boardUpdate(Board board) {
        return boardRepository.save(board).getId();
    }
}
