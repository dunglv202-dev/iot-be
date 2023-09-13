package edu.ptit.iot.controller;

import edu.ptit.iot.service.MqttGateway;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/{deviceId}")
@RequiredArgsConstructor
public class DeviceController {
    private final MqttGateway mqttGateway;

    @PostMapping("/state")
    public void changeLedState(@PathVariable String deviceId, @RequestBody String action) {
        mqttGateway.sendToMqtt(action, "esp8266/" + deviceId);
    }
}
