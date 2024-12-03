package shop.khj745700.backend.domain.board.service;


import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import shop.khj745700.backend.domain.board.domain.dao.BoardFinder;
import shop.khj745700.backend.domain.board.service.dto.BoardView;

@RequiredArgsConstructor
@Service
public class BoardFindService {
    private final BoardFinder boardFinder;


    public BoardView findById(Integer id) {
        return boardFinder.surelyFindBoardViewById(id);
    }
}
