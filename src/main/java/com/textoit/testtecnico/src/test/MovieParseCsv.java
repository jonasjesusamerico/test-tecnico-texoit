package com.textoit.testtecnico.src.test;

import com.opencsv.bean.CsvBindByPosition;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Setter
@Getter
@ToString
public class MovieParseCsv {

    @CsvBindByPosition(position = 0)
    private Integer year;

    @CsvBindByPosition(position = 1)
    private String title;

    @CsvBindByPosition(position = 2)
    private String studios;

    @CsvBindByPosition(position = 3)
    private String producers;

    @CsvBindByPosition(position = 4, required = false)
    private Boolean winner;


}
