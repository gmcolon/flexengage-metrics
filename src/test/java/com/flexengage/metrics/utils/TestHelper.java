package com.flexengage.metrics.utils;

import com.flexengage.metrics.model.Metric;
import com.flexengage.metrics.model.MetricSummary;

import java.math.BigDecimal;
import java.util.Arrays;
import java.util.List;

public class TestHelper {
    //************************************************
    //** METRICS
    //************************************************

    public static final String METRIC_NAME_0 = "METRIC_0";
    public static final String METRIC_NAME_1 = "METRIC_1";
    public static final String METRIC_NAME_2 = "METRIC_2";
    public static final String METRIC_NAME_3 = "METRIC_3";
    public static final String METRIC_NAME_4 = "METRIC_4";
    public static final String METRIC_NAME_5 = "METRIC_5";
    public static final String METRIC_NAME_INVALID = "METRIC_!NV@Li!)";

    public static final BigDecimal METRIC_VALUE_1 = BigDecimal.valueOf(1.987);
    public static final BigDecimal METRIC_VALUE_2 = BigDecimal.valueOf(2.65);
    public static final BigDecimal METRIC_VALUE_3 = BigDecimal.valueOf(-3);
    public static final BigDecimal METRIC_VALUE_4 = BigDecimal.valueOf(4.25);
    public static final BigDecimal METRIC_VALUE_5 = BigDecimal.valueOf(874.50);
    public static final BigDecimal METRIC_VALUE_6 = BigDecimal.valueOf(100.5000000);
    public static final BigDecimal METRIC_VALUE_7 = BigDecimal.valueOf(407);
    public static final BigDecimal METRIC_VALUE_8 = BigDecimal.valueOf(-34.343434);
    public static final BigDecimal METRIC_VALUE_9 = BigDecimal.valueOf(87539754);
    public static final BigDecimal METRIC_VALUE_10 = BigDecimal.valueOf(0);


    public static final Metric METRIC_INVALID = Metric.builder().name(METRIC_NAME_INVALID).build();

    public static final Metric METRIC_0_NO_VALUE = Metric.builder().name(METRIC_NAME_0).build();

    public static final Metric METRIC_1_NO_VALUE = Metric.builder().name(METRIC_NAME_1).build();
    public static final Metric METRIC_1_VALUE_1 = Metric.builder().name(METRIC_NAME_1).value(METRIC_VALUE_1).build();

    public static final Metric METRIC_2_NO_VALUE = Metric.builder().name(METRIC_NAME_2).build();
    public static final Metric METRIC_2_VALUE_1 = Metric.builder().name(METRIC_NAME_2).value(METRIC_VALUE_6).build();
    public static final Metric METRIC_2_VALUE_2 = Metric.builder().name(METRIC_NAME_2).value(METRIC_VALUE_7).build();

    public static final Metric METRIC_3_NO_VALUE = Metric.builder().name(METRIC_NAME_3).build();
    public static final Metric METRIC_3_VALUE_1 = Metric.builder().name(METRIC_NAME_3).value(METRIC_VALUE_1).build();
    public static final Metric METRIC_3_VALUE_2 = Metric.builder().name(METRIC_NAME_3).value(METRIC_VALUE_6).build();
    public static final Metric METRIC_3_VALUE_3 = Metric.builder().name(METRIC_NAME_3).value(METRIC_VALUE_2).build();

    public static final Metric METRIC_4_NO_VALUE = Metric.builder().name(METRIC_NAME_4).build();
    public static final Metric METRIC_4_VALUE_1 = Metric.builder().name(METRIC_NAME_4).value(METRIC_VALUE_8).build();
    public static final Metric METRIC_4_VALUE_2 = Metric.builder().name(METRIC_NAME_4).value(METRIC_VALUE_4).build();
    public static final Metric METRIC_4_VALUE_3 = Metric.builder().name(METRIC_NAME_4).value(METRIC_VALUE_9).build();
    public static final Metric METRIC_4_VALUE_4 = Metric.builder().name(METRIC_NAME_4).value(METRIC_VALUE_5).build();
    public static final Metric METRIC_4_VALUE_5 = Metric.builder().name(METRIC_NAME_4).value(METRIC_VALUE_10).build();
    public static final Metric METRIC_4_VALUE_6 = Metric.builder().name(METRIC_NAME_4).value(METRIC_VALUE_6).build();

    public static final Metric METRIC_5_NO_VALUE = Metric.builder().name(METRIC_NAME_5).build();
    public static final Metric METRIC_5_VALUE_1 = Metric.builder().name(METRIC_NAME_5).value(METRIC_VALUE_1).build();
    public static final Metric METRIC_5_VALUE_2 = Metric.builder().name(METRIC_NAME_5).value(METRIC_VALUE_10).build();
    public static final Metric METRIC_5_VALUE_3 = Metric.builder().name(METRIC_NAME_5).value(METRIC_VALUE_2).build();
    public static final Metric METRIC_5_VALUE_4 = Metric.builder().name(METRIC_NAME_5).value(METRIC_VALUE_9).build();
    public static final Metric METRIC_5_VALUE_5 = Metric.builder().name(METRIC_NAME_5).value(METRIC_VALUE_3).build();
    public static final Metric METRIC_5_VALUE_6 = Metric.builder().name(METRIC_NAME_5).value(METRIC_VALUE_8).build();

    public static List<Metric> ALL_METRICS_0_VALUES = Arrays.asList(METRIC_0_NO_VALUE);

    public static List<Metric> ALL_METRICS_1_VALUES = Arrays.asList(METRIC_1_NO_VALUE, METRIC_1_VALUE_1);

    public static List<Metric> ALL_METRICS_2_VALUES = Arrays.asList(METRIC_2_NO_VALUE, METRIC_2_VALUE_1, METRIC_2_VALUE_2);

    public static List<Metric> ALL_METRICS_3_VALUES = Arrays.asList(METRIC_3_NO_VALUE, METRIC_3_VALUE_1, METRIC_3_VALUE_2, METRIC_3_VALUE_3);

    public static List<Metric> ALL_METRICS_4_VALUES = Arrays.asList(
            METRIC_4_NO_VALUE, METRIC_4_VALUE_1, METRIC_4_VALUE_2,
            METRIC_4_VALUE_3, METRIC_4_VALUE_4, METRIC_4_VALUE_5, METRIC_4_VALUE_6
    );
    public static List<Metric> ALL_METRICS_5_VALUES = Arrays.asList(
            METRIC_5_NO_VALUE, METRIC_5_VALUE_1, METRIC_5_VALUE_2,
            METRIC_5_VALUE_3, METRIC_5_VALUE_4, METRIC_5_VALUE_5, METRIC_5_VALUE_6
    );

    public static List<Metric> ALL_METRICS = Arrays.asList(
            METRIC_0_NO_VALUE,
            METRIC_1_NO_VALUE, METRIC_1_VALUE_1,
            METRIC_2_NO_VALUE, METRIC_2_VALUE_1, METRIC_2_VALUE_2,
            METRIC_3_NO_VALUE, METRIC_3_VALUE_1, METRIC_3_VALUE_2, METRIC_3_VALUE_3,
            METRIC_4_NO_VALUE, METRIC_4_VALUE_1, METRIC_4_VALUE_2, METRIC_4_VALUE_3, METRIC_4_VALUE_4, METRIC_4_VALUE_5,
            METRIC_5_NO_VALUE, METRIC_5_VALUE_1, METRIC_5_VALUE_2, METRIC_5_VALUE_3, METRIC_5_VALUE_4, METRIC_5_VALUE_5
    );

    public static List<List<Metric>> ALL_METRICS_BY_METRIC = Arrays.asList(
            ALL_METRICS_0_VALUES, ALL_METRICS_1_VALUES, ALL_METRICS_2_VALUES, ALL_METRICS_3_VALUES, ALL_METRICS_4_VALUES, ALL_METRICS_5_VALUES
    );

    //************************************************
    //** METRICS SUMMARY
    //************************************************
    public static final MetricSummary METRIC_0_SUMMARY = MetricSummary.builder()
            .name(METRIC_NAME_0).build();
    public static final MetricSummary METRIC_1_SUMMARY = MetricSummary.builder()
            .name(METRIC_NAME_1).min(BigDecimal.valueOf(1.987)).max(BigDecimal.valueOf(1.987)).mean(BigDecimal.valueOf(1.987)).median(BigDecimal.valueOf(1.987)).build();
    public static final MetricSummary METRIC_2_SUMMARY = MetricSummary.builder()
            .name(METRIC_NAME_2).min(BigDecimal.valueOf(100.500000000000)).max(BigDecimal.valueOf(407)).mean(BigDecimal.valueOf(253.75)).median(BigDecimal.valueOf(253.75)).build();
    public static final MetricSummary METRIC_3_SUMMARY = MetricSummary.builder()
            .name(METRIC_NAME_3).min(BigDecimal.valueOf(1.987)).max(BigDecimal.valueOf(100.500000000000)).mean(BigDecimal.valueOf(35.04567)).median(BigDecimal.valueOf(2.65)).build();
    public static final MetricSummary METRIC_4_SUMMARY = MetricSummary.builder()
            .name(METRIC_NAME_4).min(BigDecimal.valueOf(-34.34343)).max(BigDecimal.valueOf(87539754)).mean(BigDecimal.valueOf(14590116.48443)).median(BigDecimal.valueOf(52.37500)).build();
    public static final MetricSummary METRIC_5_SUMMARY = MetricSummary.builder()
            .name(METRIC_NAME_5).min(BigDecimal.valueOf(-34.34343)).max(BigDecimal.valueOf(87539754)).mean(BigDecimal.valueOf(14589953.54893)).median(BigDecimal.valueOf(0.9935)).build();

    public static List<MetricSummary> ALL_METRICS_SUMMARIES = Arrays.asList(
            METRIC_0_SUMMARY,METRIC_1_SUMMARY,METRIC_2_SUMMARY,METRIC_3_SUMMARY,METRIC_4_SUMMARY,METRIC_5_SUMMARY);
}
