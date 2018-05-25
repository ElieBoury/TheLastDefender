package Editor;


import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;

import java.net.URL;
import java.util.ResourceBundle;

public class MainController {

    @FXML
    private Label title;

    @FXML
    private Button newGameButton;

    @FXML
    private Button loadGameButton;

    @FXML
    private Button quitButton;

    public void initialize(URL url, ResourceBundle rb) {

    }

    public void newGameButtonPush(){}

    public void loadGameButton(){}

    public void quitButtonPush(){
        System.exit(0);
    }

}

