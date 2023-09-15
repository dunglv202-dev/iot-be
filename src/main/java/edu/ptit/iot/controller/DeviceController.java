package edu.ptit.iot.controller;

import edu.ptit.iot.constant.ActionType;
import edu.ptit.iot.entity.ActionHistory;
import edu.ptit.iot.model.MqttGateway;
import edu.ptit.iot.repository.ActionHistoryRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;

@RestController
@RequestMapping("/api/devices/{deviceId}")
@RequiredArgsConstructor
@CrossOrigin
public class DeviceController {
    private final MqttGateway mqttGateway;
    private final ActionHistoryRepository actionHistoryRepository;

    @PostMapping("/state")
    public void changeLedState(@PathVariable String deviceId, @RequestBody String action) {
        if (!isExistedDevice(deviceId)) {
            throw new RuntimeException("Invalid device id: " + deviceId);
        }
        ActionType actionType = getActionType(action);
        mqttGateway.sendToMqtt(action, "esp8266/" + deviceId);
        actionHistoryRepository.save(
            ActionHistory.builder()
                .actionType(actionType)
                .timestamp(LocalDateTime.now())
                .details(actionType + " for " + deviceId)
                .build()
        );
    }

    private boolean isExistedDevice(String deviceId) {
        return "led".equals(deviceId) || "fan".equals(deviceId);
    }

    private ActionType getActionType(String action) {
        switch (action) {
            case "on" -> {
                return ActionType.TURN_ON;
            }
            case "off" -> {
                return ActionType.TURN_OFF;
            }
            default -> {
                throw new RuntimeException("Invalid action: " + action);
            }
        }
    }
}
