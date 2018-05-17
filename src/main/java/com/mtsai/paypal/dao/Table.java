package com.mtsai.paypal.dao;


import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Statement;

public class Table {
    public static void createAll() {
        createTx();
    }

    public static void createTx() {
        PreparedStatement statement = null;
        Connection connection = null;
        try {
            connection = Db.dataSource.getConnection();
            statement = connection.prepareStatement("CREATE TABLE IF NOT EXISTS tx (" +
                    "id VARCHAR(255) NOT NULL, " +
                    "ipn VARCHAR(10000) NOT NULL, " +
                    "timestamp TIMESTAMP NOT NULL, " +
                    "verifiedTimestamp TIMESTAMP DEFAULT NULL, " +
                    "transactionType VARCHAR(255) DEFAULT NULL, " +
                    "verified BOOLEAN NOT NULL DEFAULT FALSE " +
                    "PRIMARY KEY(id)" +
                    ")");
            statement.executeUpdate();
        } catch (SQLException e) {
        } finally {
            DbUtil.close(statement);
            DbUtil.close(connection);
        }
    }

    public static void createReceipt() {
        PreparedStatement statement = null;
        Connection connection = null;
        try {
            connection = Db.dataSource.getConnection();
            statement = connection.prepareStatement("CREATE TABLE IF NOT EXISTS receipt (" +
                    "txId VARCHAR(255) NOT NULL, " +
                    "recipientEmail VARCHAR(255) DEFAULT NULL, " +
                    "timestamp TIMESTAMP NOT NULL " +
                    "KEY(txId)" +
                    ")");
            statement.executeUpdate();
        } catch (SQLException e) {
        } finally {
            DbUtil.close(statement);
            DbUtil.close(connection);
        }
    }

}
