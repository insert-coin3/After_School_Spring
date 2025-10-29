package gc.board.article.api;

import gc.board.article.service.reponse.ArticlePageResponse;
import gc.board.article.service.reponse.ArticleResponse;
import lombok.AllArgsConstructor;
import lombok.Getter;
import org.junit.jupiter.api.Test;
import org.springframework.web.client.RestClient;

public class ArticleApiTest {
    RestClient restClient = RestClient.create("http://localhost:9000");

    @Test
    void createTest() {
        ArticleResponse response = create(new ArticleCreateRequest(
                "hi", "my_content", 1L, 1L
        ));
        System.out.println("response = " + response);
    }

    ArticleResponse create(ArticleCreateRequest request) {
        return restClient.post()
                .uri("/v1/articles")
                .body(request)
                .retrieve() //api 호출!
                .body(ArticleResponse.class);
    }

    @Test
    void updateTest() {
        update(226663818521264128L);

    }
    private void update(Long articleId) {
        restClient.put()
                .uri("/v1/articles/{articleId}", articleId)
                .body(new ArticleUpdateRequest("hi2", "my content 2"))
                .retrieve();
    }

    @Test
    void readTest() {
        read(226663818521264128L);
    }
    ArticleResponse read(Long articleId) {
        return restClient.get()
                .uri("/v1/articles/{articleId}", articleId)
                .retrieve()
                .body(ArticleResponse.class);

    }

    @Test
    void deleteTest() {
        delete(226663818521264128L);
    }

    private void delete(Long articleId) {
        restClient.delete()
                .uri("/v1/articles/{articleId}", articleId)
                .retrieve()
                .toBodilessEntity();

    }

    @Test
    void readAllTest() {
        ArticlePageResponse response = restClient.get()
                .uri("/v1/articles?boardId=1&page=1&pageSize=30")
                .retrieve()
                .body(ArticlePageResponse.class);
        System.out.println("response.getArticleCount() = " + response.getArticleCount());
        for(ArticleResponse article : response.getArticles()) {
            System.out.println("article = " + article.getArticleId());
        }
    }

    @Getter
    @AllArgsConstructor
    static class ArticleCreateRequest {
        private String title;
        private String content;
        private Long boardId;
        private long writerId;

    }

    @Getter
    @AllArgsConstructor
    static class ArticleUpdateRequest {
        private String title;
        private String content;
    }
}

