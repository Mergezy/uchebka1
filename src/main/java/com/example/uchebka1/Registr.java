package com.example.uchebka1;

import java.io.IOException;
import java.net.URL;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

public class Registr {

    @FXML
    private TextField TF3;

    @FXML
    private Button back;

    @FXML
    private TextField login_registr;

    @FXML
    private TextField phone_registr;

    @FXML
    private Button registr_button;


    @FXML
    void initialize() {
        registr_button.setOnAction(event -> {
            signUpNewUser();
        });

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
    private void signUpNewUser() {
        String p1 = TF3.getText();
        String password = hashString(p1);
        DataHelper dbHandler = new DataHelper();

        String Name = login_registr.getText();
        String phone = phone_registr.getText();

        User user = new User(Name, password, phone,"2");

        if(!login_registr.getText().isEmpty() && !TF3.getText().isEmpty() && !phone_registr.getText().isEmpty()){
            dbHandler.sinUpUser(user);
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("Message");
            alert.setHeaderText(null);
            alert.setContentText("Вы зарегистрированы!Вернитесь на главную форму и авторизуйтесь!");
            alert.showAndWait();

        }else {
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("Message");
            alert.setHeaderText(null);
            alert.setContentText("Введите корректные данные!");
            alert.showAndWait();
        }
    }
    private String hashString(String password) {
        try {
            MessageDigest digest = MessageDigest.getInstance("SHA-256");
            byte[] hashBytes = digest.digest(password.getBytes());
            StringBuilder hexString = new StringBuilder();
            for (byte a : hashBytes) {
                String hex = Integer.toHexString(0xff & a);
                if (hex.length() == 1) {hexString.append('0');}
                hexString.append(hex);
            }
            return hexString.toString();
        } catch (NoSuchAlgorithmException ex) {
            ex.printStackTrace();
            return null;
        }
    }
}