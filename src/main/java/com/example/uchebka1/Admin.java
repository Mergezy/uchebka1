package com.example.uchebka1;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TextField;

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
    private TextField id_accounts;

    @FXML
    private TableColumn<?, ?> id_table_accounts;

    @FXML
    private TextField login_accounts;

    @FXML
    private TableColumn<?, ?> login_table_accounts;

    @FXML
    private TextField phone_accounts;

    @FXML
    private TableColumn<?, ?> phone_table_accounts;

    @FXML
    void initialize() {
    }

}
