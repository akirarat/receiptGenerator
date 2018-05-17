package com.mtsai.paypal.dao;

import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.exception.ExceptionUtils;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.io.ByteArrayInputStream;
import java.sql.*;

@Slf4j
public class DbUtil {
    public static void close(Connection connection) {
        if (connection != null) {
            try {
                connection.close();
            } catch (SQLException e) {
                log.error(ExceptionUtils.getStackTrace(e));
            }
        }
    }

    public static void close(Statement statement) {
        if (statement != null) {
            try {
                statement.close();
            } catch (SQLException e) {
                log.error(ExceptionUtils.getStackTrace(e));
            }
        }
    }

    public static void close(ResultSet resultSet) {
        if (resultSet != null) {
            try {
                resultSet.close();
            } catch (SQLException e) {
                log.error(ExceptionUtils.getStackTrace(e));
            }
        }
    }

    public static void rollback(Connection connection) {
        if (connection != null) {
            try {
                connection.rollback();
            } catch (SQLException e) {
                log.error(ExceptionUtils.getStackTrace(e));
            }
        }
    }

    public static void setNullable(@NotNull final PreparedStatement preparedStatement, final int parameterIndex, @Nullable final byte[] binaryValue) throws SQLException {
        if (binaryValue != null)
            preparedStatement.setBinaryStream(parameterIndex, new ByteArrayInputStream(binaryValue));
        else
            preparedStatement.setNull(parameterIndex, Types.BLOB);
    }

    public static void setNullable(@NotNull final PreparedStatement preparedStatement, final int parameterIndex, @Nullable final java.util.Date dateValue) throws SQLException {
        if (dateValue != null)
            preparedStatement.setTimestamp(parameterIndex, new java.sql.Timestamp(dateValue.getTime()));
        else
            preparedStatement.setNull(parameterIndex, Types.TIMESTAMP);
    }

    public static void setNullable(@NotNull final PreparedStatement preparedStatement, final int parameterIndex, @Nullable final Double doubleValue) throws SQLException {
        if (doubleValue != null)
            preparedStatement.setDouble(parameterIndex, doubleValue);
        else
            preparedStatement.setNull(parameterIndex, Types.DOUBLE);
    }

    public static void setNullable(@NotNull final PreparedStatement preparedStatement, final int parameterIndex, @Nullable final Integer integerValue) throws SQLException {
        if (integerValue != null)
            preparedStatement.setInt(parameterIndex, integerValue);
        else
            preparedStatement.setNull(parameterIndex, Types.INTEGER);
    }

    public static void setNullable(@NotNull final PreparedStatement preparedStatement, final int parameterIndex, @Nullable final String stringValue) throws SQLException {
        if (stringValue != null)
            preparedStatement.setString(parameterIndex, stringValue.substring(0, Math.min(65535, stringValue.length())));
        else
            preparedStatement.setNull(parameterIndex, Types.VARCHAR);
    }
}
