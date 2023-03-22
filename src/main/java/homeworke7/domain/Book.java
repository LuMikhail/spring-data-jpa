package homeworke7.domain;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.BatchSize;
import org.hibernate.annotations.Fetch;
import org.hibernate.annotations.FetchMode;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "book")
@NamedEntityGraph(name = "Book.book-all-entity-graph", includeAllAttributes = true)
public class Book {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @Column(name = "title", nullable = false)
    private String title;

    @ManyToOne
    @JoinColumn(name = "author_id", referencedColumnName = "id")
    private Author author;

    @ManyToOne
    @JoinColumn(name = "genre_id", referencedColumnName = "id")
    private Genre genre;

    @Column(name = "amount", nullable = false)
    private int amount;

    @OneToMany(fetch = FetchType.LAZY)
    @BatchSize(size = 20)
    @JoinColumn(name = "book_id", referencedColumnName = "id")
    private List<Comment> comments;

    public Book(String title, Author author, Genre genre, int amount) {
        this.title = title;
        this.author = author;
        this.genre = genre;
        this.amount = amount;
    }
}
