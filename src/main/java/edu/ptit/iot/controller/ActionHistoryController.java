package edu.ptit.iot.controller;

import edu.ptit.iot.dto.ActionHistoryDTO;
import edu.ptit.iot.dto.Page;
import edu.ptit.iot.model.Pagination;
import edu.ptit.iot.repository.ActionHistoryRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/actions")
@RequiredArgsConstructor
@CrossOrigin
public class ActionHistoryController {
    private final ActionHistoryRepository actionHistoryRepository;

    @GetMapping("/history")
    public Page<ActionHistoryDTO> getListActionHistory(Pagination pagination) {
        var actionHistories = actionHistoryRepository.findAll(PageRequest.of(
            pagination.getPage(),
            pagination.getSize(),
            Sort.by(Sort.Direction.DESC, "timestamp")
        ));
        return Page.<ActionHistoryDTO>builder()
            .totalPage(actionHistories.getTotalPages())
            .data(actionHistories.stream().map(ActionHistoryDTO::fromModel).toList())
            .build();
    }
}
