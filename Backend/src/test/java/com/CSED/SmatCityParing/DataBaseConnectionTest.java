package com.CSED.SmatCityParing;


import com.CSED.SmartCityParking.SmartCityParkingApplication;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.jdbc.core.JdbcTemplate;

import static org.assertj.core.api.Assertions.assertThat;


@SpringBootTest(classes =  SmartCityParkingApplication.class)
public class DataBaseConnectionTest {


    @Autowired
    JdbcTemplate jdbcTemplate ;

    @Test
    public void testDatabaseConnection() {
        Integer result = jdbcTemplate.queryForObject("SELECT 1;", Integer.class);
        assertThat(result).isEqualTo(1);
    }
}
