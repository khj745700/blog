package shop.khj745700.backend.domain.hashtag.domain;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import shop.khj745700.backend.domain.hashtag.rest.dto.HashTagCountView;

import java.util.List;

public interface HashTagRepository extends JpaRepository<HashTag, Integer> {

    @Query("SELECT ht FROM HashTag ht WHERE :name = LOWER(ht.name) ")
    HashTag findByName(@Param("name") String name);

//    @Query("SELECT ht FROM HashTag ht WHERE :name LIKE LOWER(CONCAT('%', ht.name, '%')) ")
    List<HashTag> findByNameContainingIgnoreCase(@Param("name") String name);

    @Query("SELECT new shop.khj745700.backend.domain.hashtag.rest.dto.HashTagCountView(ht, COUNT(b.id)) " +
            "FROM HashTag ht " +
                "LEFT JOIN HashTagList htl ON ht.id = htl.hashTag.id " +
                "LEFT JOIN Board b ON htl.board.id = b.id " +
            "GROUP BY ht HAVING COUNT(b.id) > 0 ORDER BY ht.name ASC ")
    List<HashTagCountView> findAllCounts();


}
