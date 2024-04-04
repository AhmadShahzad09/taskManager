module mff.java {
    requires javafx.controls;
    requires javafx.fxml;
    requires java.sql;
    requires com.google.common;
    requires sqlite.jdbc;

    opens project.java to javafx.fxml;
    exports project.java;
}
