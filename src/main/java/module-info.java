module net.etfbl.pj2.project.bordercrossing {
    requires javafx.controls;
    requires javafx.fxml;

    requires com.dlsc.formsfx;

    opens net.etfbl.pj2.project.bordercrossing to javafx.fxml;
    exports net.etfbl.pj2.project.bordercrossing;
}