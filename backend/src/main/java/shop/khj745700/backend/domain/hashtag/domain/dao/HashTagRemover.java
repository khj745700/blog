package shop.khj745700.backend.domain.hashtag.domain.dao;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import shop.khj745700.backend.domain.hashtag.domain.HashTagListRepository;
import shop.khj745700.backend.domain.hashtag.domain.HashTagRepository;


@RequiredArgsConstructor
@Repository
public class HashTagRemover {
    private final HashTagRepository hashTagRepository;
    private final HashTagListRepository hashTagListRepository;

    @Transactional
    public void relationship(Integer boardId) {
        hashTagListRepository.deleteAllByBoard_Id(boardId);
    }
}
