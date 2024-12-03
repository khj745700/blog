package shop.khj745700.backend.domain.board.service;


import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Slice;
import org.springframework.stereotype.Service;
import shop.khj745700.backend.domain.board.domain.dao.BoardFinder;
import shop.khj745700.backend.domain.board.rest.dto.BoardView;

@RequiredArgsConstructor
@Service
public class BoardFindService {
    private final BoardFinder boardFinder;


    public BoardView findById(Integer id) {
        return boardFinder.surelyFindBoardViewById(id);
    }

    public Slice<BoardView> findAll(Pageable pageable) {
        return boardFinder.surelyFindAllBoards(pageable);
    }
}
