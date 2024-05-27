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
    private TextField login_registr;//хранит пароль

    @FXML
    private TextField phone_registr;

    @FXML
    private Button registr_button;

    DataHelper dbHandler = new DataHelper();


    @FXML
    void initialize() {
        registr_button.setOnAction(event -> {
            signUpNewUser(TF3.getText(), login_registr.getText(), phone_registr.getText());
            if(!login_registr.getText().isEmpty() && !TF3.getText().isEmpty() && !phone_registr.getText().isEmpty()){
                Alert alert = new Alert(Alert.AlertType.INFORMATION);
                alert.setTitle("Message");
                alert.setHeaderText(null);
                alert.setContentText("Вы зарегистрированы!Вернитесь на главную форму и авторизуйтесь!");
                alert.showAndWait();
            }

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

    public void signUpNewUser(String login_registr, String passwordS, String phone_registr) {
        String p1 = login_registr;
        String password = hashString(login_registr);

        User user = new User(passwordS, password, phone_registr,"2");
        System.out.println(passwordS+", "+password+", "+phone_registr);
        if(!login_registr.isEmpty() && !passwordS.isEmpty() && !phone_registr.isEmpty()){
            dbHandler.sinUpUser(user);

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