package homeworke7.dao;

import homeworke7.domain.Genre;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatCode;

@DataJpaTest
class GenreDaoTest {

    private static final long FIRST_GENRE_ID = 1;
    private static final String FIRST_GENRE_FIRST_NAME = "Программирование";
    private static final int GENRE_COUNT = 2;

    @Autowired
    private GenreDao genreDao;

    @Test
    void shouldAddGenre() {
        genreDao.save(new Genre("Проза"));
        assertThat(genreDao.findAll()).hasSize(GENRE_COUNT + 1);

    }

    @Test
    void shouldReturnGenreById() {
        Optional<Genre> genre = genreDao.findById(FIRST_GENRE_ID);
        assertThat(genre).isNotEmpty().get()
                .hasFieldOrPropertyWithValue("genre", FIRST_GENRE_FIRST_NAME);
    }

    @Test
    void shouldFindAllGenre() {
        List<Genre> genres = genreDao.findAll();
        assertThat(genres).hasSize(GENRE_COUNT);
    }

    @Test
    void shouldCorrectDeleteAuthorById() {
        assertThatCode(() -> genreDao.findById(FIRST_GENRE_ID))
                .doesNotThrowAnyException();
        genreDao.deleteById(FIRST_GENRE_ID);
        assertThat(genreDao.findAll()).hasSize(GENRE_COUNT - 1);
    }
}