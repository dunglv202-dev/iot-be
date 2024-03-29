package edu.ptit.iot.controller;

import edu.ptit.iot.dto.Page;
import edu.ptit.iot.dto.SensorDataDTO;
import edu.ptit.iot.model.Pagination;
import edu.ptit.iot.model.specs.SensorDataSpecs;
import edu.ptit.iot.repository.SensorDataRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import static edu.ptit.iot.model.specs.SensorDataSpecBuilder.*;

@RestController
@RequestMapping("/api/sensor")
@RequiredArgsConstructor
@CrossOrigin
public class SensorDataController {
    private final SensorDataRepository sensorDataRepository;

    @GetMapping("/history")
    public Page<SensorDataDTO> getSensorHistory(Pagination pagination, SensorDataSpecs specs) {
        var sensorData = sensorDataRepository.findAll(
            attributeInRange("timestamp", specs.getFrom(), specs.getTo())
                .and(attributeInRange("temperature", specs.getMinTemp(), specs.getMaxTemp()))
                .and(attributeInRange("humidity", specs.getMinHud(), specs.getMaxHud()))
                .and(attributeInRange("lighting", specs.getMinLight(), specs.getMaxLight())),
            PageRequest.of(
                pagination.getPage(),
                pagination.getSize(),
                Sort.by(Sort.Direction.DESC, "timestamp")
            )
        );
        return Page.<SensorDataDTO>builder()
            .totalPage(sensorData.getTotalPages())
            .data(sensorData.stream().map(SensorDataDTO::fromModel).toList())
            .build();
    }
}
