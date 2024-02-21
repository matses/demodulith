package fr.ippon.mase.demodulith;

import org.junit.jupiter.api.Test;
import org.springframework.modulith.core.ApplicationModules;

public class ModularityIT {

    ApplicationModules modules = ApplicationModules.of(DemodulithApplication.class);

    @Test
    void verifiesModularStructure() {
        modules.stream().forEach(System.out::println);
        modules.verify();
    }
}
