package fr.ippon.mase.demodulith;

import com.github.dockerjava.api.command.CreateContainerCmd;
import com.github.dockerjava.api.model.ExposedPort;
import com.github.dockerjava.api.model.HostConfig;
import com.github.dockerjava.api.model.PortBinding;
import com.github.dockerjava.api.model.Ports;
import org.testcontainers.containers.PostgreSQLContainer;

/**
 * Use of singleton containers method (https://java.testcontainers.org/test_framework_integration/manual_lifecycle_control/#singleton-containers)
 * to ease spring modulith and test-containers integration .
 * This is a workaround for IT class declaring both @TestContainers and @ApplicationModuleTest causing "Mapped port can only be obtained after the container is started"
 * since @ApplicationModuleTest declares @TestInstance(Lifecycle.PER_CLASS) which is in conflict with TestContainers
 */

public abstract class SingletonDatabaseContainer {

    private static PostgreSQLContainer container;

    public static PostgreSQLContainer getInstance() {
        if (container == null) {
            container = (PostgreSQLContainer) new PostgreSQLContainer("postgres:16-alpine")
                    .withDatabaseName("demodulith")
                    .withUsername("root")
                    .withPassword("root")
                    .withExposedPorts(5432)
                    .withCreateContainerCmdModifier( cmd -> ((CreateContainerCmd)cmd).withHostConfig(
                            new HostConfig().withPortBindings(new PortBinding(Ports.Binding.bindPort(5432), new ExposedPort(5432)))
                    ));
            container.start();
        }
        return container;
    }

}
