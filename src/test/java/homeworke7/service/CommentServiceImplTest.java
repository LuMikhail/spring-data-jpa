package homeworke7.service;

import homeworke7.dao.BookDao;
import homeworke7.dao.CommentDao;
import homeworke7.domain.Comment;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;

import static org.mockito.Mockito.verify;

@SpringBootTest
class CommentServiceImplTest {

    private static final long COMMENT_ID = 1;

    @Configuration
    @Import({CommentServiceImpl.class})
    static class NestedTestConfiguration {
    }

    @MockBean
    private CommentDao commentDao;

    @MockBean
    private BookDao bookDao;

    @Autowired
    private CommentServiceImpl commentService;

    @Test
    void calledCorrectlyMethodInsertComment() {
        Comment comment = new Comment("Надо повторить");
        commentService.insertComment("Надо повторить");
        verify(commentDao).save(comment);
    }

    @Test
    void calledCorrectlyMethodFindCommentById() {
        commentService.findCommentById(COMMENT_ID);
        verify(commentDao).findById(COMMENT_ID);
    }


    @Test
    void calledCorrectlyMethodUpdateCommentById() {
        commentService.updateCommentById(COMMENT_ID, "Новый комментарий");
        verify(commentDao).updateCommentById(COMMENT_ID, "Новый комментарий");
    }

    @Test
    void calledCorrectlyMethodDeleteCommentById() {
        commentService.deleteCommentById(COMMENT_ID);
        verify(commentDao).deleteById(COMMENT_ID);
    }
}