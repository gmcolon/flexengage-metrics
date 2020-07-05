package com.flexengage.metrics.service;

import com.flexengage.metrics.model.Metric;
import com.flexengage.metrics.model.MetricSummary;
import com.flexengage.metrics.repository.MetricRepository;
import com.flexengage.metrics.utils.TestHelper;
import com.flexengage.metrics.utils.exception.BadRequestException;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.List;
import java.util.concurrent.ThreadLocalRandom;

@ExtendWith(MockitoExtension.class)
public class MetricsServiceTest {
    @Mock MetricRepository metricRepository;
    @InjectMocks MetricsService metricsService;

    @Test
    public void testGetMetricSummary(){
        //This test covers 1 or more values for a metric
        int rand = ThreadLocalRandom.current().nextInt(1, 6);
        List<Metric> metrics = TestHelper.ALL_METRICS_BY_METRIC.get(rand);
        MetricSummary expectedSummary = TestHelper.ALL_METRICS_SUMMARIES.get(rand);
        String name =  metrics.get(0).getName();

        Mockito.when(metricRepository.existsByName(name)).thenReturn(true);
        Mockito.when(metricRepository.findAllByName(name)).thenReturn(metrics);

        MetricSummary summary = metricsService.getMetricSummary(name);

        Assertions.assertEquals(expectedSummary, summary);
    }

    @Test
    public void testGetMetricSummaryThrowsBadRequestMetricNotExist(){
        String name =  TestHelper.METRIC_NAME_0;

        Mockito.when(metricRepository.existsByName(name)).thenReturn(false);

        Assertions.assertThrows(BadRequestException.class, () -> metricsService.getMetricSummary(name));
    }
}
