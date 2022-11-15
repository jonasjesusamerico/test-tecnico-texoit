package com.textoit.testtecnico.src.model;

import com.textoit.testtecnico.src.test.MovieParseCsv;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import javax.persistence.*;


@Entity
@Table(name = "MOVIE")
@Getter
@Builder
@ToString
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

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name="I_PRODUCER", nullable=false)
    private Producer producer;

    @Column(name = "FL_WINNER")
    private Boolean winner;


    public static Movie convertFrom(MovieParseCsv csv) {
        Producer producer = Producer.builder().name(csv.getProducers()).build();
        return Movie.builder()
                .year(csv.getYear())
                .title(csv.getTitle())
                .studios(csv.getStudios())
                .producer(producer)
                .winner(csv.getWinner())
                .build();
    }
}
