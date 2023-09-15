package edu.ptit.iot.model;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import edu.ptit.iot.dto.SensorDataDTO;
import lombok.RequiredArgsConstructor;
import org.springframework.integration.mqtt.support.MqttHeaders;
import org.springframework.messaging.Message;
import org.springframework.messaging.MessageHandler;
import org.springframework.messaging.MessagingException;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class MqttMessageHandler implements MessageHandler {
    private final SimpMessagingTemplate messagingTemplate;
    private final ObjectMapper objectMapper;

    @Override
    public void handleMessage(Message<?> message) throws MessagingException {
        String topic = (String) message.getHeaders().get(MqttHeaders.RECEIVED_TOPIC);
        if ("esp8266/dht".equals(topic)) {
            try {
                SensorDataDTO sensorDataDTO = objectMapper.readValue(
                    message.getPayload().toString(),
                    SensorDataDTO.class
                );
                messagingTemplate.convertAndSend("/topic/dht", sensorDataDTO);
            } catch (JsonProcessingException e) {
                throw new RuntimeException("Unable to convert MQTT message", e);
            }
        }
    }
}
