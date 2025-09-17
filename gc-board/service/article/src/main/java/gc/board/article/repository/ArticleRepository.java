package gc.board.article.repository;

import gc.board.article.entity.Article;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

//데이터베이스에 접근해주는 코드..
@Repository
public interface ArticleRepository extends JpaRepository<Article,Long> {

}
