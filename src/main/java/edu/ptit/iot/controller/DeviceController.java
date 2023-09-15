package edu.ptit.iot.controller;

import edu.ptit.iot.model.MqttGateway;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/devices/{deviceId}")
@RequiredArgsConstructor
@CrossOrigin
public class DeviceController {
    private final MqttGateway mqttGateway;

    @PostMapping("/state")
    public void changeLedState(@PathVariable String deviceId, @RequestBody String action) {
        if (!isExistedDevice(deviceId)) {
            throw new RuntimeException("Invalid device id " + deviceId);
        }
        mqttGateway.sendToMqtt(action, "esp8266/" + deviceId);
    }

    private boolean isExistedDevice(String deviceId) {
        return "led".equals(deviceId) || "fan".equals(deviceId);
    }
}
