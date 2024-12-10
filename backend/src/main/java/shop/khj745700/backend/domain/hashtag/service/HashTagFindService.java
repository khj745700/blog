package shop.khj745700.backend.domain.hashtag.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import shop.khj745700.backend.domain.hashtag.domain.dao.HashTagFinder;
import shop.khj745700.backend.domain.hashtag.rest.dto.HashTagCountView;
import shop.khj745700.backend.domain.hashtag.rest.dto.HashTagView;

import java.util.List;

@RequiredArgsConstructor
@Service
public class HashTagFindService {
    private final HashTagFinder hashTagFinder;

    public List<HashTagView> findAll(String name) {
        return hashTagFinder.findByNameLikeIgnore(name);
    }

    public List<HashTagCountView> findAllCount() {
        return hashTagFinder.findAllWithBoardCount();
    }
}
