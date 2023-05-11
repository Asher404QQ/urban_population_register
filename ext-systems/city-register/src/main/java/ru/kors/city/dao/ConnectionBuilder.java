package ru.kors.city.dao;

import java.sql.Connection;
import java.sql.SQLException;

interface ConnectionBuilder {
    Connection getConnection() throws SQLException;
}
