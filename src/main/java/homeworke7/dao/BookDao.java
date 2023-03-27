package homeworke7.dao;

import homeworke7.domain.Book;
import org.jetbrains.annotations.NotNull;
import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface BookDao extends JpaRepository<Book, Long> {

    @EntityGraph(attributePaths = {"author", "genre", "comments"})
    @NotNull
    List<Book> findAll();

    @EntityGraph(attributePaths = {"author", "genre"})
    Optional<Book> findById(long id);

    @EntityGraph(attributePaths = {"author", "genre"})
    List<Book> findByGenreId(Long genreId);

    @EntityGraph(attributePaths = {"author", "genre"})
    List<Book> findByAuthorId(Long authorId);
}
