package homeworke7.service;

import homeworke7.dao.AuthorDao;
import homeworke7.dao.BookDao;
import homeworke7.dao.CommentDao;
import homeworke7.dao.GenreDao;
import homeworke7.domain.Author;
import homeworke7.domain.Book;
import homeworke7.domain.Genre;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;

import static org.mockito.Mockito.verify;

@SpringBootTest
class BookServiceImplTest {

    @Configuration
    @Import({BookServiceImpl.class})
    static class NestedTestConfiguration {
    }

    @MockBean
    private CommentDao commentDao;

    @MockBean
    private BookDao bookDao;

    @MockBean
    private GenreDao genreDao;

    @MockBean
    private AuthorDao authorDao;

    @Autowired
    private BookServiceImpl bookService;

    @Test
    void calledCorrectlyMethodInsertBook() {
        Book book = new Book("Скриба", new Author(3L), new Genre(2L), 3);
        bookService.insertBook("Скриба", 3, 2, 3);
        verify(bookDao).save(book);
    }

    @Test
    void calledCorrectlyMethodFindBookById() {
        bookService.findBookById(1L);
        verify(bookDao).findById(1L);
    }

    @Test
    void calledCorrectlyMethodFindAllBooks() {
        bookService.findAllBooks();
        verify(bookDao).findAll();
    }

    @Test
    void calledCorrectlyMethodFindBooksContainThisGenre() {
        Genre genre = new Genre(1);
        bookService.findBooksContainThisGenre(1L);
        verify((bookDao)).findByGenreId(genre.getId());
    }

    @Test
    void calledCorrectlyMethodFindBooksContainThisAuthor() {
        Author author = new Author(1);
        bookService.findBooksContainThisAuthor(1L);
        verify(bookDao).findByAuthorId(author.getId());
    }


    @Test
    void calledCorrectlyMethodDeleteBooksById() {
        bookService.deleteBooksById(1L);
        verify(bookDao).deleteById(1L);
    }
}