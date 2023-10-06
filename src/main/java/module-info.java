module com.iesfranciscodelosrios.chatproyectjaxb {
    requires javafx.controls;
    requires javafx.fxml;
    requires org.controlsfx.controls;
    requires com.dlsc.formsfx;
    requires java.xml.bind;

    opens com.iesfranciscodelosrios.chatproyectjaxb to javafx.fxml;
    exports com.iesfranciscodelosrios.chatproyectjaxb.controller;

    opens com.iesfranciscodelosrios.chatproyectjaxb.controller to javafx.fxml;
    exports com.iesfranciscodelosrios.chatproyectjaxb;

    opens com.iesfranciscodelosrios.chatproyectjaxb.model.dto to java.xml.bind;
}
