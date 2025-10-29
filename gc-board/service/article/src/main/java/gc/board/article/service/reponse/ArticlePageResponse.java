package gc.board.article.service.reponse;

import lombok.Getter;
import lombok.ToString;

import java.util.List;

@Getter
@ToString
public class ArticlePageResponse {
    //게시글 목록
    //전체 게시글갯수

    private List<ArticleResponse> articles;
    private Long articleCount;

    public static ArticlePageResponse of (
            List<ArticleResponse> articles, Long articlCount
    ) {
        ArticlePageResponse response = new ArticlePageResponse();
        response.articles = articles;
        response.articleCount = articlCount;
        return response;
    }
}
