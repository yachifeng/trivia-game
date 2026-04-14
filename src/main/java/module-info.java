module org.example.triviagame {
    requires javafx.controls;
    requires javafx.fxml;


    opens org.example.triviagame to javafx.fxml;
    exports org.example.triviagame;
}