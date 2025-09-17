package gc.board.article.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import java.time.LocalDateTime;

@Table(name = "article")
@Getter
@Entity
@ToString
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Article {
    @Id
    @Column(name = "article_id") // 명시적 매핑
    private long articleId;
    private String title;
    private String content;
    private long boardId;
    private long writerId;

    @CreationTimestamp // 생성 시간 자동 주입
    @Column(name = "created_at", nullable = false, updatable = false)
    private LocalDateTime createdAt;

    @UpdateTimestamp // 수정 시간 자동 주입
    @Column(name = "modified_at")
    private LocalDateTime modifiedAt;

    public static Article create(
            long articleId, String title, String content, long boardId, long writerId
    ) {
        Article article = new Article();
        article.articleId = articleId;
        article.title = title;
        article.content = content;
        article.boardId = boardId;
        article.writerId = writerId;
        return article;
    }

    public void update(String title, String content) {
        this.title = title;
        this.content = content;
    }
}