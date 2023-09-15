package edu.ptit.iot.repository;

import edu.ptit.iot.entity.ActionHistory;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ActionHistoryRepository extends JpaRepository<ActionHistory, Long> {
}
