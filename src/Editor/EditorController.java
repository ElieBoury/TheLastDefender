package Editor;

import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;
import java.util.Scanner;

import Classes.Game;
import Classes.Item;
import Classes.Room;
import Classes.Character;

import javafx.beans.value.ObservableValue;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.input.InputMethodEvent;

import javax.swing.*;


public class EditorController implements Initializable {

    @FXML
    private Button quitButton;

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

    /*@FXML
    private ComboBox<?> characList;

    @FXML
    private ComboBox<?> itemList;

    @FXML
    private Button speak;*/

    @FXML
    private TextField textField;

    @FXML
    private Button textFieldOK;

    @FXML
    private Button backButton;

    @FXML
    private TextArea console;

   // private Character perso;
    private String currentSituation="main";

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        Game.initializeObjects(Game.rooms, Game.characters, Game.items);
        console.appendText("\nERREUR ERREUR, VOTRE ORDINATEUR A ETE INFECTE !\n" +
                "Passez de salle en salle et \n" +
                "tuez les virus grâce à des combats de dés !\n" +
                "Bon courage, mais faites vite !\n" +
                "-------------------------------------\n" +
                "Vous êtes dans la " + Game.characters.get(0).getCurrentRoom().getName() + ".\n" +
                "Essayez d'en sortir !\n" +
                "Pour en savoir plus, cliquez sur \"help\".\n");
        speakButton.setOnAction(e -> manageSpeak(Game.rooms, Game.characters, Game.items));
        helpButton.setOnAction(e -> help());
        quitButton.setOnAction(e -> quit());
        takeButton.setOnAction(e -> manageTake(Game.rooms, Game.characters, Game.items));
        lookButton.setOnAction(e -> Game.characters.get(0).getCurrentRoom().presentRoom(console));
        previousButton.setOnAction(e -> managePreviousRoom(Game.rooms, Game.characters, Game.items));
        nextButton.setOnAction(e -> manageNextRoom(Game.rooms, Game.characters, Game.items));
        inventoryButton.setOnAction(e -> Game.characters.get(0).manageInventory(console));
        textFieldOK.setOnAction(e -> recoverTextField(Game.characters));
        //backButton.setOnAction(e -> );

        /*speak.setOnAction(e -> perso = Character.getCharacter(characList.get));
        characList.setOnAction(e -> speak2());*/
    }

    /**
     * Manage an ask of help
     */
    public void help() {

        console.appendText("\nBut : sortir de cette salle.\n" +
                "Actions possibles :\n" +
                "   \"quit\" : quit le jeu sans sauvegarder\n" +
                "   \"inventory\" : accéder à l'inventaire\n" +
                "   \"take\" : prendre un objet présent dans la salle\n" +
                "   \"speak\" : parler à un personnage présent dans la salle\n" +
                "   \"look room\" : regarder ce qu'il y a dans la salle\n" +
                "   \"previous room\" : aller à la salle précédente\n" +
                "   \"next room\" : aller à la salle suivante\n");
    }

    public void quit() {
        console.appendText("\nAu revoir !");
        System.exit(0);
    }

    public void manageSpeak(ArrayList<Room> rooms, ArrayList<Character> characters, ArrayList<Item> items){
        //Scanner sc = new Scanner(System.in);
        currentSituation="speak";
        console.appendText("\nAvec qui voulez-vous parler ?\n");
        for(Character perso : characters.get(0).getCurrentRoom().getCharacters()){
            console.appendText("   " + perso.getName() + "\n");
            //characList.getItems().add(perso.getName());
        }
        //persoListe.getItems()..add();
        /*changeMainButtonVision(false);
        characList.setVisible(true);
        if(characList.getSelectionModel().toString()=="Antivira"){
            console.appendText("success");
        }*/
        changeMainButtonVision(false);
        textField.setVisible(true);
        textFieldOK.setVisible(true);
        textFieldOK.isPressed();

    }

    public void manageTake(ArrayList<Room> rooms, ArrayList<Character> characters, ArrayList<Item> items){
        currentSituation="take";
        Scanner sc = new Scanner(System.in);
        console.appendText("\nQuel objet voulez-vous récupérer ?\n");
        String wordRead = sc.nextLine();
        if (Item.containItem(wordRead, rooms.get(0).getItems())) {
            characters.get(0).takeItem(Item.getItem(wordRead, rooms.get(0).getItems()), console);
        } else {
            console.appendText("Cet item n'est pas dans cette salle.\n");

        }
    }

    public void managePreviousRoom(ArrayList<Room> rooms, ArrayList<Character> characters, ArrayList<Item> items){
        if (rooms.indexOf(characters.get(0).getCurrentRoom()) != 0) {
            characters.get(0).setCurrentRoom(rooms.get(rooms.indexOf(characters.get(0).getCurrentRoom()) - 1));
            console.appendText("\nVous venez de changer de salle, observez la bien ...\n");
        } else {
            console.appendText("\nVous êtes dans la première salle,\nil n'y en a pas de précédente !\n");
        }
    }

    public void manageNextRoom(ArrayList<Room> rooms, ArrayList<Character> characters, ArrayList<Item> items){
        if (characters.get(0).getCurrentRoom().isUnlocked()) {
            if (rooms.indexOf(characters.get(0).getCurrentRoom()) == rooms.size()) {
                characters.get(0).setCurrentRoom(rooms.get(rooms.indexOf(characters.get(0).getCurrentRoom()) + 1));
                console.appendText("\nVous venez de changer de salle, observez la bien ...\n");
            } else {
                console.appendText("\nVous êtes dans la dernière salle,\nil n'y en a pas de suivante !\n");
            }
        } else {
            console.appendText("\nUn ou plusieurs virus bloquent l'accès à la salle suivante,\n" +
                    "faites preuve d'assurance !\n");
        }
    }

    public void changeMainButtonVision(boolean a){
        if(a){
            speakButton.setVisible(true);
            helpButton.setVisible(true);
            takeButton.setVisible(true);
            lookButton.setVisible(true);
            previousButton.setVisible(true);
            nextButton.setVisible(true);
            inventoryButton.setVisible(true);
        }else {
            speakButton.setVisible(false);
            helpButton.setVisible(false);
            takeButton.setVisible(false);
            lookButton.setVisible(false);
            previousButton.setVisible(false);
            nextButton.setVisible(false);
            inventoryButton.setVisible(false);
        }
    }

    public void recoverTextField (ArrayList<Character> characters){
        switch(currentSituation) {
            case"speak":
            String textFieldValue = textField.getText();
            if (Room.containCharac(textFieldValue, characters.get(0).getCurrentRoom().getCharacters())) {
                if (Room.getCharac(textFieldValue, characters.get(0).getCurrentRoom().getCharacters()).isWicked()) {
                    console.appendText("\n" + Room.getCharac(textFieldValue, characters.get(0).getCurrentRoom().getCharacters()).getName() +
                            " n'aime pas quand on lui parle..\n" +
                            "\nCela va se régler en combat !");
                    characters.get(0).fight(Room.getCharac(textFieldValue, characters.get(0).getCurrentRoom().getCharacters()), 2, console);
                } else {
                    console.appendText("\n" + Room.getCharac(textFieldValue, characters.get(0).getCurrentRoom().getCharacters()).getName() +
                            " veut vous aider mais ne sait toujours pas comment, revenez plus tard !");
                }
            } else {
                console.appendText("\nCe personnage n'est pas dans cette salle.");
            }
            break;
            case"take":
                console.appendText("in coming...");
                break;
            default:
                console.appendText("error");
        }
    }

    public void setMainButton(){
        changeMainButtonVision(true);

    }

    /*public void speak2 () {
        final String okk;
        characList.getSelectionModel().selectedIndexProperty().addListener(
                (ObservableValue<? extends Number> obsValue) -> okk = Game.characters.get(((int)obsValue)+1).getName());/*, Number oldValue, Number newValue) ->
                        console.appendText("\nvs avez cliqué sur " + Game.characters.get(((int)newValue)+1).getName()
                                + "et avant il y avait " + Game.characters.get(((int)oldValue)+1).getName())
        );
    }*/

}
