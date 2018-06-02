package Editor;

import Classes.Sauvegarde;
import javafx.animation.FadeTransition;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;
import javafx.util.Duration;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class MainController implements Initializable {

    @FXML
    private BorderPane rootPane;

    @Override
    public void initialize(URL url, ResourceBundle rb) {
    }

    /**
     * start a new game
     */
    public void newGameButtonPush(){
        Sauvegarde.importNewGame();
        transitionScene();
    }

    /**
     * start a game
     */
    public void loadGameButtonPush(){
        Sauvegarde.importGame();
        transitionScene();
    }

    /**
     * quit the game
     */
    public void quitButtonPush(){
        System.exit(0);
    }

    /**
     * transition
     */
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

    /**
     * load the nextScene
     */
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

