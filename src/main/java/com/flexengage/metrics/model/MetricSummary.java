package com.flexengage.metrics.model;

import lombok.Builder;
import lombok.Data;

import java.math.BigDecimal;

@Data
@Builder
public class MetricSummary {
    private String name;
    private BigDecimal min;
    private BigDecimal max;
    private BigDecimal mean;
    private BigDecimal median;
}
