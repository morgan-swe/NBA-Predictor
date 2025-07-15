module com.example.demo {
    requires javafx.controls;
    requires javafx.fxml;
    requires org.testng;


    opens app to javafx.fxml;
    exports app;
}