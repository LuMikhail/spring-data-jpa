package homeworke7.dao;

import homeworke7.domain.Book;
import org.jetbrains.annotations.NotNull;
import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface BookDao extends JpaRepository<Book, Long> {

    @EntityGraph("book-entity-graph")
    @NotNull
    List<Book> findAll();

    @EntityGraph("book-entity-graph")
    List<Book> findByGenreId(Long genreId);

    @EntityGraph("book-entity-graph")
    List<Book> findByAuthorId(Long authorId);
}
