package homeworke7.domain;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.BatchSize;

import java.util.List;
import java.util.Objects;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "book")
@NamedEntityGraph(name = "book-entity-graph", attributeNodes = {@NamedAttributeNode("author"),
        @NamedAttributeNode("genre")})
public class Book {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @Override
    public int hashCode() {
        return Objects.hash(id, title, author, genre, amount, comments);
    }

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

    @OneToMany(mappedBy = "book", fetch = FetchType.LAZY)
    @BatchSize(size = 20)
    private List<Comment> comments;

    public Book(String title, Author author, Genre genre, int amount) {
        this.title = title;
        this.author = author;
        this.genre = genre;
        this.amount = amount;
    }

    @Override
    public String toString() {
        return String.format("Book(id=%s ,title=%s ,author=%s ,genre=%s ,amount=%s)",
                this.id,
                this.title,
                this.author,
                this.genre,
                this.amount);
    }
}

