package edu.ptit.iot.model.specs;

import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;

@Getter
@Setter
public class SensorDataSpecs {
    private LocalDate from;
    private LocalDate to;
    private Float minTemp;
    private Float maxTemp;
    private Float minHud;
    private Float maxHud;
}
