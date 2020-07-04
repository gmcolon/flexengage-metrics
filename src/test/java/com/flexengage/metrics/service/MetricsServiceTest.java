package com.flexengage.metrics.service;

import com.flexengage.metrics.model.Metric;
import com.flexengage.metrics.model.MetricSummary;
import com.flexengage.metrics.repository.MetricRepository;
import com.flexengage.metrics.utils.TestHelper;
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
        //This test covers 0 or more values for a metric
        //for(int rand = 0; rand < 6; rand++) { //ThreadLocalRandom.current().nextInt(0, 5);
        int rand = ThreadLocalRandom.current().nextInt(0, 5);
        List<Metric> metrics = TestHelper.ALL_METRICS_BY_METRIC.get(rand);
            MetricSummary expectedSummary = TestHelper.ALL_METRICS_SUMMARIES.get(rand);
            String name =  metrics.get(0).getName();

            Mockito.when(metricRepository.findAllByName(name)).thenReturn(metrics);

            MetricSummary summary = metricsService.getMetricSummary(name);

            Assertions.assertEquals(expectedSummary, summary);
       // }

    }
}
