package homeworke7.dao;

import homeworke7.domain.Comment;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface CommentDao extends JpaRepository<Comment, Long> {

    @Modifying
    @Query("update Comment c set c.comment = :comment where c.id = :id")
    void updateCommentById(@Param("id") long id, @Param("comment") String comment);

}
