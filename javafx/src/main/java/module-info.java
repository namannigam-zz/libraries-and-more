module javafx {
    exports com.sample;
    requires javafx.base;
    requires javafx.graphics;
//    requires javafx.swing;
    requires transitive javafx.controls;
    requires javafx.fxml;
}