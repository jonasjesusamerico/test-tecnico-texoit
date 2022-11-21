package com.texoit.testtecnico.src.model;

import lombok.*;

import javax.persistence.*;
import java.util.Collection;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "PRODUCER")
@Getter
@Builder
@ToString
@NoArgsConstructor
@AllArgsConstructor
public class Producer implements BaseEntity{

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ID")
    private Long id;

    @Column(name = "NAME")
    private String name;

    @OneToMany(fetch = FetchType.EAGER, cascade = CascadeType.ALL, orphanRemoval = true, mappedBy = "producer")
    private final Set<Movie> movies = new HashSet<>(0);

    public void addMovies(Collection<Movie> movies) {
        this.movies.clear();
        this.movies.addAll(movies);
        this.movies.forEach(t -> t.setProducers(this));
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Producer producer = (Producer) o;

        return name.equals(producer.name);
    }

    public boolean isMoreThenOneWin() {
        return movies.stream().
                filter(Movie::getWinner).count() > 1;
    }

    @Override
    public int hashCode() {
        return name.hashCode();
    }
}
