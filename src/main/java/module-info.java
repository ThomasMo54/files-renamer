module com.motompro.filesrenamer {
    requires javafx.controls;
    requires javafx.fxml;
    requires kotlin.stdlib;


    opens com.motompro.filesrenamer to javafx.fxml;
    exports com.motompro.filesrenamer;
}
