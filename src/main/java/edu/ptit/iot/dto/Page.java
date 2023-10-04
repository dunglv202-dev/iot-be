package edu.ptit.iot.dto;

import lombok.Builder;
import lombok.Getter;

import java.util.List;

@Builder
@Getter
public class Page<T> {
    private Integer totalPage;
    private List<T> data;
}
