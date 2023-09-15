package edu.ptit.iot.dto;

import edu.ptit.iot.entity.SensorData;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter
@Setter
public class SensorDataDTO {
    private Float temperature;
    private Float humidity;
    private Float lighting;
    private LocalDateTime timestamp;

    public SensorData toModel() {
        return SensorData.builder()
            .temperature(this.temperature)
            .humidity(this.humidity)
            .lighting(this.lighting)
            .timestamp(this.timestamp)
            .build();
    }
}
