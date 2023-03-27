package homeworke7.dao;

import homeworke7.domain.Genre;
import org.springframework.data.jpa.repository.JpaRepository;

public interface GenreDao extends JpaRepository<Genre, Long> {

}
