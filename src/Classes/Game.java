package Classes;

import Editor.EditorController;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.*;
import java.net.URL;
import java.util.*;

public class Game extends Application {

    private static EditorController editorController;

    private static Stage STAGE;

    public static ArrayList<Room> rooms = new ArrayList<>();
    public static ArrayList<Character> characters = new ArrayList<>();
    public static ArrayList<Item> items = new ArrayList<>();

    /**
     * Main loop
     *
     * @param args
     */
    public static void main(String[] args) {
        //Gestion Sauvegarde
        Sauvegarde.gestionSauvegarde();

        //Initialisation des objets
        initializeObjects(rooms, characters, items);

        //Jeu
        launch(args);

    }

    @Override
    public void start(Stage stage) throws Exception {

        Game.STAGE = stage;
        // Get the Editor Scene and its Controller from the FXML file
        FXMLLoader loader = new FXMLLoader();
        URL resource = getClass().getResource("/Editor/InterfaceProjet.fxml");
        loader.setLocation(resource);
        Scene editorScene = new Scene(loader.load());
        editorController = loader.getController();
        STAGE.setScene(editorScene);
        STAGE.setTitle("Try to Escape");
        STAGE.show();
    }

    public static void initializeObjects(ArrayList<Room> rooms, ArrayList<Character> characters, ArrayList<Item> items) {
        ArrayList<Item> inventoryNull = new ArrayList<>();
        Character player = new Character(
                "AVAST", false, "C'est vous !", true, 1, 6, 1, inventoryNull);
        characters.add(player);
        Character perso1 = new Character(
                "Antivira", false, "Antivira est un anti virus", false, 1, 6, 1, inventoryNull);
        characters.add(perso1);
        Character perso2 = new Character(
                "JigSaw", true, "JigSaw est un virus méchant", false, 1, 4, 1, inventoryNull);
        characters.add(perso2);
        Item item0 = new Item(
                "Bleuvrage", -2, 0, true, false, "Cet objet augmente la limite " +
                "max des dès de 2 pour toujours !");
        items.add(item0);
        Item item1 = new Item(
                "Gourde mystère", 0, -2, true, false, "Cet objet diminue la limite " +
                "max des dès de 2 pour toujours !");
        items.add(item1);
        perso1.getInventory().add(item0);
        perso1.getInventory().add(item1);

        Room room0 = new Room(0, "Salle initiale", "Salle de préparation");
        room0.getItems().add(item0);
        room0.getItems().add(item1);
        room0.getCharacters().add(perso1);
        room0.getCharacters().add(perso2);
        room0.getLockedCharacters().add(perso2);
        rooms.add(room0);

        Room room1 = new Room(1, "Salle infernale", "Description salle infernale");
        rooms.add(room1);

        player.setCurrentRoom(room0);
    }

}