module app {
    requires transitive javafx.graphics;
    requires javafx.controls;
    requires javafx.fxml;
    requires lombok;
    requires ejml.core;
    requires ejml.simple;
    
    opens app to javafx.fxml;
    exports app;
}