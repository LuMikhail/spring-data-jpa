package homeworke7.dao;

import homeworke7.domain.Comment;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CommentDao extends JpaRepository<Comment, Long> {

}
