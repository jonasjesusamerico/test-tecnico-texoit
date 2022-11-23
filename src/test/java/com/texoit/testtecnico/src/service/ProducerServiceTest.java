package com.texoit.testtecnico.src.service;

import com.texoit.testtecnico.src.model.ResponseInterval;
import com.texoit.testtecnico.src.model.ResponseIntervalDto;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.IfProfileValue;
import org.springframework.test.context.ActiveProfiles;

import java.util.Arrays;
import java.util.Collections;

import static org.junit.jupiter.api.Assertions.assertEquals;

@SpringBootTest
@ActiveProfiles("test")
@IfProfileValue(name = "run.integration.tests", value = "true")
class ProducerServiceTest {

    @Autowired
    private ProducerService producerService;

    @Test
    public void moreThanOneMaxWin() {
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

        ResponseIntervalDto maxMovieTwo = ResponseIntervalDto.builder()
                .producer("Matthew Vaughn")
                .interval(13)
                .previousMovieWin(2015)
                .nextMovieWin(2028)
                .build();


        ResponseInterval responseInterval = new ResponseInterval(
                Collections.singletonList(minMovie),
                Arrays.asList(maxMovie, maxMovieTwo)

        );

        ResponseInterval savedIntervals = producerService.findIntervals();
        assertEquals(savedIntervals, responseInterval);


    }

}