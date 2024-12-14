package shop.khj745700.backend.domain.hashtag.domain.dao;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import shop.khj745700.backend.domain.board.domain.Board;
import shop.khj745700.backend.domain.hashtag.domain.HashTag;
import shop.khj745700.backend.domain.hashtag.domain.HashTagList;
import shop.khj745700.backend.domain.hashtag.domain.HashTagListRepository;
import shop.khj745700.backend.domain.hashtag.domain.HashTagRepository;
import shop.khj745700.backend.domain.hashtag.rest.dto.HashTagView;

import java.util.List;

@RequiredArgsConstructor
@Repository
public class HashTagSaver {
    private final HashTagRepository hashTagRepository;
    private final HashTagListRepository hashTagListRepository;

    public HashTagView saveHashTag(HashTag hashTag) {

       return new HashTagView(hashTagRepository.save(hashTag));
    }

    @Transactional(readOnly = true)
    public void saveHashTagsByBoardId(List<Integer> hashTagIds, Board board) {
        List<HashTagList> hashTagLists = hashTagIds.stream().map(id -> new HashTagList(hashTagRepository.getReferenceById(id), board)).toList();
        hashTagListRepository.saveAll(hashTagLists);
    }
}
