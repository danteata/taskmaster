package com.abc.taskmaster.containers;

import org.testcontainers.containers.PostgreSQLContainer;

public class ReusablePostgresContainer extends PostgreSQLContainer<ReusablePostgresContainer> {
    private static final String IMAGE_VERSION = "postgres:latest";
    private static ReusablePostgresContainer container;

    private ReusablePostgresContainer() {
        super(IMAGE_VERSION);
    }

    public static ReusablePostgresContainer getInstance() {
        if (container == null) {
            container = new ReusablePostgresContainer();
            container.start();
        }
        return container;
    }

    @Override
    public void start() {
        // Add any specific configurations if needed
        super.start();
    }

    @Override
    public void stop() {
        // Do nothing, JVM handles shut down
    }
}
