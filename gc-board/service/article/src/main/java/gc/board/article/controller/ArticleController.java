package gc.board.article.controller;

import gc.board.article.service.ArticleService;
import gc.board.article.service.reponse.ArticlePageResponse;
import gc.board.article.service.reponse.ArticleResponse;
import gc.board.article.service.request.ArticleCreateRequest;
import gc.board.article.service.request.ArticleUpdateRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
public class ArticleController {
    private final ArticleService articleService;

    //읽기
    @GetMapping("/v1/articles/{articleId}")
    public ArticleResponse read(@PathVariable long articleId) {
        return articleService.read(articleId);
    }

    //목록조회
    @GetMapping("/v1/articles")
    public ArticlePageResponse readAll(
            @RequestParam("boardId") Long boardId,
            @RequestParam("page") Long page,
            @RequestParam("pageSize") Long pageSize
    ) {
        return articleService.readAll(boardId, page, pageSize);
    }

    //생성
    @PostMapping("/v1/articles")
    public ArticleResponse create(@RequestBody ArticleCreateRequest request) {
        return articleService.create(request);
    }
    //수정
    @PutMapping("/v1/articles/{articleId}")
    public ArticleResponse update(
            @PathVariable long articleId,
            @RequestBody ArticleUpdateRequest request
    ) {
        return articleService.update(request, articleId);
    }

    @DeleteMapping("/v1/articles/{articleId}")
    public void delete(@PathVariable long articleId) {
        articleService.delete(articleId);
    }
    //삭제

}
