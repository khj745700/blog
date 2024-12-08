package shop.khj745700.backend.domain.hashtag.rest;

import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import shop.khj745700.backend.domain.hashtag.rest.dto.HashTagView;
import shop.khj745700.backend.domain.hashtag.service.HashTagRegisterService;
import shop.khj745700.backend.domain.hashtag.service.dto.HashTagRegisterRequest;

@RequiredArgsConstructor
@RequestMapping("/hashtags")
@RestController
public class HashTagRestController {
    private final HashTagRegisterService hashTagRegisterService;

    @PutMapping
    public ResponseEntity<HashTagView> putHashTag(@RequestBody HashTagRegisterRequest request) {
        return ResponseEntity.ok(hashTagRegisterService.hashTagRegister(request));
    }
}
