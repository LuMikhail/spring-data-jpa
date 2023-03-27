package homeworke7.dao;

import homeworke7.domain.Author;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AuthorDao extends JpaRepository<Author, Long> {

}
