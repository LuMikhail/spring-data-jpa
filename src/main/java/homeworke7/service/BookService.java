package homeworke7.service;

import homeworke7.domain.Book;

import java.util.List;
import java.util.Optional;

public interface BookService {

    void insertBook(String title, long idAuthor, long idGenre, int amount);

    Book assignCommentToBook(Long bookId, Long commentId);

    Optional<Book> findBookById(long id);

    List<Book> findAllBooks();

    List<Book> findBooksContainThisGenre(Long genre);

    List<Book> findBooksContainThisAuthor(Long author);

    void updateBookById(long bookId, String title, long authorId, long genreId, int amount);

    void deleteBooksById(long id);

}
