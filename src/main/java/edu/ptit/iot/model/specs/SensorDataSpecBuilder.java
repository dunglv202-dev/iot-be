package edu.ptit.iot.model.specs;

import edu.ptit.iot.entity.SensorData;
import jakarta.persistence.criteria.Predicate;
import org.springframework.data.jpa.domain.Specification;

import java.util.ArrayList;
import java.util.List;

public class SensorDataSpecBuilder {
    public static <T extends Comparable<T>> Specification<SensorData> attributeInRange(String attribute, T from, T to) {
        return (root, query, criteriaBuilder) -> {
            List<Predicate> predicates = new ArrayList<>();
            if (from != null) {
                predicates.add(criteriaBuilder.greaterThanOrEqualTo(root.get(attribute), from));
            }
            if (to != null) {
                predicates.add(criteriaBuilder.lessThanOrEqualTo(root.get(attribute), to));
            }
            return criteriaBuilder.and(predicates.toArray(new Predicate[0]));
        };
    }
}
