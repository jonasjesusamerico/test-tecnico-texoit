package com.texoit.testtecnico.src.service;


import com.texoit.testtecnico.src.model.Movie;
import com.texoit.testtecnico.src.model.Producer;
import com.texoit.testtecnico.src.repository.ProducerRepository;
import com.texoit.testtecnico.src.model.ResponseInterval;
import com.texoit.testtecnico.src.model.ResponseIntervalDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Comparator;
import java.util.List;

@Service
public class ProducerService  extends AbstractService<Producer> {

    @Autowired
    private final ProducerRepository producerRepository;

    public ProducerService(ProducerRepository producerRepository) {
        this.producerRepository = producerRepository;
    }

    public ResponseInterval findIntervals() {
        List<Producer> producers = producerRepository.findProducerWithMoreThanOneMovie();
        List<ResponseIntervalDto> collectMax = getCollectInterval(producers);

        return new ResponseInterval(
                collectMax.stream().filter(i -> i.getInterval() == getMinInterval(collectMax)).toList(),
                collectMax.stream().filter(i -> i.getInterval() == getMaxInterval(collectMax)).toList()
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
        return producers.stream()
                .filter(Producer::isMoreThenOneWin)
                .map(producer -> {
                    Movie maxMovie = getMaxMovie(producer);
                    Movie minMovie = getMinMovie(producer);
                    return getIntervalDto(producer, maxMovie.getYear() - minMovie.getYear(), minMovie, maxMovie);
                }).toList();
    }

    private static Movie getMinMovie(Producer producer) {
        return producer.getMovies().stream()
                .filter(Movie::getWinner)
                .min(Comparator.comparing(Movie::getYear)).get();
    }

    private static Movie getMaxMovie(Producer producer) {
        return producer.getMovies().stream()
                .filter(Movie::getWinner)
                .max(Comparator.comparing(Movie::getYear)).get();
    }

    private ResponseIntervalDto getIntervalDto(Producer producer, int intervalAux, Movie current, Movie last) {

        return ResponseIntervalDto.builder()
                .interval(intervalAux)
                .producer(producer.getName())
                .previousMovieWin(current.getYear())
                .nextMovieWin(last.getYear()).build();
    }
}
