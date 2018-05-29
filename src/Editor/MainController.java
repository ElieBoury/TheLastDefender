package Editor;

import Classes.Game;
import Classes.Sauvegarde;
import javafx.animation.FadeTransition;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;
import javafx.util.Duration;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class MainController implements Initializable {

    @FXML
    private BorderPane rootPane;

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
        transitionScene();
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

    private void transitionScene(){
        FadeTransition transition = new FadeTransition();
        transition.setDuration(Duration.millis(1000));
        transition.setNode(rootPane);
        transition.setFromValue(1);
        transition.setToValue(0);
        transition.setOnFinished(e->{
            loadNextScene();
        });
        transition.play();
    }

    private void loadNextScene(){
        try {
            Parent secondView;
            secondView = (BorderPane) FXMLLoader.load(getClass().getResource("/Editor/Interface.fxml"));
            Scene scene2 = new Scene(secondView);
            String css = "/Editor/styleSheet.css";
            scene2.getStylesheets().add(css);
            Stage currentStage = (Stage) rootPane.getScene().getWindow();
            currentStage.setScene(scene2);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}

