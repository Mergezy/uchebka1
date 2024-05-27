package com.example.uchebka1;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

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

    public void deleteUser(String userId) throws SQLException, ClassNotFoundException {
        String delete = "DELETE FROM " + Const.USER_TABLE + " WHERE " + Const.USERS_ID + "=?";
        try (PreparedStatement prSt = getDbConnection().prepareStatement(delete)) {
            prSt.setString(1, userId);
            prSt.executeUpdate();
        }
    }

    public ObservableList<User> getAllUsers() {
        ObservableList<User> userList = FXCollections.observableArrayList();
        String select = "SELECT * FROM " + Const.USER_TABLE;
        try {
            PreparedStatement prSt = getDbConnection().prepareStatement(select);
            ResultSet resSet = prSt.executeQuery();
            while (resSet.next()) {
                String role = resSet.getString("rol");
                if ("2".equals(role)) {
                    User user = new User(
                            resSet.getString("idusers"),
                            resSet.getString("login"),
                            resSet.getString("phone"));
                    userList.add(user);
                }
            }
        } catch (SQLException | ClassNotFoundException e) {
            e.printStackTrace();
        }
        return userList;
    }

    public void updateUserVerification(User user) {
        String updateQuery = "UPDATE " + Const.USER_TABLE + " SET verified = ? WHERE " + Const.USERS_ID + "=?";
        try {
            PreparedStatement preparedStatement = getDbConnection().prepareStatement(updateQuery);
            preparedStatement.setBoolean(1, user.isVerified());
            preparedStatement.setString(2, user.getIdusers());
            preparedStatement.executeUpdate();
        } catch (SQLException | ClassNotFoundException e) {
            e.printStackTrace();
        }
    }

    public void registerEvent(String type_of_event, String date, String name_of_event, Boolean verified, String count_people, User user) {
        String insert = "INSERT INTO event_date (type_of_event, date, name_of_event, verified, count_people, user_id) " +
                "VALUES (?, ?, ?, ?, ?, ?)";
        try {
            PreparedStatement prSt = getDbConnection().prepareStatement(insert);
            prSt.setString(1, type_of_event);
            prSt.setString(2, date);
            prSt.setString(3, name_of_event);
            prSt.setBoolean(4, verified);
            prSt.setString(5, count_people);
            prSt.setString(6, user.getIdusers());

            //prSt.executeUpdate();
            System.out.println(type_of_event + ", " + date + ", " + name_of_event + ", " + count_people + ", " + user.getIdusers());
        } catch (SQLException | ClassNotFoundException e) {
            throw new RuntimeException(e);
        }
    }
    public ObservableList<User> getEvent() {
        ObservableList<User> eventList = FXCollections.observableArrayList();
        String selectEvent = "SELECT event_date.*, users.login AS login, users.phone AS phone " +
                "FROM event_date " +
                "JOIN users ON event_date.user_id = users.idusers";
        try {
            PreparedStatement prSt = getDbConnection().prepareStatement(selectEvent);
            ResultSet resSet = prSt.executeQuery();
            while (resSet.next()) {
                String idevent = resSet.getString("idevent_date");
                String type_of_event = resSet.getString("type_of_event");
                String date = resSet.getString("date");
                String name_of_event = resSet.getString("name_of_event");
                String count_people = resSet.getString("count_people");
                String nameuser = resSet.getString("login");
                String phone = resSet.getString("phone");
                Boolean verified = resSet.getBoolean("verified");

                User user = new User(idevent, type_of_event, date, name_of_event, count_people, nameuser, phone, verified);
                user.ToString();
                eventList.add(user);
            }
        } catch (SQLException | ClassNotFoundException e) {
            e.printStackTrace();
        }
        return eventList;
    }
}
