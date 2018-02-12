package ru.javastudy.springMVC.dao;

import org.apache.commons.dbcp2.BasicDataSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.javastudy.springMVC.impl.MessageService;
import ru.javastudy.springMVC.model.MessageModel;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

@Service
public class MessageServiceImpl implements MessageService {

    @Autowired
    BasicDataSource dataSource;

    public List<MessageModel> getMessages() {
        List<MessageModel> messageModels = Collections.emptyList();
        try (Connection connection = dataSource.getConnection()) {
            connection.setAutoCommit(false);
            messageModels = executeSelectAll(connection);
            connection.commit();
        } catch (SQLException e) {
            System.out.println("Exception Message " + e.getMessage());
        }
        return messageModels;
    }

    public MessageModel getMessageById(Integer id) {
        MessageModel messageModel = null;
        try (Connection connection = dataSource.getConnection()) {
            connection.setAutoCommit(false);
            messageModel = executeSelectById(connection, id);
            connection.commit();
        } catch (SQLException e) {
            System.out.println("Exception Message " + e.getMessage());
        }
        return messageModel;
    }

    public MessageModel createMessage(MessageModel message) {
        try (Connection connection = dataSource.getConnection()) {
            connection.setAutoCommit(false);
            message.setId(MessageCounter.getNextValue());
            executeInsert(connection, message);
            connection.commit();
        } catch (SQLException e) {
            System.out.println("Exception Message " + e.getMessage());
        }
        return message;
    }

    private int executeInsert(Connection connection, MessageModel model) throws SQLException {
        String insertQuery = "INSERT INTO MESSAGE(id, message) values (?,?)";
        PreparedStatement insertStmt = connection.prepareStatement(insertQuery);
        insertStmt.setInt(1, model.getId());
        insertStmt.setString(2, model.getMessage());
        int i = insertStmt.executeUpdate();
        insertStmt.close();
        return i;
    }

    private List<MessageModel> executeSelectAll(Connection connection) throws SQLException {
        String selectAllQuery = "select * from MESSAGE";
        List<MessageModel> models = new ArrayList<>();
        PreparedStatement selectStmt = connection.prepareStatement(selectAllQuery);
        ResultSet rs = selectStmt.executeQuery();
        while(rs.next()) {
            models.add(new MessageModel(rs.getInt("id"), rs.getString("message")));
        }
        selectStmt.close();
        return models;
    }

    private MessageModel executeSelectById(Connection connection, Integer id) throws SQLException {
        String selectOneQuery = "select * from MESSAGE where id=?";
        MessageModel model = null;
        PreparedStatement selectStmt = connection.prepareStatement(selectOneQuery);
        selectStmt.setInt(1, id);
        ResultSet rs = selectStmt.executeQuery();
        if(rs.next()) {
            model = new MessageModel(rs.getInt("id"), rs.getString("message"));
        }
        selectStmt.close();
        return model;
    }
}
