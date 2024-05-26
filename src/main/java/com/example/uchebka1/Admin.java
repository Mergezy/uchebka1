package com.example.uchebka1;

import java.awt.*;
import java.io.IOException;
import java.net.URL;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ResourceBundle;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;

import static com.example.uchebka1.Avtorizachia.user;

public class Admin {

    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

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
    private Button back;

    DataHelper dbHandler = new DataHelper();

    @FXML
    void initialize() {
        id_table_accounts.setCellValueFactory(new PropertyValueFactory<>("idusers"));
        login_table_accounts.setCellValueFactory(new PropertyValueFactory<>("login"));
        phone_table_accounts.setCellValueFactory(new PropertyValueFactory<>("phone"));

        loadUserData();
        dell_accounts.setOnAction(even -> deleteSelectedItem());
        add_accounts.setOnAction(even -> addNewUser());
    }

    private void loadUserData() {
        try {
            ObservableList<User> userList = dbHandler.getAllUsers();
            tableView.setItems(userList);
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
        login_accounts.getText();
        phone_accounts.getText();
        password_accounts.getText();

        Registr registr = new Registr();
        registr.signUpNewUser(login_accounts.getText(),password_accounts.getText(),phone_accounts.getText());
        loadUserData();
    }
    public void back() {
        back.getScene().getWindow().hide();

        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(getClass().getResource("Avtiriz.xml.fxml"));
        try {
            loader.load();
        } catch (IOException e) {
            e.printStackTrace();
        }
        Parent root = loader.getRoot();
        Stage stage = new Stage();
        stage.setScene(new Scene(root));
        stage.show();
    }
}
