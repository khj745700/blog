package shop.khj745700.backend.domain.hashtag.domain;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface HashTagListRepository extends JpaRepository<HashTagList, Integer> {
    @Query("""
        SELECT htl.board.id, ht.id, ht.name
            FROM HashTagList htl
            JOIN htl.hashTag ht
            WHERE htl.board.id IN :boardIds
    """)
    List<Object[]> findHashTagsByBoardIds(@Param("boardIds") List<Integer> boardIds);

    void deleteAllByBoard_Id(Integer boardId);
}
