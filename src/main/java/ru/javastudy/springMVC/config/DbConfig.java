package ru.javastudy.springMVC.config;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Statement;
import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;
import org.apache.commons.dbcp2.BasicDataSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;

@Configuration
public class DbConfig {
    private static final String CREATE_TABLE_QUERY = "CREATE TABLE MESSAGE(id int primary key, message varchar(255))";
    private static final String DROP_TABLE_QUERY = "DROP TABLE MESSAGE";

    @Autowired
    BasicDataSource dataSource;

    @PostConstruct
    public void init() {
        PreparedStatement createPreparedStatement = null;
        Connection connection = null;
        try {
            connection = dataSource.getConnection();
            connection.setAutoCommit(false);
            createPreparedStatement = connection.prepareStatement(CREATE_TABLE_QUERY);
            createPreparedStatement.executeUpdate();
            createPreparedStatement.close();
            connection.commit();
        } catch (SQLException e2) {
            e2.printStackTrace();
        }
    }

    @PreDestroy
    public void cleanUp() {
        Statement dropStatement = null;
        Connection connection = null;
        try {
            connection = dataSource.getConnection();
            connection.setAutoCommit(false);
            dropStatement = connection.createStatement();
            dropStatement.execute(DROP_TABLE_QUERY);
            dropStatement.close();
            System.out.println("I have cleaned");
            connection.commit();
        } catch (SQLException e) {
            e.printStackTrace();
        }

    }
}
