package com.example.uchebka1;

import java.sql.*;

public class DataHelper extends Confics {
    static Connection dbConnection = null;

    public Connection getDbConnection()
            throws ClassNotFoundException, SQLException {
        //Формирует строку подключения к базе данных, используя значения переменных dbHost, dbPort и dbName, которые наследуются от класса Configs.
        String connectionString = "jdbc:mysql://" + dbHost + ":" + dbPort + "/" + dbName;
        //Загружает драйвер JDBC для базы данных MySQL, используя метод Class.forName.
        Class.forName("com.mysql.cj.jdbc.Driver");
        //Устанавливает соединение с базой данных, используя метод DriverManager.getConnection.
        dbConnection = DriverManager.getConnection(connectionString, dbUser, dbPass);
        // возвращает правильный метод
        return dbConnection;
    }
    public void sinUpUser(User user) {
        // SQL запрос устанавливающий данные
        String insert = "INSERT INTO " + Const.USER_TABLE + " (" +
                Const.USERS_USERNAME + ", " + Const.USERS_PASSWORD + ", " + Const.USERS_PHONE + ", rol) " +
                "VALUES (?, ?, ?, ?)";

        try {
            PreparedStatement prSt = getDbConnection().prepareStatement(insert);
            prSt.setString(1, user.getLogin());
            prSt.setString(2, user.getPassword());
            prSt.setString(3, user.getPhone());
            prSt.setString(4, user.getRol());

            prSt.executeUpdate();
        } catch (SQLException | ClassNotFoundException e) {
            throw new RuntimeException(e);
        }
    }

    public ResultSet getUser(User user) {
        ResultSet resSet = null;

        String select = "SELECT * FROM " + Const.USER_TABLE + " WHERE " +
                Const.USERS_USERNAME + "=? AND " + Const.USERS_PASSWORD + "=?";
        System.out.println("Executing query: " + select);
        System.out.println("USERS_USERNAME: " + Const.USERS_USERNAME);
        System.out.println("USERS_PASSWORD: " + Const.USERS_PASSWORD);
        try {
            PreparedStatement prSt = getDbConnection().prepareStatement(select);
            prSt.setString(1, user.getLogin());
            prSt.setString(2, user.getPassword());

            resSet = prSt.executeQuery();
        } catch (SQLException | ClassNotFoundException e) {
            throw new RuntimeException(e);
        }
        return resSet;
    }
}
