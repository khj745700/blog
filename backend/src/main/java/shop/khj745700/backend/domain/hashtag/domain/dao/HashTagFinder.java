package shop.khj745700.backend.domain.hashtag.domain.dao;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import shop.khj745700.backend.domain.hashtag.domain.HashTag;
import shop.khj745700.backend.domain.hashtag.domain.HashTagListRepository;
import shop.khj745700.backend.domain.hashtag.domain.HashTagRepository;
import shop.khj745700.backend.domain.hashtag.rest.dto.HashTagCountView;
import shop.khj745700.backend.domain.hashtag.rest.dto.HashTagView;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@RequiredArgsConstructor
@Repository
public class HashTagFinder {
    private final HashTagRepository hashTagRepository;
    private final HashTagListRepository hashTagListRepository;

    @Transactional(readOnly = true)
    public HashTagView findByName(String name) {
        HashTag h = hashTagRepository.findByName(name);
        if (h == null) {
            return null;
        }
        return new HashTagView(h);
    }

    @Transactional(readOnly = true)
    public List<HashTagView> findByNameLikeIgnore(String name) {
        return hashTagRepository.findByNameLikeIgnoreCase(name).stream().map(HashTagView::new).collect(Collectors.toList());
    }

    @Transactional(readOnly = true)
    public List<HashTagCountView> findAllWithBoardCount() {
        return hashTagRepository.findAllCounts();
    }


    public Map<Integer, List<HashTagView>> getHashTagsByBoardIds(List<Integer> boardIds) {
        List<Object[]> results = hashTagListRepository.findHashTagsByBoardIds(boardIds);

        return results.stream()
                .collect(Collectors.groupingBy(
                        result -> (Integer) result[0],
                        Collectors.mapping(
                                result -> new HashTagView((Integer) result[1], (String) result[2]),
                                Collectors.toList()
                        )
                ));
    }
}
