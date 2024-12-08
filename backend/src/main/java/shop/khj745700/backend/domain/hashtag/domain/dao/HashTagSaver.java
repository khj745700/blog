package shop.khj745700.backend.domain.hashtag.domain.dao;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;
import shop.khj745700.backend.domain.hashtag.domain.HashTag;
import shop.khj745700.backend.domain.hashtag.domain.HashTagRepository;
import shop.khj745700.backend.domain.hashtag.rest.dto.HashTagView;

@RequiredArgsConstructor
@Repository
public class HashTagSaver {
    private final HashTagRepository hashTagRepository;


    public HashTagView saveHashTag(HashTag hashTag) {

       return new HashTagView(hashTagRepository.save(hashTag));
    }
}
