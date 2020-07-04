package com.flexengage.metrics.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.flexengage.metrics.model.Metric;
import com.flexengage.metrics.model.MetricSummary;
import com.flexengage.metrics.repository.MetricRepository;
import com.flexengage.metrics.utils.TestHelper;
import com.jayway.jsonpath.JsonPath;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;

import javax.inject.Inject;
import java.util.List;
import java.util.UUID;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;

@SpringBootTest
@AutoConfigureMockMvc
public class MetricsControllerTest {
    @Autowired private MockMvc mockMvc;
    @Inject private MetricRepository metricRepository;
    private static ObjectMapper jsonMapper = new ObjectMapper();

    //************************************************
    //** CREATE METRICS
    //************************************************
    @Test
    public void testCreateMetric() throws Exception {
        MvcResult result = createMetric(TestHelper.METRIC_0_NO_VALUE);

        UUID id = UUID.fromString(JsonPath.read(result.getResponse().getContentAsString(),"$.uuid"));
        Metric saved = metricRepository.findById(id).orElse(null);

        Assertions.assertEquals(TestHelper.METRIC_NAME_0, saved.getName());
    }

    @Test
    public void testCreateMetricEmptyName() throws Exception {
        TestHelper.METRIC_INVALID.setName("");
        this.mockMvc.perform(post("/api/metrics")
                .contentType(MediaType.APPLICATION_JSON)
                .content(jsonMapper.writeValueAsString(TestHelper.METRIC_INVALID)))
                .andExpect(status().isBadRequest())
                .andExpect(content().string("Name is required to create a new metric."))
                .andReturn();
    }

    @Test
    public void testCreateMetricWithIdPopulated() throws Exception {
        TestHelper.METRIC_INVALID.setUuid(UUID.randomUUID());
        this.mockMvc.perform(post("/api/metrics")
                .contentType(MediaType.APPLICATION_JSON)
                .content(jsonMapper.writeValueAsString(TestHelper.METRIC_INVALID)))
                .andExpect(status().isBadRequest())
                .andExpect(content().string("Metric name is the only allowed property for a new metric."))
                .andReturn();
    }

    @Test
    public void testCreateMetricInvalidName() throws Exception {
        this.mockMvc.perform(post("/api/metrics")
                .contentType(MediaType.APPLICATION_JSON)
                .content(jsonMapper.writeValueAsString(TestHelper.METRIC_INVALID)))
                .andExpect(status().isBadRequest())
                .andExpect(content().string("Metric name is not a valid string. " +
                        "Only characters, numbers, dashes, and underscores allowed"))
                .andReturn();
    }

    @Test
    public void testCreateMetricWithDuplicateName() throws Exception {
        createMetric(TestHelper.METRIC_0_NO_VALUE);
        this.mockMvc.perform(post("/api/metrics")
                .contentType(MediaType.APPLICATION_JSON)
                .content(jsonMapper.writeValueAsString(TestHelper.METRIC_0_NO_VALUE)))
                .andExpect(status().isBadRequest())
                .andExpect(content().string("Metric name must be unique."))
                .andReturn();
    }

    //************************************************
    //** ADD VALUES
    //************************************************

    @Test
    public void testAddValueToMetric() throws Exception {
        createMetric(TestHelper.METRIC_1_NO_VALUE);
        MvcResult result = addValueToMetric(TestHelper.METRIC_1_VALUE_1);

        UUID id = UUID.fromString(JsonPath.read(result.getResponse().getContentAsString(),"$.uuid"));
        Metric savedMetric = metricRepository.findById(id).orElse(null);
        List<Metric> saved = metricRepository.findAllByName(TestHelper.METRIC_NAME_1);

        Assertions.assertTrue(saved.size() == 2);
        Assertions.assertTrue(saved.contains(savedMetric));
    }

    @Test
    public void testAddValueToMetricNoValue() throws Exception {
        this.mockMvc.perform(post("/api/metrics/{name}", TestHelper.METRIC_INVALID.getName())
                .contentType(MediaType.APPLICATION_JSON)
                .content(jsonMapper.writeValueAsString(TestHelper.METRIC_INVALID)))
                .andExpect(status().isBadRequest())
                .andExpect(content().string("Metric name and value are required to post values to a metric."));
    }

    //************************************************
    //** GET SUMMARY
    //************************************************

    @Test
    public void testGetMetricSummary() throws Exception {
        createMetric(TestHelper.METRIC_5_NO_VALUE);
        addValueToMetric(TestHelper.METRIC_5_VALUE_1);
        addValueToMetric(TestHelper.METRIC_5_VALUE_2);
        addValueToMetric(TestHelper.METRIC_5_VALUE_3);
        addValueToMetric(TestHelper.METRIC_5_VALUE_4);
        addValueToMetric(TestHelper.METRIC_5_VALUE_5);
        addValueToMetric(TestHelper.METRIC_5_VALUE_6);
        getMetricSummary(TestHelper.METRIC_5_SUMMARY);
    }

    @Test
    public void testGetMetricSummaryNoValues() throws Exception {
        createMetric(TestHelper.METRIC_5_NO_VALUE);
        MetricSummary emptySummary = MetricSummary.builder().name(TestHelper.METRIC_NAME_5).build();
        getMetricSummary(emptySummary);
    }

    //************************************************
    //** MISC.
    //************************************************
    @Test
    public void testNotFound() throws Exception {
        this.mockMvc.perform(post("/api/metrics/abc/def/")
                .contentType(MediaType.APPLICATION_JSON)
                .content(jsonMapper.writeValueAsString(TestHelper.METRIC_INVALID)))
                .andExpect(status().isNotFound())
                .andExpect(content().string("Action not found. Please check request and try again"));
    }

    private MvcResult getMetricSummary(MetricSummary summary) throws Exception {
        return this.mockMvc.perform(get("/api/metrics/{name}/summary", summary.getName())
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.name").value(summary.getName()))
                .andExpect(jsonPath("$.min").value(summary.getMin()))
                .andExpect(jsonPath("$.max").value(summary.getMax()))
                .andExpect(jsonPath("$.mean").value(summary.getMean()))
                .andExpect(jsonPath("$.median").value(summary.getMedian()))
                .andReturn();
    }

    private MvcResult addValueToMetric(Metric metric) throws Exception {
        return this.mockMvc.perform(post("/api/metrics/{name}", metric.getName())
                .contentType(MediaType.APPLICATION_JSON)
                .content(jsonMapper.writeValueAsString(metric)))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.name").value(metric.getName()))
                .andExpect(jsonPath("$.value").value(metric.getValue()))
                .andReturn();
    }


    private MvcResult createMetric(Metric metric) throws Exception {
        return this.mockMvc.perform(post("/api/metrics")
                .contentType(MediaType.APPLICATION_JSON)
                .content(jsonMapper.writeValueAsString(metric)))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.name").value(metric.getName()))
                .andReturn();
    }
}
