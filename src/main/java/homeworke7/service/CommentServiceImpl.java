package homeworke7.service;

import homeworke7.dao.BookDao;
import homeworke7.dao.CommentDao;
import homeworke7.domain.Book;
import homeworke7.domain.Comment;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@RequiredArgsConstructor
@Service
public class CommentServiceImpl implements CommentService {

    private final CommentDao commentDao;
    private final BookDao bookDao;

    @Override
    @Transactional
    public void insertComment(String comment) {
        commentDao.save(new Comment(comment));
    }

    @Override
    @Transactional(readOnly = true)
    public Optional<Comment> findCommentById(long id) {
        return commentDao.findById(id);
    }

    @Override
    @Transactional(readOnly = true)
    public List<Comment> findCommentsByBookId(long id) {
        Optional<Book> book = bookDao.findById(id);
        if (book.isPresent()) {
            Book findBook = book.get();
            List<Comment> comments = findBook.getComments();
            comments.forEach(System.out::println);
            return comments;
        }
        return null;
    }

    @Override
    public void updateCommentById(long id, String comment) {
        commentDao.findById(id).ifPresent(updateComment -> {
            updateComment.setComment(comment);
            commentDao.save(updateComment);
        });
    }

    @Override
    public void deleteCommentById(long id) {
        commentDao.deleteById(id);
    }
}
