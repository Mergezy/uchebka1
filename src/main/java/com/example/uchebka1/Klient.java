package com.example.uchebka1;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;

import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.time.LocalDate;

public class Klient {

    @FXML
    private MenuButton services;

    @FXML
    private MenuItem marriage;

    @FXML
    private MenuItem festival;

    @FXML
    private MenuItem forum;

    @FXML
    private Button sendrRquest;

    @FXML
    private DatePicker date_of_event;

    @FXML
    private TextField name_of_event;

    @FXML
    private TextField count_people;

    @FXML
    private Button back;

    @FXML
    public void initialize() {
        marriage.setOnAction(even -> services.setText(marriage.getText()));
        festival.setOnAction(even -> services.setText(festival.getText()));
        forum.setOnAction(even -> services.setText(forum.getText()));
        date_of_event.setValue(LocalDate.now().plusDays(1));
        sendrRquest.setOnAction(even -> registerEvent());
    }
    private void showAlert(Alert.AlertType alertType, String title, String content) {
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("Сообщение");
        alert.setHeaderText(null);
        alert.setContentText(content);
        alert.showAndWait();
    }
    public void registerEvent() {

        LocalDate selectedDate = date_of_event.getValue();
        LocalDate today = LocalDate.now();

        if (!selectedDate.isAfter(today)) {
            showAlert(Alert.AlertType.ERROR, "Ошибка", "Дата должна быть "+ today +" .");
            return;
        }

        String countText = count_people.getText();
        try {
            int count = Integer.parseInt(countText);
            if (count < 0 || count > 100000) {
                throw new IllegalArgumentException("Количество людей должно быть от 0 до 100000.");
            } else if (services.getText().equals("Услуги")) {
                throw new IllegalArgumentException("Пожалуйста, выберите услугу.");
            } else if (name_of_event.getText().isEmpty()) {
                showAlert(Alert.AlertType.ERROR, "Ошибка", "Название не может быть пустым.");
            } else {
                DataHelper dbhelper = new DataHelper();
                dbhelper.registerEvent(services.getText(), selectedDate.toString(), name_of_event.getText(), countText, Avtorizachia.user);
            }
        } catch (NumberFormatException e) {
            showAlert(Alert.AlertType.ERROR, "Ошибка", "Неверное значение для количества людей. Введите число от 0 до 100000.");
        } catch (IllegalArgumentException e) {
            showAlert(Alert.AlertType.ERROR, "Ошибка", e.getMessage());
        }
    }
    public void back(MouseEvent mouseEvent) {
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
