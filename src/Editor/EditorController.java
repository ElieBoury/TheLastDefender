package Editor;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;

public class EditorController implements Initializable {

    @FXML
    Button quitButton;

    @Override
    public void initialize(URL url, ResourceBundle rb) {

        quitButton.setOnAction(e -> quit());

    }

    public void quit() {
        System.out.println("Au revoir !");
        System.exit(0);
    }
}
