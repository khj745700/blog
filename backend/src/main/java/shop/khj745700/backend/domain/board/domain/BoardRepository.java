package shop.khj745700.backend.domain.board.domain;

import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Slice;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import shop.khj745700.backend.domain.board.rest.dto.BoardView;

public interface BoardRepository extends JpaRepository<Board, Integer> {

    @Query("SELECT new shop.khj745700.backend.domain.board.rest.dto.BoardView(b) FROM Board b WHERE b.wroteDate IS NOT NULL AND b.isHidden = false ORDER BY b.wroteDate DESC ")
    Slice<BoardView> findAllBoards(Pageable pageable);

    @Query("SELECT new shop.khj745700.backend.domain.board.rest.dto.BoardView(b) FROM HashTag ht LEFT JOIN HashTagList htl ON ht = htl.hashTag LEFT JOIN Board b ON htl.board = b WHERE ht.id = :id AND b.isHidden = false AND b.wroteDate != null ORDER BY b.wroteDate DESC ")
    Slice<BoardView> findAllBoardsByHashTagId(Integer id, Pageable pageable);
}
