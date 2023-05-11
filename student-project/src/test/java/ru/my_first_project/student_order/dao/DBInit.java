package ru.my_first_project.student_order.dao;

import java.net.URL;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.sql.Connection;
import java.sql.Statement;
import java.util.List;
import java.util.stream.Collectors;

public class DBInit {
    public static void startUP() throws Exception {
        URL url1 = DictionaryDouImplTest.class.getClassLoader().getResource("student_project.sql");
        List<String> str1 = Files.readAllLines(Paths.get(url1.toURI()));
        String sql1 = str1.stream().collect(Collectors.joining());

        URL url2 = DictionaryDouImplTest.class.getClassLoader().getResource("student_data.sql");
        List<String> str2 = Files.readAllLines(Paths.get(url2.toURI()));
        String sql2 = str2.stream().collect(Collectors.joining());

        try(Connection con = ConnectionBuilder.getConnection();
            Statement statement = con.createStatement()) {
            statement.executeUpdate(sql1);
            statement.executeUpdate(sql2);
        }
    }
}
