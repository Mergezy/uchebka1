module com.example.uchebka1 {
    requires javafx.controls;
    requires javafx.fxml;
    requires java.desktop;
    requires java.sql;
    requires mysql.connector.java;


    opens com.example.uchebka1 to javafx.fxml;
    exports com.example.uchebka1;
}