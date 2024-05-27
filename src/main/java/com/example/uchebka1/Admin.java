package com.example.uchebka1;

import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;

import java.io.IOException;
import java.sql.SQLException;

public class Admin {

    @FXML
    private Button add_accounts;

    @FXML
    private Button dell_accounts;

    @FXML
    private TableColumn<User, String> id_table_accounts;

    @FXML
    private TextField login_accounts;

    @FXML
    private TableColumn<String, String> login_table_accounts;

    @FXML
    private TextField phone_accounts;

    @FXML
    private TextField password_accounts;

    @FXML
    private TableColumn<String, String> phone_table_accounts;

    @FXML
    private TableView<User> tableView;
    @FXML
    private TableView<User> tableEvent;
    @FXML
    private TableColumn<User, String> id;
    @FXML
    private TableColumn<User, String> type;
    @FXML
    private TableColumn<User, String> date;
    @FXML
    private TableColumn<User, String> name_event;
    @FXML
    private TableColumn<User, String> count;
    @FXML
    private TableColumn<User, String> name_user;
    @FXML
    private TableColumn<User, String> phone;

    @FXML
    private Button back;

    DataHelper dbHandler = new DataHelper();

    @FXML
    void initialize() {
        id_table_accounts.setCellValueFactory(new PropertyValueFactory<>("idusers"));
        login_table_accounts.setCellValueFactory(new PropertyValueFactory<>("login"));
        phone_table_accounts.setCellValueFactory(new PropertyValueFactory<>("phone"));

        loadUserData();
        dell_accounts.setOnAction(event -> deleteSelectedItem());
        add_accounts.setOnAction(event -> addNewUser());

        id.setCellValueFactory(new PropertyValueFactory<>("idevent"));
        type.setCellValueFactory(new PropertyValueFactory<>("typeOfEvent"));
        date.setCellValueFactory(new PropertyValueFactory<>("date"));
        name_event.setCellValueFactory(new PropertyValueFactory<>("nameOfEvent"));
        count.setCellValueFactory(new PropertyValueFactory<>("countPeople"));
        name_user.setCellValueFactory(new PropertyValueFactory<>("login"));
        phone.setCellValueFactory(new PropertyValueFactory<>("phone"));

        loadEventData();
    }

    private void loadUserData() {
        try {
            ObservableList<User> userList = dbHandler.getAllUsers();
            tableView.setItems(userList);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void loadEventData() {
        try {
            ObservableList<User> userList1 = dbHandler.getEvent();
            tableEvent.setItems(userList1);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void deleteSelectedItem() {
        User selectedUser = tableView.getSelectionModel().getSelectedItem();
        if (selectedUser != null) {
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            try {
                dbHandler.deleteUser(selectedUser.getIdusers());
                tableView.getItems().remove(selectedUser);
            } catch (SQLException e) {
                e.printStackTrace();
            } catch (ClassNotFoundException e) {
                throw new RuntimeException(e);
            }
        }
        loadUserData();
    }

    private void addNewUser() {
        String login = login_accounts.getText();
        String phone = phone_accounts.getText();
        String password = password_accounts.getText();

        Registr registr = new Registr();
        registr.signUpNewUser(password,login, phone);
        loadUserData();
    }

    public void back() {
        back.getScene().getWindow().hide();

        try {
            Stage stage = new Stage();
            stage.setScene(new Scene(FXMLLoader.load(getClass().getResource("Avtiriz.xml.fxml"))));
            stage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
