package Editor;

import Classes.Game;
import Classes.Sauvegarde;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class MainController implements Initializable {

    @FXML
    private Label title;

    @FXML
    private Button newGameButton;

    @FXML
    private Button loadGameButton;

    @FXML
    private Button quitButton;

    @Override
    public void initialize(URL url, ResourceBundle rb) {
    }

    public void newGameButtonPush(ActionEvent event) throws IOException {
        Sauvegarde.importNewGame();
        Parent tableViewParent = FXMLLoader.load(getClass().getResource("Interface.fxml"));
        Scene tableViewScene = new Scene(tableViewParent);

        //This line gets the Stage information
        Stage window = (Stage)((Node)event.getSource()).getScene().getWindow();

        window.setScene(tableViewScene);
        window.show();
    }


    public void loadGameButtonPush(ActionEvent event) throws IOException {
        //Sauvegarde.importGame();
        Parent tableViewParent = FXMLLoader.load(getClass().getResource("InterfaceProjet.fxml"));
        Scene tableViewScene = new Scene(tableViewParent);

        //This line gets the Stage information
        Stage window = (Stage)((Node)event.getSource()).getScene().getWindow();

        window.setScene(tableViewScene);
        window.show();
    }

    public void quitButtonPush(){
        System.exit(0);
    }

}

