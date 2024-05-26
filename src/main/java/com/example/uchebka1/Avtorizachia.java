package com.example.uchebka1;

import java.io.IOException;
import java.net.URL;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;

public class Avtorizachia {

    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private Button enter_button;

    @FXML
    private TextField login_text;

    @FXML
    private TextField pasword_text;
    static User user = new User();

    @FXML
    private Button regictr_button;
    @FXML
    void initialize() {
        // кнопка с методом удаляющий пустые места
        enter_button.setOnAction(event -> {
            String p1 = pasword_text.getText().trim();
            String password_text = hashString(p1);

            String loginText = login_text.getText().trim();
            enter_button.getScene().getWindow().hide();
            if (!loginText.equals("") && !password_text.equals(""))
            {
                loginUser(loginText, password_text);
            }

            else {
                Alert alert = new Alert(Alert.AlertType.INFORMATION);
                alert.setTitle("Message");
                alert.setHeaderText(null);
                alert.setContentText("Введите корректные данные!");
                alert.showAndWait();
            }
        });
    }

    @FXML
    void Entrance(MouseEvent event) {
        regictr_button.getScene().getWindow().hide();
        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(getClass().getResource("Registr.fxml"));
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
    private void loginUser(String loginText, String loginPassword) {
        DataHelper dbHandler = new DataHelper();

        user.setLogin(loginText);
        user.setPassword(loginPassword);
        ResultSet result = dbHandler.getUser(user);
        try {
            if (result.next()) {
                user.setPhone(result.getString("phone"));
                user.setRol(result.getString("rol"));
                openRoleBasedWindow();
            } else {
                showAlert("Пользователь не найден или неверный пароль.");
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    private void openRoleBasedWindow() {
        String fxmlFile = "";
        switch (user.getRol()) {
            case "admin":
                fxmlFile = "Admin.fxml";
                break;
            case "2":
                fxmlFile = "Klient.fxml";
                break;
            default:
                showAlert("Неизвестная роль пользователя.");
                return;
        }
        loadWindow(fxmlFile);
    }

    private void loadWindow(String fxmlFile) {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource(fxmlFile));
            Parent root = loader.load();
            Stage stage = new Stage();
            stage.setScene(new Scene(root));
            stage.showAndWait();
        } catch (IOException e) {
            e.printStackTrace();
            showAlert("Ошибка при открытии окна: " + fxmlFile);
        }
    }

    private void showAlert(String message) {
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("Сообщение");
        alert.setHeaderText(null);
        alert.setContentText(message);
        alert.showAndWait();
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


