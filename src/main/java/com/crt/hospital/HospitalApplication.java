package com.crt.hospital;

import java.util.TimeZone;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class HospitalApplication {
    public static void main(String[] args) {
        // Force a valid timezone for PostgreSQL startup parameters.
        TimeZone.setDefault(TimeZone.getTimeZone("UTC"));
        SpringApplication.run(HospitalApplication.class, args);
    }
}
