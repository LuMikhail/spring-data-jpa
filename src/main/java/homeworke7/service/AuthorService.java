package homeworke7.service;

import homeworke7.domain.Author;

import java.util.List;

public interface AuthorService {

    void insertAuthor(String name);

    List<Author> findAllAuthors();
}
