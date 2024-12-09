package shop.khj745700.backend.domain.hashtag.rest;

import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import shop.khj745700.backend.domain.hashtag.rest.dto.HashTagCountView;
import shop.khj745700.backend.domain.hashtag.rest.dto.HashTagView;
import shop.khj745700.backend.domain.hashtag.service.HashTagFindService;
import shop.khj745700.backend.domain.hashtag.service.HashTagRegisterService;
import shop.khj745700.backend.domain.hashtag.service.dto.HashTagRegisterRequest;

import java.util.List;

@RequiredArgsConstructor
@RequestMapping("/hashtags")
@RestController
public class HashTagRestController {
    private final HashTagRegisterService hashTagRegisterService;
    private final HashTagFindService hashTagFindService;

    @PutMapping
    public ResponseEntity<HashTagView> putHashTag(@RequestBody HashTagRegisterRequest request) {
        return ResponseEntity.ok(hashTagRegisterService.hashTagRegister(request));
    }

    @GetMapping("/search")
    public ResponseEntity<List<HashTagView>> getAllHashTags(@RequestParam String keyword) {
        List<HashTagView> all = hashTagFindService.findAll(keyword);
        return ResponseEntity.ok(all);
    }

    @GetMapping("/counts")
    public ResponseEntity<List<HashTagCountView>> getAllHashTagCounts() {
        List<HashTagCountView> allCount = hashTagFindService.findAllCount();
        return ResponseEntity.ok(allCount);
    }
}
