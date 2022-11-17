package com.texoit.testtecnico.src.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.ToString;

import java.util.List;

@Getter
@ToString
@AllArgsConstructor
public class ResponseInterval {
    private List<ResponseIntervalDto> min;
    private List<ResponseIntervalDto> max;
}
