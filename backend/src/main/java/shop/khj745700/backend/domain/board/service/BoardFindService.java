package shop.khj745700.backend.domain.board.service;


import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Slice;
import org.springframework.stereotype.Service;
import shop.khj745700.backend.domain.board.domain.dao.BoardFinder;
import shop.khj745700.backend.domain.board.rest.dto.BoardView;
import shop.khj745700.backend.domain.hashtag.domain.dao.HashTagFinder;
import shop.khj745700.backend.domain.hashtag.rest.dto.HashTagView;

import java.util.Collections;
import java.util.List;
import java.util.Map;

@RequiredArgsConstructor
@Service
public class BoardFindService {
    private final BoardFinder boardFinder;
    private final HashTagFinder hashTagFinder;


    public BoardView findById(Integer id) {
        BoardView boardView = boardFinder.surelyFindBoardViewById(id);
        Map<Integer, List<HashTagView>> hashTagsByBoardIds = hashTagFinder.getHashTagsByBoardIds(Collections.singletonList(id));
        boardView.setHashTags(hashTagsByBoardIds.getOrDefault(id, Collections.emptyList()));
        return boardView;
    }

    public Slice<BoardView> findAll(Pageable pageable) {
        Slice<BoardView> boardViews = boardFinder.surelyFindAllBoards(pageable);
        setHashTags(boardViews);
        return boardViews;
    }

    public Slice<BoardView> findByHashTagId(Integer hashtagId, Pageable pageable) {
        Slice<BoardView> allByHashTagId = boardFinder.findAllByHashTagId(hashtagId, pageable);

        setHashTags(allByHashTagId);
        return allByHashTagId;
    }

    private void setHashTags(Slice<BoardView> target) {
        List<Integer> boardIds = target.getContent().stream().map(BoardView::getId).toList();
        Map<Integer, List<HashTagView>> hashTagsByBoardIds = hashTagFinder.getHashTagsByBoardIds(boardIds);

        target.forEach(boardView -> {
            boardView.setHashTags(hashTagsByBoardIds.getOrDefault(boardView.getId(), Collections.emptyList()));
        });

    }
}
