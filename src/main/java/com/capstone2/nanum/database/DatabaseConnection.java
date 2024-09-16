package com.capstone2.nanum.database;

import jakarta.annotation.PostConstruct;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Component
public class DatabaseConnection {
    @Value("${spring.datasource.url}")
    private String datasourceUrl;
    @Value("${spring.datasource.username}")
    private String datasourceUsername;
    @PostConstruct
    public void logDatabaseConnectionDetails() {
        System.out.println("Database URL: " + datasourceUrl);
        System.out.println("Database Username: " + datasourceUsername);
        System.out.println("Capstone DB 접속 완료!!!!");
    }
}
