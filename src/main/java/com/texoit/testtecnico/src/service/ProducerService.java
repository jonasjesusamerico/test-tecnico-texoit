package com.texoit.testtecnico.src.service;


import com.texoit.testtecnico.src.model.Movie;
import com.texoit.testtecnico.src.model.Producer;
import com.texoit.testtecnico.src.model.ResponseInterval;
import com.texoit.testtecnico.src.model.ResponseIntervalDto;
import com.texoit.testtecnico.src.repository.ProducerRepository;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
public class ProducerService extends AbstractService<Producer> {

    private final ProducerRepository producerRepository;

    public ProducerService(ProducerRepository producerRepository) {
        this.producerRepository = producerRepository;
    }

    public ResponseInterval findIntervals() {
        List<Producer> producers = producerRepository.findProducerWithMoreThanOneMovie();
        List<ResponseIntervalDto> collectInterval = getCollectInterval(producers);

        return new ResponseInterval(
                collectInterval.stream().filter(i -> i.getInterval() == getMinInterval(collectInterval)).toList(),
                collectInterval.stream().filter(i -> i.getInterval() == getMaxInterval(collectInterval)).toList()
        );
    }

    private static int getMinInterval(List<ResponseIntervalDto> collectMax) {
        return collectMax.stream()
                .mapToInt(ResponseIntervalDto::getInterval)
                .min()
                .orElse(0);
    }

    private static int getMaxInterval(List<ResponseIntervalDto> collectMax) {
        return collectMax.stream()
                .mapToInt(ResponseIntervalDto::getInterval)
                .max()
                .orElse(0);
    }

    private List<ResponseIntervalDto> getCollectInterval(List<Producer> producers) {
        List<ResponseIntervalDto> list = new ArrayList<>(0);
        producers.stream()
                .filter(Producer::isMoreThenOneWin)
                .forEach(producer -> {
                    List<Movie> collect = producer.getMovies().stream()
                            .filter(Movie::getWinner)
                            .sorted(Comparator.comparing(Movie::getYear)).toList();

                    for (Movie movie : collect) {
                        Movie next = getNextMovie(collect, movie);
                        if (Objects.nonNull(next.getId())) {
                            ResponseIntervalDto intervalDto = getIntervalDto(producer, next.getYear() - movie.getYear(), movie, next);
                            list.add(intervalDto);
                        }
                    }
                });
        return list;
    }

    public Movie getNextMovie(List<Movie> collect, Movie movie) {
        int idx = collect.indexOf(movie);
        if (idx < 0 || idx + 1 == collect.size()) return Movie.empty();
        return collect.get(idx + 1);
    }

    private ResponseIntervalDto getIntervalDto(Producer producer, int intervalAux, Movie current, Movie last) {

        return ResponseIntervalDto.builder()
                .interval(intervalAux)
                .producer(producer.getName())
                .previousMovieWin(current.getYear())
                .nextMovieWin(last.getYear()).build();
    }
}
