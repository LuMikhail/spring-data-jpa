package homeworke7.dao;

import homeworke7.domain.Comment;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatCode;

@DataJpaTest
class CommentDaoTest {

    private static final long FIRST_COMMENT_ID = 1;
    private static final String FIRST_COMMENT_FIRST_NAME = "comment_book_01";
    private static final int COMMENT_COUNT = 4;

    @Autowired
    private CommentDao commentDao;

    @Test
    void shouldAddComment() {
        commentDao.save(new Comment("Было интересно"));
        assertThat(commentDao.findAll()).hasSize(COMMENT_COUNT + 1);
    }

    @Test
    void shouldReturnCommentById() {
        Optional<Comment> comments = commentDao.findById(FIRST_COMMENT_ID);
        assertThat(comments).isNotEmpty().get()
                .hasFieldOrPropertyWithValue("comment", FIRST_COMMENT_FIRST_NAME);
    }

    @Test
    void shouldFindAllComments() {
        List<Comment> comments = commentDao.findAll();
        assertThat(comments).hasSize(COMMENT_COUNT);
    }

    @Test
    void shouldCorrectDeleteCommentById() {
        assertThatCode(() -> commentDao.findById(FIRST_COMMENT_ID))
                .doesNotThrowAnyException();

        commentDao.deleteById(FIRST_COMMENT_ID);
        assertThat(commentDao.findAll()).hasSize(COMMENT_COUNT - 1);
    }
}
