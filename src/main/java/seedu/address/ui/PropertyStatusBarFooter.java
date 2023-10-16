package seedu.address.ui;

import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.layout.Region;

import java.nio.file.Path;
import java.nio.file.Paths;

/**
 * A ui for the status bar that is displayed at the footer of the application.
 */
public class PropertyStatusBarFooter extends UiPart<Region> {

    private static final String FXML = "PropertyStatusBarFooter.fxml";

    @FXML
    private Label savePropertyLocationStatus;

    /**
     * Creates a {@code StatusBarFooter} with the given {@code Path}.
     */
    public PropertyStatusBarFooter(Path saveLocation) {
        super(FXML);
        savePropertyLocationStatus.setText(Paths.get(".").resolve(saveLocation).toString());
    }

}
