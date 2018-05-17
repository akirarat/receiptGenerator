package com.mtsai.paypal.dao;

import org.h2.jdbcx.JdbcConnectionPool;

public class Db {
    public static final String driver = "org.h2.Driver";
    public static final String url = "jdbc:h2:./receipts";
    public static final String username = "receipts";
    public static final String password = "receipts";
    public static final JdbcConnectionPool dataSource = JdbcConnectionPool.create(url, username, password);
}
