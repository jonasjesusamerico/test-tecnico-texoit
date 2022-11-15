package com.textoit.testtecnico.src.service;

import com.opencsv.bean.CsvToBeanBuilder;
import com.textoit.testtecnico.src.model.Movie;
import com.textoit.testtecnico.src.repository.MovieRepository;
import com.textoit.testtecnico.src.test.MovieParseCsv;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class DbInit {

    private final MovieRepository repository;

    public DbInit(MovieRepository repository) {
        this.repository = repository;
    }

    @PostConstruct
    public void init() {
        try {
            List<MovieParseCsv> beans = new CsvToBeanBuilder(new FileReader("./movielist.csv"))
                    .withSkipLines(1)
                    .withSeparator(';')
                    .withType(MovieParseCsv.class)
                    .build()
                    .parse();
            List<Movie> collect = beans.stream().map(Movie::convertFrom).collect(Collectors.toList());
            repository.saveAll(collect);

            System.out.println(repository.count());
            System.out.println("==============");
        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        }

    }

}
