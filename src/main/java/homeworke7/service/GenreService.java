package homeworke7.service;

import homeworke7.domain.Genre;

import java.util.List;

public interface GenreService {

    void insertGenre(String genre);

    List<Genre> findAllGenres();
}
