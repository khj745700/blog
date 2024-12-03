package shop.khj745700.backend.domain.board.domain;

import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Slice;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import shop.khj745700.backend.domain.board.rest.dto.BoardView;

public interface BoardRepository extends JpaRepository<Board, Integer> {

    @Query("SELECT new shop.khj745700.backend.domain.board.rest.dto.BoardView(b) FROM Board b")
    Slice<BoardView> findAllBoards(Pageable pageable);
}
