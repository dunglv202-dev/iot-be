package edu.ptit.iot.controller;

import edu.ptit.iot.dto.SensorDataDTO;
import edu.ptit.iot.repository.SensorDataRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/sensor")
@RequiredArgsConstructor
@CrossOrigin
public class SensorDataController {
    private final SensorDataRepository sensorDataRepository;

    @GetMapping("/history")
    public List<SensorDataDTO> getSensorHistory() {
        return sensorDataRepository.findAll(PageRequest.of(
            0,
            20,
            Sort.by(Sort.Direction.DESC, "timestamp")
        )).stream().map(SensorDataDTO::fromModel).toList();
    }
}
