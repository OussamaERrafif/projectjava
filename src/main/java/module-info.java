module pharmacy.pharmacyms {
    requires javafx.controls;
    requires javafx.fxml;
    requires json.simple;


    opens com.pharmacy to javafx.fxml;
    exports com.pharmacy;
    exports userInterface;
    opens userInterface to javafx.fxml;
}