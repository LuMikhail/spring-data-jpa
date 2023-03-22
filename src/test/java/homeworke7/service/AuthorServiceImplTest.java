package homeworke7.service;

import homeworke7.dao.AuthorDao;
import homeworke7.domain.Author;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;

import static org.mockito.Mockito.verify;

@SpringBootTest
class AuthorServiceImplTest {

    @Configuration
    @Import(AuthorServiceImpl.class)
    static class NestedTestConfiguration {
    }

    @MockBean
    private AuthorDao authorDao;

    @Autowired
    AuthorService authorService;

    @Test
    void calledCorrectlyInsertAuthor() {
        Author author = new Author("Джош Блох");
        authorService.insertAuthor("Джош Блох");
        verify(authorDao).save(author);
    }

    @Test
    void calledCorrectlyFindAllAuthors() {
        authorService.findAllAuthors();
        verify(authorDao).findAll();
    }
}