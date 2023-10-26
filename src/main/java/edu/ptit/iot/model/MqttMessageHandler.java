package edu.ptit.iot.model;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import edu.ptit.iot.dto.SensorDataDTO;
import edu.ptit.iot.repository.SensorDataRepository;
import io.micrometer.common.util.StringUtils;
import lombok.RequiredArgsConstructor;
import org.springframework.integration.mqtt.support.MqttHeaders;
import org.springframework.messaging.Message;
import org.springframework.messaging.MessageHandler;
import org.springframework.messaging.MessagingException;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;
import java.util.Random;

@Component
@RequiredArgsConstructor
public class MqttMessageHandler implements MessageHandler {
    private final SimpMessagingTemplate messagingTemplate;
    private final ObjectMapper objectMapper;
    private final SensorDataRepository sensorDataRepository;

    @Override
    public void handleMessage(Message<?> message) throws MessagingException {
        String topic = (String) message.getHeaders().get(MqttHeaders.RECEIVED_TOPIC);
        if ("esp8266/dht".equals(topic)) {
            try {
                SensorDataDTO sensorDataDTO = objectMapper.readValue(
                    message.getPayload().toString(),
                    SensorDataDTO.class
                );
//                sensorDataDTO.setLighting(new Random().nextFloat() * 10 + 120);
                sensorDataDTO.setTimestamp(LocalDateTime.now());
                messagingTemplate.convertAndSend("/topic/dht", sensorDataDTO);
                sensorDataRepository.save(sensorDataDTO.toModel());
            } catch (JsonProcessingException e) {
                throw new RuntimeException("Unable to convert MQTT message", e);
            }
        } else if (StringUtils.isNotBlank(topic) && topic.endsWith("state")) {
            messagingTemplate.convertAndSend("/topic/" + topic, message.getPayload().toString());
        }
    }
}
