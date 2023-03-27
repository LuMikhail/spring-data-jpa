package homeworke7.service;

import homeworke7.dao.AuthorDao;
import homeworke7.domain.Author;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@RequiredArgsConstructor
@Service
public class AuthorServiceImpl implements AuthorService {

    private final AuthorDao authorDao;

    @Override
    public void insertAuthor(String name) {
        authorDao.save(new Author(name));
    }

    @Override
    public List<Author> findAllAuthors() {
        return authorDao.findAll();
    }
}
