package edu.ptit.iot.dto;

import edu.ptit.iot.entity.SensorData;
import lombok.*;

import java.time.LocalDateTime;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class SensorDataDTO {
    private Float temperature;
    private Float humidity;
    private Float lighting;
    private Float dustLevel;
    private LocalDateTime timestamp;

    public SensorData toModel() {
        return SensorData.builder()
            .temperature(this.temperature)
            .humidity(this.humidity)
            .lighting(this.lighting)
            .timestamp(this.timestamp)
            .dustLevel(this.dustLevel)
            .build();
    }

    public static SensorDataDTO fromModel(SensorData sensorData) {
        return SensorDataDTO.builder()
            .temperature(sensorData.getTemperature())
            .humidity(sensorData.getHumidity())
            .lighting(sensorData.getLighting())
            .timestamp(sensorData.getTimestamp())
            .dustLevel(sensorData.getDustLevel())
            .build();
    }
}
