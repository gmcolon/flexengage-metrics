package com.flexengage.metrics.controller;

import com.flexengage.metrics.model.Metric;
import com.flexengage.metrics.model.MetricSummary;
import com.flexengage.metrics.service.MetricsService;
import org.springframework.web.bind.annotation.*;

import javax.inject.Inject;

@RestController
@RequestMapping("/api/metrics")
public class MetricsController {

    @Inject private MetricsService metricsService;

    @PostMapping
    public Metric createMetric(@RequestBody Metric metric){
        return metricsService.createMetric(metric);
    }

    @PostMapping("/{name}")
    public Metric addValueToMetric(@RequestBody Metric metric){
        return metricsService.addValueToMetric(metric);
    }

    @GetMapping("/{name}/summary")
    public MetricSummary getMetricSummary(@PathVariable("name") String name){
        return metricsService.getMetricSummary(name);
    }
}
