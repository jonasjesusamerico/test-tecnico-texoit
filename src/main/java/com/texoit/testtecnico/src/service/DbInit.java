package com.texoit.testtecnico.src.service;

import com.opencsv.bean.CsvToBeanBuilder;
import com.texoit.testtecnico.src.model.Movie;
import com.texoit.testtecnico.src.model.MovieParseCsv;
import com.texoit.testtecnico.src.model.Producer;
import com.texoit.testtecnico.src.repository.ProducerRepository;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.Stream;

@Service
public class DbInit {

    @Value("${filename}")
    private String fileName;
    private final ProducerRepository producerRepository;

    public DbInit(ProducerRepository producerRepository) {
        this.producerRepository = producerRepository;
    }

    private static Set<Producer> createProducers(List<MovieParseCsv> beans) {
        return beans.stream()
                .sorted(Comparator.comparing(MovieParseCsv::getProducers))
                .flatMap(DbInit::adjustProducerName)
                .map(t -> Producer.builder().name(t).build())
                .peek(producer -> producer.addMovies(getMovies(beans, producer)))
                .collect(Collectors.toSet());
    }

    @PostConstruct
    public List<Producer> init() {
        try {
            Set<Producer> producers = dataExtractor(fileName);
            producerRepository.deleteAll(producers);
            return producerRepository.saveAll(producers);
        } catch (FileNotFoundException e) {
            throw new RuntimeException("Unable to read the file", e);
        }
    }

    public Set<Producer> dataExtractor(final String fileName) throws FileNotFoundException {
        List<MovieParseCsv> beans = new CsvToBeanBuilder(new FileReader(fileName))
                .withSkipLines(1)
                .withSeparator(';')
                .withType(MovieParseCsv.class)
                .build()
                .parse();

        return createProducers(beans);
    }

    private static Stream<String> adjustProducerName(MovieParseCsv csvLine) {
        return Arrays.stream(csvLine.getProducers().split(",| and "))
                .filter(Objects::nonNull)
                .filter(name -> name.length() != 0)
                .map(String::trim)
                .distinct();
    }

    private static List<Movie> getMovies(List<MovieParseCsv> beans, Producer producer) {
        return beans.stream()
                .filter(csvLine -> csvLine.getProducers().contains(producer.getName()))
                .map(Movie::convertFrom)
                .collect(Collectors.toList());
    }

}
