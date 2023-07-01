module com.example.giocoAuto {
    requires javafx.controls;
    requires javafx.fxml;


    opens com.example.giocoAuto to javafx.fxml;
    exports com.example.giocoAuto;
}