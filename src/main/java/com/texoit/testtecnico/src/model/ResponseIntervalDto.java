package com.texoit.testtecnico.src.model;

import lombok.Builder;
import lombok.Getter;
import lombok.ToString;

@Builder
@ToString
@Getter
public class ResponseIntervalDto {

    private String producer;
    private int interval;
    private int previousMovieWin;
    private int nextMovieWin;

}
