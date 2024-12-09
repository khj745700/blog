package shop.khj745700.backend.domain.hashtag.domain.dao;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import shop.khj745700.backend.domain.hashtag.domain.HashTagRepository;
import shop.khj745700.backend.domain.hashtag.rest.dto.HashTagCountView;
import shop.khj745700.backend.domain.hashtag.rest.dto.HashTagView;

import java.util.List;
import java.util.stream.Collectors;

@RequiredArgsConstructor
@Repository
public class HashTagFinder {
    private final HashTagRepository hashTagRepository;

    @Transactional(readOnly = true)
    public HashTagView findByName(String name) {
        return new HashTagView(hashTagRepository.findByName(name));
    }

    @Transactional(readOnly = true)
    public List<HashTagView> findByNameLikeIgnore(String name) {
        return hashTagRepository.findByNameLikeIgnoreCase(name).stream().map(HashTagView::new).collect(Collectors.toList());
    }

    @Transactional(readOnly = true)
    public List<HashTagCountView> findAllWithBoardCount() {
        return hashTagRepository.findAllCounts();
    }
}
