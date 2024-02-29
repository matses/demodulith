package fr.ippon.mase.demodulith.user;

import fr.ippon.mase.demodulith.SingletonDatabaseContainer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.modulith.test.ApplicationModuleTest;
import org.testcontainers.containers.PostgreSQLContainer;

@ApplicationModuleTest
class UserManagementIT {

    @Autowired
    private UserManagement userManagement;

    private PostgreSQLContainer postgresqlContainer = SingletonDatabaseContainer.getInstance();



}