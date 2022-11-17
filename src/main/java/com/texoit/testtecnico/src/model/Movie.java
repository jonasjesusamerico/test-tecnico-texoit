package com.texoit.testtecnico.src.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.*;

import javax.persistence.*;


@Entity
@Table(name = "MOVIE")
@Getter
@Builder
@ToString
@NoArgsConstructor
@AllArgsConstructor
public class Movie {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ID")
    private Long id;

    @Column(name = "YEAR")
    private Integer year;

    @Column(name = "TITLE")
    private String title;

    @Column(name = "STUDIOS")
    private String studios;

    @ManyToOne
    @JoinColumn(name = "I_PRODUCER", nullable = false)
    @JsonIgnore
    private Producer producer;

    @Column(name = "FL_WINNER")
    private Boolean winner;

    public Boolean getWinner() {
        return Boolean.TRUE.equals(winner);
    }

    public static Movie convertFrom(MovieParseCsv csv) {
        return Movie.builder()
                .year(csv.getYear())
                .title(csv.getTitle())
                .studios(csv.getStudios())
                .winner(csv.getWinner())
                .build();
    }

    public void setProducers(Producer producer) {
        this.producer = producer;
    }
}
