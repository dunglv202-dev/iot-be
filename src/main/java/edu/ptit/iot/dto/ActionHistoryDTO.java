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
    private LocalDateTime timestamp;
    private ActionType actionType;
    private String detail;

    public static ActionHistoryDTO fromModel(ActionHistory actionHistory) {
        return ActionHistoryDTO.builder()
            .timestamp(actionHistory.getTimestamp())
            .actionType(actionHistory.getActionType())
            .detail(actionHistory.getDetail())
            .build();
    }
}
