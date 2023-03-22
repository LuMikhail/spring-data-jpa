package homeworke7.dao;

import homeworke7.domain.Book;
import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface BookDao extends JpaRepository<Book, Long> {

    @EntityGraph("Book.book-all-entity-graph")
    List<Book> findAll();

    @EntityGraph("Book.book-all-entity-graph")
    List<Book> findByGenreId(Long genreId);

    @EntityGraph("Book.book-all-entity-graph")
    List<Book> findByAuthorId(Long authorId);
}
