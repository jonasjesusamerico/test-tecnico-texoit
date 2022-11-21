package com.texoit.testtecnico.src.model;

import lombok.Builder;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.ToString;

@Builder
@ToString
@Getter
@EqualsAndHashCode
public class ResponseIntervalDto {

    private String producer;
    private int interval;
    private int previousMovieWin;
    private int nextMovieWin;

}
