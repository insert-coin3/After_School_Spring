package gc.board.article.service;

import gc.board.article.entity.Article;
import gc.board.article.repository.ArticleRepository;
import gc.board.article.service.reponse.ArticleResponse;
import gc.board.article.service.request.ArticleCreateRequest;
import gc.board.article.service.request.ArticleUpdateRequest;
import jakarta.transaction.Transactional;
import kuke.board.common.snowflake.Snowflake;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

//핵심 비지니스 로직 코드 작성...
//근데.. DTO라는 재료가 필요함.
//DTO는 컨트롤러에 주고받는 요청/응답 타입.
@Service
@RequiredArgsConstructor
public class ArticleService {
    //필요한 의존성들 추가!
    private final Snowflake snowflake = new Snowflake();
    private final ArticleRepository articleRepository;

    //생성!
    @Transactional
    public ArticleResponse create(ArticleCreateRequest request) {
        Article article = articleRepository.save(
                Article.create(
                        snowflake.nextId(), request.getTitle(),
                        request.getContent(), request.getBoardId(),
                        request.getWriterId()
                )
        );
        return ArticleResponse.from(article);
    }
    //수정!
    @Transactional
    public ArticleResponse update(ArticleUpdateRequest request, Long articleId) {
        Article article = articleRepository.findById(articleId).orElseThrow();
        article.update(request.getTitle(), request.getContent());
        return ArticleResponse.from(article);
    }
    //읽기!
    //사용자한테 articleId를 받아서 레포지토리로 찾아서 반환(없으면 예외던지기)
    public ArticleResponse read(Long articleId) {
        Article article = articleRepository.findById(articleId).orElseThrow();
        return ArticleResponse.from(article);
    }

    //삭제!
    //레포지토리.deleteById(articleId) void형으로 반환
    @Transactional
    public void delete(Long articleId) {
        articleRepository.deleteById(articleId);
    }
}
