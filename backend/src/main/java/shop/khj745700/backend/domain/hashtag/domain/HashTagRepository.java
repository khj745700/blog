package shop.khj745700.backend.domain.hashtag.domain;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface HashTagRepository extends JpaRepository<HashTag, Integer> {

    @Query("SELECT ht FROM HashTag ht WHERE :name = LOWER(ht.name) ")
    HashTag findByName(@Param("name") String name);
}
