package homeworke7.service;

import homeworke7.dao.AuthorDao;
import homeworke7.dao.BookDao;
import homeworke7.dao.CommentDao;
import homeworke7.dao.GenreDao;
import homeworke7.domain.Author;
import homeworke7.domain.Book;
import homeworke7.domain.Comment;
import homeworke7.domain.Genre;
import org.springframework.transaction.annotation.Transactional;;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

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
        Optional<Book> bookOptional = bookDao.findById(bookId);
        if (bookOptional.isPresent()) {
            Book book = bookOptional.get();
            Optional<Comment> commentOptional = commentDao.findById(commentId);
            if (commentOptional.isPresent()) {
                Comment comment = commentOptional.get();
                List<Comment> commentList = book.getComments();
                commentList.add(comment);
                book.setComments(commentList);
                return bookDao.save(book);
            }
        }
        return null;
    }

    @Override
    @Transactional(readOnly = true)
    public Optional<Book> findBookById(long id) {
        return bookDao.findById(id);
    }

    @Override
    @Transactional(readOnly = true)
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
    @Transactional
    public void updateBookById(long bookId, String title, long authorId, long genreId, int amount) {
        Optional<Book> book = bookDao.findById(bookId);
        Optional<Author> author = authorDao.findById(authorId);
        Optional<Genre> genre = genreDao.findById(genreId);
        book.ifPresent(updateBook -> {
            updateBook.setTitle(title);
            updateBook.setAuthor(author.get());
            updateBook.setGenre(genre.get());
            updateBook.setAmount(amount);
            bookDao.save(updateBook);
        });
    }

    @Override
    @Transactional
    public void deleteBooksById(long id) {
        bookDao.deleteById(id);
    }
}
