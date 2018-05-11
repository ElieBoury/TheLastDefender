package Editor;

import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;
import java.util.Scanner;

import Classes.Game;
import Classes.Item;
import Classes.Room;
import Classes.Character;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.TextArea;


public class EditorController implements Initializable {



    @FXML
    Button quitButton;

    @FXML
    private Button helpButton;

    @FXML
    private Button inventoryButton;

    @FXML
    private Button speakButton;

    @FXML
    private Button takeButton;

    @FXML
    private Button lookButton;

    @FXML
    private Button previousButton;

    @FXML
    private Button nextButton;

    @FXML
    private TextArea console;


    @Override
    public void initialize(URL url, ResourceBundle rb) {
        Game.initializeObjects(Game.rooms, Game.characters, Game.items);
        console.setText("ERREUR ERREUR, VOTRE ORDINATEUR A ETE INFECTE !\n" +
                "Passez de salle en salle et \n" +
                "tuez les virus grâce à des combats de dés !\n" +
                "Bon courage, mais faites vite !\n" +
                "-------------------------------------\n" +
                "Vous êtes dans la " + Game.characters.get(0).getCurrentRoom().getName() + ".\n" +
                "Essayez d'en sortir !\n" +
                "Pour en savoir plus sur les commandes, cliquez sur \"help\".");
        speakButton.setOnAction(e -> manageSpeak(Game.rooms, Game.characters, Game.items));
        helpButton.setOnAction(e -> help());
        quitButton.setOnAction(e -> quit());
        takeButton.setOnAction(e -> manageTake(Game.rooms, Game.characters, Game.items));
        lookButton.setOnAction(e -> Game.characters.get(0).getCurrentRoom().presentRoom());
        previousButton.setOnAction(e -> managePreviousRoom(Game.rooms, Game.characters, Game.items));
        nextButton.setOnAction(e -> manageNextRoom(Game.rooms, Game.characters, Game.items));
        inventoryButton.setOnAction(e -> Game.characters.get(0).manageInventory());
    }

    /**
     * Manage an ask of help
     */
    public void help() {

        console.setText("But : sortir de cette salle.\n" +
                "Actions possibles :\n" +
                "   \"quit\" : quit le jeu sans sauvegarder\n" +
                "   \"inventory\" : accéder à l'inventaire\n" +
                "   \"take\" : prendre un objet présent dans la salle\n" +
                "   \"speak\" : parler à un personnage présent dans la salle\n" +
                "   \"look room\" : regarder ce qu'il y a dans la salle\n" +
                "   \"previous room\" : aller à la salle précédente\n" +
                "   \"next room\" : aller à la salle suivante");
    }

    public void quit() {
        console.setText("Au revoir !");
        System.exit(0);
    }

    public void manageSpeak(ArrayList<Room> rooms, ArrayList<Character> characters, ArrayList<Item> items){
        //Scanner sc = new Scanner(System.in);
        console.setText("Avec qui voulez-vous parler ?\");
        for(Character perso : characters.get(0).getCurrentRoom().getCharacters()){
            console.appendText(perso.getName());
        }
        /*String wordRead = sc.nextLine();
        if (Room.containCharac(wordRead, rooms.get(0).getCharacters())) {
            if (Room.getCharac(wordRead, rooms.get(0).getCharacters()).isWicked()) {
                console.appendText(Room.getCharac(wordRead, rooms.get(0).getCharacters()).getName() +
                        " n'aime pas quand on lui parle..\n" +
                        "Cela va se régler en combat !");
                characters.get(0).fight(Room.getCharac(wordRead, rooms.get(0).getCharacters()), 2);
            } else {
                console.appendText(Room.getCharac(wordRead, rooms.get(0).getCharacters()).getName() +
                        " veut vous aider mais ne sait toujours pas comment, revenez plus tard !");
            }
        } else {
            console.appendText("Ce personnage n'est pas dans cette salle.");
        }*/
    }

    public void manageTake(ArrayList<Room> rooms, ArrayList<Character> characters, ArrayList<Item> items){
        Scanner sc = new Scanner(System.in);
        console.appendText("Quel objet voulez-vous récupérer ?");
        String wordRead = sc.nextLine();
        if (Item.containItem(wordRead, rooms.get(0).getItems())) {
            characters.get(0).takeItem(Item.getItem(wordRead, rooms.get(0).getItems()));
        } else {
            console.appendText("Cet item n'est pas dans cette salle.");

        }
    }

    public void managePreviousRoom(ArrayList<Room> rooms, ArrayList<Character> characters, ArrayList<Item> items){
        if (rooms.indexOf(characters.get(0).getCurrentRoom()) != 0) {
            characters.get(0).setCurrentRoom(rooms.get(rooms.indexOf(characters.get(0).getCurrentRoom()) - 1));
            console.appendText("Vous venez de changer de salle, observez la bien ...");
        } else {
            console.appendText("Vous êtes dans la première salle, il n'y en a pas de précédente !");
        }
    }

    public void manageNextRoom(ArrayList<Room> rooms, ArrayList<Character> characters, ArrayList<Item> items){
        if (characters.get(0).getCurrentRoom().isUnlocked()) {
            if (rooms.indexOf(characters.get(0).getCurrentRoom()) == rooms.size()) {
                characters.get(0).setCurrentRoom(rooms.get(rooms.indexOf(characters.get(0).getCurrentRoom()) + 1));
                console.appendText("Vous venez de changer de salle, observez la bien ...");
            } else {
                console.appendText("Vous êtes dans la dernière salle, il n'y en a pas de suivante !");
            }
        } else {
            console.appendText("Un ou plusieurs virus bloquent l'accès à la salle suivante,\n" +
                    "faites preuve d'assurance !");
        }
    }
}
