package Classes;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.util.*;

public class Game extends Application {

    public static ArrayList<Room> rooms = new ArrayList<>();
    public static ArrayList<Character> characters = new ArrayList<>();
    public static ArrayList<Item> items = new ArrayList<>();

    /**
     * Main loop
     * @param args
     */
    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage stage) throws Exception {

        Parent root = FXMLLoader.load(getClass().getResource("/Editor/Main.fxml"));

        Scene scene = new Scene(root);

        String css = "/Editor/styleSheet.css";
        scene.getStylesheets().add(css);

        stage.setScene(scene);
        stage.show();
    }



}