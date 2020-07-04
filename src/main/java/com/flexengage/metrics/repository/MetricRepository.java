package com.flexengage.metrics.repository;

import com.flexengage.metrics.model.Metric;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.UUID;

public interface MetricRepository extends CrudRepository<Metric, UUID> {
    List<Metric> findAllByName(@Param("name") String name);
    boolean existsByName(@Param("name") String name);
}