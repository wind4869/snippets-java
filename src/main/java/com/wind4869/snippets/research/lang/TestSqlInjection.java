package com.wind4869.snippets.research.lang;

import java.sql.*;

/**
 * TestSqlInjection
 *
 * @author wind4869
 * @since 1.0.0
 */
public class TestSqlInjection {
    public static void main(String[] args) throws SQLException {
        String name = "' or 1 = '1";
        String sql = String.format("select * from person where name = '%s';", name);
        try (
                Connection connection = getConnection();
                Statement statement = connection.createStatement();
                ResultSet resultSet = statement.executeQuery(sql)
        ) {
            // get all records in this table
            while (resultSet.next()) {
                System.out.println(resultSet.getString("name"));
            }
        }

        try (
                Connection connection = getConnection();
                PreparedStatement statement = getPreparedStatement(connection, name);
                ResultSet resultSet = statement.executeQuery()
        ) {
            // get records whose name = '\' or 1 = \'1'
            while (resultSet.next()) {
                System.out.println(resultSet.getString("name"));
            }
        }
    }

    private static Connection getConnection() throws SQLException {
        return DriverManager.getConnection("jdbc:mysql://127.0.0.1:3306/test", "root", "");
    }

    private static PreparedStatement getPreparedStatement(
            Connection connection, String name
    ) throws SQLException {
        PreparedStatement statement = connection.prepareStatement("select * from person where name = ?");
        statement.setString(1, name);
        return statement;
    }
}
