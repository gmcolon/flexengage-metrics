package com.flexengage.metrics.service;

import com.flexengage.metrics.model.Metric;
import com.flexengage.metrics.model.MetricSummary;
import com.flexengage.metrics.repository.MetricRepository;
import com.flexengage.metrics.utils.exception.BadRequestException;

import javax.inject.Inject;
import javax.inject.Named;
import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

@Named
public class MetricsService {

    @Inject MetricRepository metricRepository;
    private final String VALID_NAME = "^[a-zA-Z0-9_-]*$";

    public Metric createMetric(Metric metric){
        validateCreateMetric(metric);
        return metricRepository.save(metric);
    }

    public Metric addValueToMetric(Metric metric){
        validateAddValueToMetric(metric);
        return metricRepository.save(metric);
    }

    public MetricSummary getMetricSummary(String name){
        validateGetSummary(name);
        List<Metric> metrics = metricRepository.findAllByName(name);
        boolean hasNoMetrics = metrics.size() == 0;
        //Return empty summary if no metric data
        return hasNoMetrics ? MetricSummary.builder().build()
                : MetricSummary.builder()
                .name(name)
                .median(calculateMedian(metrics))
                .mean(calculateMean(metrics))
                .max(calculateMax(metrics))
                .min(calculateMin(metrics))
                .build();
    }

    private void validateCreateMetric(Metric metric) {
        if(metric == null || metric.getName() == null || metric.getName().isEmpty()){
            throw new BadRequestException("Name is required to create a new metric.");
        }

        if(metric.getUuid() != null || metric.getValue() != null){
            throw new BadRequestException("Metric name is the only allowed property for a new metric.");
        }

        validateNameIsValidString(metric.getName());

        if(metricRepository.existsByName(metric.getName())){
            throw new BadRequestException("Metric name must be unique.");
        }
    }

    private void validateAddValueToMetric(Metric metric) {
        if(metric == null || metric.getName() == null || metric.getName().isEmpty() || metric.getValue() == null){
            throw new BadRequestException("Metric name and value are required to post values to a metric.");
        }
        if(metric.getUuid() != null){
            throw new BadRequestException("Metric name and value are the only allowed properties for a new metric.");
        }

        validateNameIsValidString(metric.getName());

        if(!metricRepository.existsByName(metric.getName())){
            throw new BadRequestException("Metric must exist to add values.");
        }
    }

    private void validateGetSummary(String name) {
        if(name == null || name.isEmpty()){
            throw new BadRequestException("Metric name is required to get a summary.");
        }

        validateNameIsValidString(name);
    }

    private void validateNameIsValidString(String name){
        if(!name.matches(VALID_NAME)){
            throw new BadRequestException("Metric name is not a valid string. " +
                    "Only characters, numbers, dashes, and underscores allowed");
        }
    }

    private BigDecimal calculateMin(List<Metric> metrics){
        return metrics.stream().map(Metric::getValue).filter(val -> val!=null)
                .min(Comparator.naturalOrder()).orElse(null);
    }

    private BigDecimal calculateMax(List<Metric> metrics){
        return metrics.stream().map(Metric::getValue).filter(val -> val!=null)
                .max(Comparator.naturalOrder()).orElse(null);
    }

    private BigDecimal calculateMean(List<Metric> metrics){
        BigDecimal mean = null;
        if(metrics.size() > 1){
            // Need to subtract one for the created record which will always have no values
            int size = metrics.size()-1;
            // Get all values and add them
            BigDecimal sum = metrics.stream().map(Metric::getValue).filter(val -> val!=null)
                    .reduce(BigDecimal.ZERO, BigDecimal::add);
            // Setting the scale to 5 digits after the decimal & strip any trailing zeroes
            mean = sum.divide(BigDecimal.valueOf(size), 5, RoundingMode.CEILING);
            mean = mean.stripTrailingZeros();
        }
        return mean;
    }

    private BigDecimal calculateMedian(List<Metric> metrics){
        BigDecimal median = null;
        if(metrics.size() > 1){
            // Get all values and sort
            List<BigDecimal> values = metrics.stream().map(Metric::getValue).filter(val -> val!=null)
                    .sorted(Comparator.naturalOrder()).collect(Collectors.toList());

            boolean even = values.size() % 2 == 0;
            int index = values.size() / 2;

            BigDecimal valueAtMiddleIndex = values.get(index);

            //If the size is odd, we can just grab the value in the middle of the list
            //If the value is even, we find the average between the two middle elements
            if(even){
                BigDecimal valueBeforeMiddleIndex = values.get(index-1);
                // Setting the scale to 5 digits after the decimal
                median = valueAtMiddleIndex.add(valueBeforeMiddleIndex)
                        .divide(BigDecimal.valueOf(2), 5, RoundingMode.CEILING);
            } else {
                median = valueAtMiddleIndex;
            }
            // Strip any trailing zeroes
            median = median.stripTrailingZeros();
        }
        return median;
    }
}
