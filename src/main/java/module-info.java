module org.example.triviagame {
    requires javafx.controls;
    requires javafx.fxml;

    requires java.sql;
    requires org.xerial.sqlitejdbc;

    opens org.example.triviagame to javafx.fxml;
    exports org.example.triviagame;
}
