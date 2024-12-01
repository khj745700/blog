package shop.khj745700.backend.domain.board.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import shop.khj745700.backend.domain.board.domain.dao.BoardSaver;

@RequiredArgsConstructor
@Service
public class BoardTempGenerateService {
    private final BoardSaver boardSaver;

    public Integer boardGenerate() {
        return boardSaver.tempBoardCreate();
    }
}
