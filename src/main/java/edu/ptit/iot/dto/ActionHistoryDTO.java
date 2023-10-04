package edu.ptit.iot.dto;

import edu.ptit.iot.constant.ActionType;
import edu.ptit.iot.entity.ActionHistory;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter
@Setter
@Builder
public class ActionHistoryDTO {
    private String deviceId;
    private ActionType actionType;
    private LocalDateTime timestamp;

    public static ActionHistoryDTO fromModel(ActionHistory actionHistory) {
        return ActionHistoryDTO.builder()
            .deviceId(actionHistory.getDeviceId())
            .actionType(actionHistory.getActionType())
            .timestamp(actionHistory.getTimestamp())
            .build();
    }
}
