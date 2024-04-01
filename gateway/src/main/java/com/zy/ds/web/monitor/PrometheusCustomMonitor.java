package com.zy.ds.web.monitor;

import io.micrometer.core.instrument.Counter;
import io.micrometer.core.instrument.MeterRegistry;
import org.springframework.beans.factory.annotation.Autowired;
import javax.annotation.PostConstruct;
import org.springframework.stereotype.Component;


@Component
public class PrometheusCustomMonitor {
    private Counter orderCount;

    private final MeterRegistry registry;

    @Autowired
    public PrometheusCustomMonitor(MeterRegistry registry) {
        this.registry = registry;
    }

    @PostConstruct
    private void init() {
        orderCount = registry.counter("order_request_count", "order", "test-svc");
    }

    public Counter getOrderCount() {
        return orderCount;
    }
}
