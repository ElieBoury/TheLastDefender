package Editor;

import java.net.URL;
import java.util.ResourceBundle;

import Classes.*;
import Classes.Character;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;



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

    @FXML
    private TextField textField;

    @FXML
    private Button textFieldOK;

    @FXML
    private Button backButton;

    @FXML
    private TextArea console;

    @FXML
    private Button attackButton;

    @FXML
    private Button useButton;

    @FXML
    private Button runButton;

    @FXML
    private Button releaseButton;

    @FXML
    private Button activateButton;

    @FXML
    private Button knowMoreButton;

    @FXML
    private Button sauvegardeButton;

    private String currentSituation="main";

    int ptsPlayer = 0, ptsIa = 0, rolledPlayer, rolledIA, currentRound = 1, nbRounds;
    Character opponent;

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        startMessage();
    }

    public void sauvegardeButtonPush(){
        Sauvegarde.saveGame();
        console.appendText("Sauvegarde éffectué");
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

    public void lookRoom(){
        Game.characters.get(0).getCurrentRoom().presentRoom(console);
    }

    public void manageSpeak(){
        currentSituation="speak";
        console.appendText("\nAvec qui voulez-vous parler ?\n");
        for(Character perso : Game.characters.get(0).getCurrentRoom().getCharacters()){
            console.appendText("   " + perso.getName() + "\n");
        }
        changeMainButtonVision();
        textField.setVisible(true);
        textFieldOK.setVisible(true);
        backButton.setVisible(true);
    }

    public void manageTake(){
        currentSituation="take";
        console.appendText("\nQuel objet voulez-vous récupérer ?\n");
        for(Item item : Game.characters.get(0).getCurrentRoom().getItems()){
            console.appendText("   " + item.getName() + "\n");
            //characList.getItems().add(perso.getName());
        }
        changeMainButtonVision();
        textField.setVisible(true);
        textFieldOK.setVisible(true);
        backButton.setVisible(true);

    }

    public void managePreviousRoom(){
        if (Game.rooms.indexOf(Game.characters.get(0).getCurrentRoom()) != 0) {
            Game.characters.get(0).setCurrentRoom(Game.rooms.get(Game.rooms.indexOf(Game.characters.get(0).getCurrentRoom()) - 1));
            console.appendText("\nVous venez de changer de salle, observez la bien ...\n");
        } else {
            console.appendText("\nVous êtes dans la première salle,\nil n'y en a pas de précédente !\n");
        }
    }

    public void manageNextRoom(){
        if (Game.characters.get(0).getCurrentRoom().isUnlocked()) {
            if (Game.rooms.indexOf(Game.characters.get(0).getCurrentRoom()) == Game.rooms.size()) {
                console.appendText("\nVous êtes dans la dernière salle,\nil n'y en a pas de suivante !\n");
            } else {
                Game.characters.get(0).setCurrentRoom(Game.rooms.get(Game.rooms.indexOf(Game.characters.get(0).getCurrentRoom()) + 1));
                console.appendText("\nVous venez de changer de salle, observez la bien ...\n");
            }
        } else {
            console.appendText("\nUn ou plusieurs virus bloquent l'accès à la salle suivante,\n" +
                    "faites preuve d'assurance !\n");
        }
    }

    public void changeMainButtonVision(){
        speakButton.setVisible(!speakButton.isVisible());;
        helpButton.setVisible(!helpButton.isVisible());
        takeButton.setVisible(!takeButton.isVisible());
        lookButton.setVisible(!lookButton.isVisible());
        previousButton.setVisible(!previousButton.isVisible());
        nextButton.setVisible(!nextButton.isVisible());
        inventoryButton.setVisible(!inventoryButton.isVisible());
        sauvegardeButton.setVisible(!sauvegardeButton.isVisible());
    }

    public void removeSideButtonsVision(){
        textField.setVisible(false);
        textFieldOK.setVisible(false);
        backButton.setVisible(false);
        attackButton.setVisible(false);
        useButton.setVisible(false);
        runButton.setVisible(false);
        activateButton.setVisible(false);
        knowMoreButton.setVisible(false);
        releaseButton.setVisible(false);
    }

    public void recoverTextField (){
        String textFieldValue = textField.getText();
        switch(currentSituation) {
            case"speak":
                if (Room.containCharac(textFieldValue, Game.characters.get(0).getCurrentRoom().getCharacters())) {
                    if (Room.getCharac(textFieldValue, Game.characters.get(0).getCurrentRoom().getCharacters()).isWicked()) {
                        console.appendText("\n" + Room.getCharac(textFieldValue, Game.characters.get(0).getCurrentRoom().getCharacters()).getName() +
                                " n'aime pas quand on lui parle.." +
                                "\nCela va se régler en combat !");
                        currentSituation="fight";
                        fight(Room.getCharac(textFieldValue, Game.characters.get(0).getCurrentRoom().getCharacters()), 2, console);
                    } else {
                        console.appendText("\n" + textFieldValue +
                                " veut vous aider \nmais ne sait toujours pas comment, revenez plus tard !");
                    }
                } else {
                    console.appendText("\nCe personnage n'est pas dans cette salle.");
                }
                break;
            case"take":
                if (Item.containItem(textFieldValue, Game.characters.get(0).getCurrentRoom().getItems())) {
                    Game.characters.get(0).takeItem(Item.getItem(textFieldValue, Game.characters.get(0).getCurrentRoom().getItems()), console);
                } else {
                    console.appendText("\nCet item n'est pas dans cette salle.\n");
                }
                removeSideButtonsVision();
                changeMainButtonVision();
                break;
            case "fight":
                if (Item.containItem(textFieldValue, Game.characters.get(0).getInventory())) {
                    Game.characters.get(0).activateItem(Item.getItem(textFieldValue, Game.characters.get(0).getInventory()), console);
                } else {
                    console.appendText("Vous ne possédez pas cet objet.\n");
                }
                removeSideButtonsVision();
                attackButton.setVisible(true);
                useButton.setVisible(true);
                runButton.setVisible(true);
                break;
            case "activate":
                if (Item.containItem(textFieldValue, Game.characters.get(0).getInventory())) {
                    Game.characters.get(0).activateItem(Item.getItem(textFieldValue, Game.characters.get(0).getInventory()), console);
                }else {
                    console.appendText("\nVous ne possédez pas cet item.\n");
                }
                removeSideButtonsVision();
                backButton.setVisible(true);
                releaseButton.setVisible(true);
                activateButton.setVisible(true);
                knowMoreButton.setVisible(true);
                break;
            case "knowMore":
                if (Item.containItem(textFieldValue, Game.characters.get(0).getInventory())) {
                    console.appendText(textFieldValue + " : " + Item.getItem(textFieldValue, Game.characters.get(0).getInventory()).getDescription());
                } else {
                    console.appendText("Vous ne possédez pas cet item.\n");
                }
                removeSideButtonsVision();
                backButton.setVisible(true);
                releaseButton.setVisible(true);
                activateButton.setVisible(true);
                knowMoreButton.setVisible(true);
                break;
            case "release":
                if(Item.containItem(textFieldValue, Game.characters.get(0).getInventory())){
                    Game.characters.get(0).getInventory().remove(Item.getItem(textFieldValue, Game.characters.get(0).getInventory()));
                    Game.characters.get(0).getCurrentRoom().getItems().add(Item.getItem(textFieldValue, Game.characters.get(0).getInventory()));
                    console.appendText("Vous venez de relâcher l'objet dans " + Game.characters.get(0).getCurrentRoom().getName() + "\n");
                }else{
                    console.appendText("Vous ne possédez pas cet item\n");
                }
                removeSideButtonsVision();
                backButton.setVisible(true);
                releaseButton.setVisible(true);
                activateButton.setVisible(true);
                knowMoreButton.setVisible(true);
                break;
            default:
                console.appendText("error");
        }
    }

    public void manageBackButton(){
        removeSideButtonsVision();
        changeMainButtonVision();
    }

    /**
     * Manage a fight
     * @param opponent The opponent
     * @param nbRounds The number of rounds needed to win
     */
    public void fight(Character opponent, int nbRounds, TextArea console) {
        this.opponent=opponent;
        this.nbRounds=nbRounds;
        ptsPlayer = 0; ptsIa = 0; currentRound = 1;
        console.appendText("\nCombat entre " +
                Game.characters.get(0).getName() + " et " + opponent.getName() + " en " + nbRounds + " manches !\n");
        console.appendText("Manche " + currentRound + "\n");
        console.appendText(Game.characters.get(0).getName() + " : " + ptsPlayer + " - " + ptsIa + " : " + opponent.getName() + "\n");
        console.appendText("Que voulez vous faire ?\n" +
                "   Attaquer (Attack)\n   Utiliser objet (Use)\n   Fuir (Run Away)\n");
        manageBackButton();
        changeMainButtonVision();
        attackButton.setVisible(true);
        useButton.setVisible(true);
        runButton.setVisible(true);
    }

    public void manageAttack(){
        rolledPlayer = Generator.generateScore(Game.characters.get(0).getLowerDice(), Game.characters.get(0).getUpperDice());
        rolledIA = Generator.generateScore(opponent.getLowerDice(), opponent.getUpperDice());
        console.appendText("Vous avez obtenu " + rolledPlayer + "\n");
        console.appendText(opponent.getName() + " a obtenu " + rolledIA + "\n");
        if (rolledPlayer < rolledIA) {
            console.appendText(opponent.getName() + " gagne cette manche\n");
            ptsIa++;
            currentRound++;
        } else if (rolledPlayer > rolledIA) {
            console.appendText("Vous gagnez cette manche\n");
            ptsPlayer++;
            currentRound++;
        } else {
            console.appendText("Egalité, la défense gagne, " + opponent.getName() + " remporte la manche\n");
            ptsIa++;
            currentRound++;
        }
        console.appendText(Game.characters.get(0).getName() + " : " + ptsPlayer + " - " + ptsIa + " : " + opponent.getName() + "\n");

        if (ptsPlayer > nbRounds / 2) {
            console.appendText("Bravo " + Game.characters.get(0).getName() + ", vous avez gagné !\n");
            if(Game.characters.get(0).getCurrentRoom().containCharac(opponent.getName(), Game.characters.get(0).getCurrentRoom().getCharacters())){
                Game.characters.get(0).getCurrentRoom().removeCharac(opponent.getName(), Game.characters.get(0).getCurrentRoom().getLockedCharacters());
                Game.characters.get(0).getCurrentRoom().getLockedCharacters().remove(opponent);
                console.appendText("Bonne nouvelle ! Vous venez de battre un virus gardien de la salle!\n");
            }else{
                console.appendText("Félicitation, mais " + opponent.getName() +
                        " n'était pas un gardien de la salle.\n");
            }
            if(Game.characters.get(0).getCurrentRoom().getLockedCharacters().isEmpty() && Game.characters.get(0).getCurrentRoom().getLockedItems().isEmpty()){
                Game.characters.get(0).getCurrentRoom().setUnlocked(true);
            }
            manageBackButton();
        } else if( ptsIa > nbRounds/2){
            console.appendText(opponent.getName() + " a gagné ! Vous avez perdu.\n");
            manageBackButton();
        }
    }

    public void manageUse() {
        if (Game.characters.get(0).getInventory().size() != 0) {
            console.appendText("Quel item voulez vous activer ?\n");

            for (Item items : Game.characters.get(0).getInventory()) {
                console.appendText("   " + items.getName() + "\n");
            }
            removeSideButtonsVision();
            textField.setVisible(true);
            textFieldOK.setVisible(true);
            backButton.setVisible(true);
        } else {
            console.appendText("Vous n'avez aucun item.\n");
            removeSideButtonsVision();
            attackButton.setVisible(true);
            useButton.setVisible(true);
            runButton.setVisible(true);
        }
    }

    public void manageRun(){
        console.appendText("Vous avez fuit, combat terminé.\n");
        manageBackButton();
    }

    /**
     * Management of the inventory by the player
     */
    public void manageInventory() {
        if (Game.characters.get(0).getInventory().size() == 0) {
            console.appendText("\nVotre inventaire est vide, récupérez des items !\n");
        } else {
            console.appendText("\nVoici les différents objets présents dans votre inventaire :\n");
            for (Item item : Game.characters.get(0).getInventory()) {
                console.appendText("   " + item.getName() + "\n");
            }
            console.appendText("Vous pouvez :\n" +
                    "   Relâcher un item (release)\n" +
                    "   Activer un item (activate)\n" +
                    "   En savoir plus sur cet objet (know more)\n");
        }
        changeMainButtonVision();
        removeSideButtonsVision();
        backButton.setVisible(true);
        releaseButton.setVisible(true);
        activateButton.setVisible(true);
        knowMoreButton.setVisible(true);
    }

    public void manageRelease(){
        currentSituation="release";
        console.appendText("\nQuel item voulez-vous relâcher ?\n");
        removeSideButtonsVision();
        textFieldOK.setVisible(true);
        textField.setVisible(true);
        backButton.setVisible(true);
    }

    public void manageActivate(){
        currentSituation="activate";
        console.appendText("\nEntrez le nom de l'objet concerné :\n");
        removeSideButtonsVision();
        textFieldOK.setVisible(true);
        textField.setVisible(true);
        backButton.setVisible(true);
    }

    public void manageKnowMore(){
        currentSituation="knowMore";
        console.appendText("\nEntrez le nom de l'objet concerné :\n");
        removeSideButtonsVision();
        textFieldOK.setVisible(true);
        textField.setVisible(true);
        backButton.setVisible(true);
    }

    public void startMessage(){
        console.appendText("\nERREUR ERREUR, VOTRE ORDINATEUR A ETE INFECTE !\n" +
                "Passez de salle en salle et \n" +
                "tuez les virus grâce à des combats de dés !\n" +
                "Bon courage, mais faites vite !\n" +
                "-------------------------------------\n" +
                "Vous êtes dans la " + Game.characters.get(0).getCurrentRoom().getName() + ".\n" +
                "Essayez d'en sortir !\n" +
                "Pour en savoir plus, cliquez sur \"help\".\n");
    }

}
