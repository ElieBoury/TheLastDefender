package Editor;

import java.net.URL;
import java.util.ArrayList;
import java.util.Collection;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.paint.Color;

public class EditorController implements Initializable {

    @FXML
    Button button;

    @Override
    @SuppressWarnings("unchecked")
    public void initialize(URL url, ResourceBundle rb) {

        button.setOnAction(e -> System.out.println("salut"));

    }

    public void quit() {
        System.out.println("Au revoir !");
        System.exit(0);
    }
}
