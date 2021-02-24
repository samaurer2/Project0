package dev.maurer.BankApi.UtilityTests;

import dev.maurer.bank_api.utilities.ConnectionUtil;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.sql.Connection;

public class ConnectionTests {

    @Test
    void systemEnvTest(){
        Assertions.assertNotNull(System.getenv("bankDBConnectionInfo"));
    }

    @Test
    void connectionTest() {
        Connection connection = ConnectionUtil.createConnection();
        Assertions.assertNotNull(connection);
    }

}
