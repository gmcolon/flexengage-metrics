package com.flexengage.metrics.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import java.math.BigDecimal;
import java.util.UUID;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Entity(name = "metrics")
public class Metric {
    @Id
    @GeneratedValue
    private UUID uuid;
    private String name;
    @Column(columnDefinition = "DECIMAL(65535, 5)")
    private BigDecimal value;
}
