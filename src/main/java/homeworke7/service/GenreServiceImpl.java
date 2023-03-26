package homeworke7.service;

import homeworke7.dao.GenreDao;
import homeworke7.domain.Genre;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@RequiredArgsConstructor
@Service
public class GenreServiceImpl implements GenreService {

    private final GenreDao genreDao;

    @Override
    @Transactional
    public void insertGenre(String genre) {
        genreDao.save(new Genre(genre));
    }

    @Override
    public List<Genre> findAllGenres() {
        return genreDao.findAll();
    }
}

