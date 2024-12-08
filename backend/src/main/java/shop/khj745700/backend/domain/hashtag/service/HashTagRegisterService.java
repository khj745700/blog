package shop.khj745700.backend.domain.hashtag.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import shop.khj745700.backend.domain.hashtag.domain.HashTag;
import shop.khj745700.backend.domain.hashtag.domain.dao.HashTagFinder;
import shop.khj745700.backend.domain.hashtag.domain.dao.HashTagSaver;
import shop.khj745700.backend.domain.hashtag.rest.dto.HashTagView;
import shop.khj745700.backend.domain.hashtag.service.dto.HashTagRegisterRequest;

@RequiredArgsConstructor
@Service
public class HashTagRegisterService {
    private final HashTagFinder hashTagFinder;
    private final HashTagSaver hashTagSaver;

    public HashTagView hashTagRegister(HashTagRegisterRequest request) {
        HashTagView byName = hashTagFinder.findByName(request.getHashTag());
        if(byName != null) {
            return byName;
        }

        return hashTagSaver.saveHashTag(new HashTag(request.getHashTag()));
    }
}
