package com.texoit.testtecnico.src.service;

import com.texoit.testtecnico.src.model.ResponseInterval;
import com.texoit.testtecnico.src.model.ResponseIntervalDto;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.Collections;

import static org.junit.jupiter.api.Assertions.assertEquals;

@SpringBootTest
class ProducerServiceTest {

    @Autowired
    private ProducerService producerService;

    @Test
    public void mustTestMaximumMinimumMovieDefault() {
        ResponseIntervalDto minMovie = ResponseIntervalDto.builder()
                .producer("Joel Silver")
                .interval(1)
                .previousMovieWin(1990)
                .nextMovieWin(1991)
                .build();
        ResponseIntervalDto maxMovie = ResponseIntervalDto.builder()
                .producer("Matthew Vaughn")
                .interval(13)
                .previousMovieWin(2002)
                .nextMovieWin(2015)
                .build();


        ResponseInterval responseInterval = new ResponseInterval(
                Collections.singletonList(minMovie),
                Collections.singletonList(maxMovie)

        );

        ResponseInterval savedIntervals = producerService.findIntervals();
        assertEquals(savedIntervals, responseInterval);


    }

}