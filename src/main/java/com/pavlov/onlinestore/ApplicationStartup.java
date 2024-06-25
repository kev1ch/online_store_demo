package com.pavlov.onlinestore;

import org.flywaydb.core.Flyway;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.context.ApplicationListener;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

import javax.sql.DataSource;

@Order(0)
@Component
public class ApplicationStartup
        implements ApplicationListener<ApplicationReadyEvent> {

    @Autowired
    DataSource dataSource;

    @Value("${flyway.locations}")
    private String flywayLocations;

    @Override
    public void onApplicationEvent(final ApplicationReadyEvent event) {
        flywayStartup();

        return;
    }

    private void flywayStartup() {
        Flyway flyway = Flyway.configure().dataSource(dataSource).locations(flywayLocations).load();
        flyway.migrate();
    }
}