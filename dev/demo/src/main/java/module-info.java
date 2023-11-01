module com.example.demo {
    requires javafx.controls;
    requires javafx.fxml;

    requires org.controlsfx.controls;
    requires com.dlsc.formsfx;
    requires net.synedra.validatorfx;
    requires org.kordamp.bootstrapfx.core;

    opens app to javafx.fxml;
    exports app;
    exports event;
    opens event to javafx.fxml;
    exports handler;
    opens handler to javafx.fxml;
}