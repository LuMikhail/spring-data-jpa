package homeworke7.service;

import homeworke7.dao.AuthorDao;
import homeworke7.dao.BookDao;
import homeworke7.dao.CommentDao;
import homeworke7.dao.GenreDao;
import homeworke7.domain.Author;
import homeworke7.domain.Book;
import homeworke7.domain.Comment;
import homeworke7.domain.Genre;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@RequiredArgsConstructor
@Service
public class BookServiceImpl implements BookService {

    private final BookDao bookDao;
    private final AuthorDao authorDao;
    private final GenreDao genreDao;
    private final CommentDao commentDao;

    @Override
    public void insertBook(String title, long idAuthor, long idGenre, int amount) {
        bookDao.save(new Book(title, new Author(idAuthor), new Genre(idGenre), amount));
    }

    @Override
    @Transactional
    public Book assignCommentToBook(Long bookId, Long commentId) {
        Comment comment = commentDao.findById(commentId)
                .orElseThrow(() -> new IllegalArgumentException("Comment not found with id: " + commentId));
        Book book = bookDao.findById(bookId)
                .orElseThrow(() -> new IllegalArgumentException("Book not found with id: " + bookId));
        comment.setBook(book);
        book.getComments().add(comment);
        bookDao.save(book);
        return book;
    }

    @Override
    @Transactional(readOnly = true)
    public Optional<Book> findBookById(long id) {
        return bookDao.findById(id);
    }

    @Override
    public List<Book> findAllBooks() {
        return bookDao.findAll();
    }

    @Override
    public List<Book> findBooksContainThisGenre(Long genreId) {
        return bookDao.findByGenreId(genreId);
    }

    @Override
    public List<Book> findBooksContainThisAuthor(Long authorId) {
        return bookDao.findByAuthorId(authorId);
    }

    @Override
    public void updateBookById(long bookId, String title, long authorId, long genreId, int amount) {
        bookDao.findById(bookId).ifPresent(book -> {
            authorDao.findById(authorId).ifPresent(book::setAuthor);
            genreDao.findById(genreId).ifPresent(book::setGenre);
            book.setTitle(title);
            book.setAmount(amount);
            bookDao.save(book);
        });
    }

    @Override
    public void deleteBooksById(long id) {
        bookDao.deleteById(id);
    }
}
