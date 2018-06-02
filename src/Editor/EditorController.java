package Editor;

import java.net.URL;
import java.util.ResourceBundle;
import Classes.*;
import Classes.Character;

import javafx.animation.FadeTransition;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.layout.BorderPane;
import javafx.util.Duration;


public class EditorController implements Initializable {

    @FXML
    private BorderPane rootPane;

    @FXML
    private ButtonBar ButtonBar;

    @FXML
    private Button speakButton;

    @FXML
    private Button takeButton;

    @FXML
    private Button inventoryButton;

    @FXML
    private Button lookButton;

    @FXML
    private Button previousButton;

    @FXML
    private Button nextButton;

    @FXML
    private ComboBox comboBox;

    @FXML
    private Button releaseButton;

    @FXML
    private Button attackButton;

    @FXML
    private Button knowMoreButton;

    @FXML
    private Button useButton;

    @FXML
    private Button activateButton;

    @FXML
    private Button backButton;

    @FXML
    private Button comboxOK;

    @FXML
    private Button runButton;

    @FXML
    private Button saveButton;

    @FXML
    private Button helpButton;

    @FXML
    private Button quitButton;

    @FXML
    private Label roomText;

    @FXML
    private Label characterText;

    @FXML
    private Label objetText;

    @FXML
    private TextArea console;

    private String currentSituation="main";

    private String choixItem="";

    private String choixUtilisateur="";

    int ptsPlayer = 0, ptsIa = 0, rolledPlayer, rolledIA, currentRound = 1, nbRounds;
    Character opponent;

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        transitionScene();
        roomText.setText(Game.characters.get(0).getCurrentRoom().getName());
        characterText.setText(Game.characters.get(0).getCurrentRoom().getCharacters().toString());
        objetText.setText(Game.characters.get(0).getCurrentRoom().getItems().toString());
        startMessage();
        comboBox.setVisibleRowCount(3);
        for (Character c : Game.characters){
            System.out.println(c.getName());
            System.out.println(c.getInventory());
        }
    }

    /**
     * Manage saving
     */
    public void sauvegardeButtonPush(){
        Sauvegarde.saveGame();
        console.appendText("Sauvegarde effectuée\n");
    }


    /**
     * Manage an ask of help
     */
    public void help() throws InterruptedException {
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

    /**
     * Manage leaving the game
     */
    public void quit() {
        console.appendText("\nAu revoir !");
        System.exit(0);
    }

    /**
     * Manage looking a room
     */
    public void lookRoom(){
        Game.characters.get(0).getCurrentRoom().presentRoom(console);
    }

    /**
     * Manage first step of speaking with a character
     */
    public void manageSpeak(){
        loadCharacter();
        currentSituation="speak";
        console.appendText("\nAvec qui voulez-vous parler ?\n");
        for(Character perso : Game.characters.get(0).getCurrentRoom().getCharacters()){
            console.appendText("   " + perso.getName() + "\n");
        }
        changeMainButtonVision();
        comboBox.setVisible(true);
        ButtonBar.setVisible(true);
    }

    /**
     * Manage first step of taking an item
     */
    public void manageTake(){
        if(Game.characters.get(0).getCurrentRoom().getItems().size()==0){
            console.appendText("La salle ne contient pas d'objet !");
        }else{
            loadItem();
            currentSituation="take";
            console.appendText("\nQuel objet voulez-vous récupérer ?\n");
            for(Item item : Game.characters.get(0).getCurrentRoom().getItems()){
                console.appendText("   " + item.getName() + "\n");
            }
            changeMainButtonVision();
            comboBox.setVisible(true);
            ButtonBar.setVisible(true);
        }
    }

    /**
     * Manage an ask of going to the previous room
     */
    public void managePreviousRoom(){
        if (Game.rooms.indexOf(Game.characters.get(0).getCurrentRoom()) != 0) {
            Game.characters.get(0).setCurrentRoom(Game.rooms.get(Game.rooms.indexOf(
                    Game.characters.get(0).getCurrentRoom()) - 1));
            console.appendText("\nVous venez de changer de salle, observez la bien ...\n");
        } else {
            console.appendText("\nVous êtes dans la première salle,\nil n'y en a pas de précédente !\n");
        }
    }

    /**
     * Manage an ask of going to the next room
     */
    public void manageNextRoom(){
        if (Game.characters.get(0).getCurrentRoom().isUnlocked()) {
            if (Game.rooms.indexOf(Game.characters.get(0).getCurrentRoom()) == Game.rooms.size()) {
                console.appendText("\nVous êtes dans la dernière salle,\nil n'y en a pas de suivante !\n");
            } else {
                Game.characters.get(0).setCurrentRoom(Game.rooms.get(Game.rooms.indexOf(
                        Game.characters.get(0).getCurrentRoom()) + 1));
                console.appendText("\nVous venez de changer de salle, observez la bien ...\n");
            }
        } else {
            console.appendText("\nDes virus (ou peut-être autre chose) bloquent l'accès à la salle suivante,\n" +
                    "faites preuve d'assurance !\n");
        }
    }

    /**
     * Change main buttons vision
     */
    public void changeMainButtonVision(){
        speakButton.setVisible(!speakButton.isVisible());;
        helpButton.setVisible(!helpButton.isVisible());
        takeButton.setVisible(!takeButton.isVisible());
        lookButton.setVisible(!lookButton.isVisible());
        previousButton.setVisible(!previousButton.isVisible());
        nextButton.setVisible(!nextButton.isVisible());
        inventoryButton.setVisible(!inventoryButton.isVisible());
        saveButton.setVisible(!saveButton.isVisible());
    }

    /**
     * Make side buttons unvisible
     */
    public void removeSideButtonsVision(){
        comboBox.setVisible(false);
        comboxOK.setDisable(false);
        ButtonBar.setVisible(false);
        attackButton.setVisible(false);
        useButton.setVisible(false);
        runButton.setVisible(false);
        activateButton.setVisible(false);
        knowMoreButton.setVisible(false);
        releaseButton.setVisible(false);
    }

    /**
     * Recover the value of the textField according to the currentSituatuion and manage in consequence
     */
    public void recoverTextField () {
        comboBoxUdapte();
        manageGameOver();
        switch(currentSituation) {
            case"speak":
                if (Room.containCharac(choixUtilisateur, Game.characters.get(0).getCurrentRoom().getCharacters())) {
                    if (Room.getCharac(choixUtilisateur, Game.characters.get(0).getCurrentRoom().
                            getCharacters()).isWicked()) {
                        console.appendText("\n" + Room.getCharac(choixUtilisateur,
                                Game.characters.get(0).getCurrentRoom().getCharacters()).getDialogue() +
                                "\nCela va se régler en combat !");
                        currentSituation="fight";
                        fight(Room.getCharac(choixUtilisateur, Game.characters.get(0).getCurrentRoom().getCharacters()),
                                2, console);
                    } else {
                        console.appendText("\n" + Room.getCharac(choixUtilisateur,
                                Game.characters.get(0).getCurrentRoom().getCharacters()).getDialogue());
                        if(!Room.getCharac(choixUtilisateur, Game.characters.get(0).getCurrentRoom().getCharacters())
                                .getInventory().isEmpty()){
                            console.appendText("\nVoilà de quoi t'aider pour ta mission !\n" +
                                    "Vous venez d'obtenir un " +
                                    Room.getCharac(choixUtilisateur, Game.characters.get(0).getCurrentRoom().
                                            getCharacters()).getInventory().get(0).getName());
                            Game.characters.get(0).getInventory().add(Room.getCharac(choixUtilisateur,
                                    Game.characters.get(0).getCurrentRoom().getCharacters()).getInventory().get(0));
                            Room.getCharac(choixUtilisateur, Game.characters.get(0).getCurrentRoom().getCharacters()).
                                    getInventory().remove(0);
                        }
                    }
                } else {
                    console.appendText("\nCe personnage n'est pas dans cette salle.");
                }
                break;
            case"take":
                if (Item.containItem(choixUtilisateur, Game.characters.get(0).getCurrentRoom().getItems())) {
                    Game.characters.get(0).takeItem(Item.getItem(choixUtilisateur,
                            Game.characters.get(0).getCurrentRoom().getItems()), console);
                    if(Item.containItem(choixUtilisateur, Game.characters.get(0).getCurrentRoom().getLockedItems())) {
                        Game.characters.get(0).getCurrentRoom().removeItem(choixUtilisateur,
                                Game.characters.get(0).getCurrentRoom().getItems());
                        Game.characters.get(0).getCurrentRoom().getLockedItems().remove(
                                Item.getItem(choixUtilisateur, Game.characters.get(0).getCurrentRoom().getItems()));
                        console.appendText("Bonne nouvelle ! " +
                                "Vous venez de déposer un objet nécessaire au débloquage de la salle !\n");
                    }
                    if(Game.characters.get(0).getCurrentRoom().getLockedCharacters().isEmpty() &&
                            Game.characters.get(0).getCurrentRoom().getLockedItems().isEmpty()){
                        Game.characters.get(0).getCurrentRoom().setUnlocked(true);
                    }

                } else {
                    console.appendText("\nCet item n'est pas dans cette salle.\n");
                }
                removeSideButtonsVision();
                changeMainButtonVision();
                break;
            case "fight":
                if (Item.containItem(choixUtilisateur, Game.characters.get(0).getInventory())) {
                    Game.characters.get(0).activateItem(Item.getItem(choixUtilisateur,
                            Game.characters.get(0).getInventory()), console, "all");
                } else {
                    console.appendText("Vous ne possédez pas cet objet.\n");
                }
                removeSideButtonsVision();
                attackButton.setVisible(true);
                useButton.setVisible(true);
                runButton.setVisible(true);
                break;
            case "activate":
                if (Item.containItem(choixUtilisateur, Game.characters.get(0).getInventory())) {
                    Game.characters.get(0).activateItem(Item.getItem(choixUtilisateur,
                            Game.characters.get(0).getInventory()), console, "all");
                }else {
                    console.appendText("\nVous ne possédez pas cet item.\n");
                }
                ButtonBar.setVisible(true);
                removeSideButtonsVision();
                releaseButton.setVisible(true);
                activateButton.setVisible(true);
                knowMoreButton.setVisible(true);
                break;
            case "knowMore":
                if (Item.containItem(choixUtilisateur, Game.characters.get(0).getInventory())) {
                    console.appendText(choixUtilisateur + " : " + Item.getItem(choixUtilisateur,
                            Game.characters.get(0).getInventory()).getDescription());
                } else {
                    console.appendText("Vous ne possédez pas cet item.\n");
                }
                removeSideButtonsVision();
                ButtonBar.setVisible(true);
                releaseButton.setVisible(true);
                activateButton.setVisible(true);
                knowMoreButton.setVisible(true);
                break;
            case "release":
                if(Item.containItem(choixUtilisateur, Game.characters.get(0).getInventory())){
                    Game.characters.get(0).getInventory().remove(Item.getItem(choixUtilisateur,
                            Game.characters.get(0).getInventory()));
                    Game.characters.get(0).getCurrentRoom().getItems().add(Item.getItem(choixUtilisateur, Game.items));
                    console.appendText("Vous venez de relâcher l'objet dans " +
                            Game.characters.get(0).getCurrentRoom().getName() + "\n");
                    if(Item.containItem(choixUtilisateur,Game.characters.get(0).getCurrentRoom().getLockedItems())){
                        Game.characters.get(0).getCurrentRoom().getItems().add(Item.getItem(
                                choixUtilisateur, Game.characters.get(0).getCurrentRoom().getItems()));
                        Game.characters.get(0).getCurrentRoom().getLockedItems().remove(choixUtilisateur);
                        console.appendText("Bonne nouvelle ! " +
                                "Vous venez de déposer un objet déverrouillant la salle !\n");
                        if(Game.characters.get(0).getCurrentRoom().getLockedCharacters().isEmpty() &&
                                Game.characters.get(0).getCurrentRoom().getLockedItems().isEmpty()){
                            Game.characters.get(0).getCurrentRoom().setUnlocked(true);
                            console.appendText("\nBravo ! " +
                                    "Vous avez nettoyé la salle, plus rien n'en bloque la sortie !");
                        }
                    }
                }else{
                    console.appendText("Vous ne possédez pas cet item\n");
                }
                removeSideButtonsVision();
                ButtonBar.setVisible(true);
                releaseButton.setVisible(true);
                activateButton.setVisible(true);
                knowMoreButton.setVisible(true);
                break;
            default:
                console.appendText("error");
        }
    }

    /**
     * Set buttons vision after a backButton
     */
    public void manageBackButton(){
        removeSideButtonsVision();
        changeMainButtonVision();
    }

    /**
     * Manage a fight
     * @param opponent The opponent
     * @param nbRounds The number of rounds needed to win
     * @param console where the text is showed
     */
    public void fight(Character opponent, int nbRounds, TextArea console) {
        this.opponent=opponent;
        this.nbRounds=nbRounds;
        ptsPlayer = 0; ptsIa = 0; currentRound = 1;
        console.appendText("\nCombat entre " +
                Game.characters.get(0).getName() + " et " + opponent.getName() + " en " + nbRounds + " manches !\n");
        console.appendText("Manche " + currentRound + "\n");
        console.appendText(Game.characters.get(0).getName() + " : " + ptsPlayer + " - " + ptsIa + " : " +
                opponent.getName() + "\n");
        console.appendText("Que voulez vous faire ?\n" +
                "   Attaquer (Attack)\n   Utiliser objet (Use)\n   Fuir (Run Away)\n");
        manageBackButton();
        changeMainButtonVision();
        attackButton.setVisible(true);
        useButton.setVisible(true);
        runButton.setVisible(true);
    }

    /**
     * Manage a current fight
     */
    public void manageAttack(){
        rolledPlayer = Generator.generateScore(Game.characters.get(0).getLowerDice(),
                Game.characters.get(0).getUpperDice(), Game.characters.get(0).getNbDice());
        rolledIA = Generator.generateScore(opponent.getLowerDice(), opponent.getUpperDice(), opponent.getNbDice());
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
        console.appendText(Game.characters.get(0).getName() + " : " + ptsPlayer + " - " + ptsIa + " : " +
                opponent.getName() + "\n");

        if (ptsPlayer > nbRounds / 2) {
            console.appendText("Bravo " + Game.characters.get(0).getName() + ", vous avez gagné !\n");
            console.appendText(opponent.getDescription());
            console.appendText("\nGrâce à ce combat :");
            Game.characters.get(0).activateItem(opponent.getInventory().get(0), console, "bonus");
            if(Game.characters.get(0).getCurrentRoom().containCharac(opponent.getName(),
                    Game.characters.get(0).getCurrentRoom().getLockedCharacters())){
                Game.characters.get(0).getCurrentRoom().removeCharac(opponent.getName(),
                        Game.characters.get(0).getCurrentRoom().getCharacters());
                Game.characters.get(0).getCurrentRoom().getLockedCharacters().remove(opponent);
                console.appendText("Bonne nouvelle ! Vous venez de battre un virus gardien de la salle!\n");
            }else{
                console.appendText("Félicitation, mais " + opponent.getName() +
                        " n'était pas un gardien de la salle.\n");
            }
            if(Game.characters.get(0).getCurrentRoom().getLockedCharacters().isEmpty() &&
                    Game.characters.get(0).getCurrentRoom().getLockedItems().isEmpty()){
                Game.characters.get(0).getCurrentRoom().setUnlocked(true);
                console.appendText("\nBravo ! Vous avez nettoyé la salle, plus rien n'en bloque la sortie !");
            }
            manageBackButton();
        } else if( ptsIa > nbRounds/2){
            console.appendText(opponent.getName() + " a gagné ! Vous avez perdu.\n");
            console.appendText("\nA cause de ce combat :");
            Game.characters.get(0).activateItem(opponent.getInventory().get(0), console, "malus");
            manageBackButton();
        }
    }

    /**
     * Manage a using of an item during a fight
     */
    public void manageUse() {
        loadInventory();
        if (Game.characters.get(0).getInventory().size() != 0) {
            console.appendText("Quel item voulez vous activer ?\n");

            for (Item items : Game.characters.get(0).getInventory()) {
                console.appendText("   " + items.getName() + "\n");
            }
            removeSideButtonsVision();
            ButtonBar.setVisible(true);
            comboBox.setVisible(true);
        } else {
            console.appendText("Vous n'avez aucun item.\n");
            removeSideButtonsVision();
            attackButton.setVisible(true);
            useButton.setVisible(true);
            runButton.setVisible(true);
        }
    }

    /**
     * Manage a run out of a fight
     */
    public void manageRun(){
        console.appendText("Vous avez fuit, combat terminé.\n");
        console.appendText("\nA cause de ce combat :");
        Game.characters.get(0).activateItem(opponent.getInventory().get(0), console, "malus");
        manageBackButton();
    }

    /**
     * Management of the inventory by the player
     */
    public void manageInventory() {
        if (Game.characters.get(0).getInventory().size() == 0) {
            console.appendText("\nVotre inventaire est vide, récupérez des items !\n");
            releaseButton.setDisable(true);
            activateButton.setDisable(true);
            knowMoreButton.setDisable(true);
        } else {
            releaseButton.setDisable(false);
            activateButton.setDisable(false);
            knowMoreButton.setDisable(false);
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
        ButtonBar.setVisible(true);
        comboxOK.setDisable(true);
        releaseButton.setVisible(true);
        activateButton.setVisible(true);
        knowMoreButton.setVisible(true);
    }

    /**
     * Manage a release of an item
     */
    public void manageRelease(){
        loadInventory();
        currentSituation="release";
        console.appendText("\nQuel item voulez-vous relâcher ?\n");
        removeSideButtonsVision();
        ButtonBar.setVisible(true);
        comboBox.setVisible(true);
    }

    /**
     * Manage a activation of an item
     */
    public void manageActivate(){
        loadInventory();
        currentSituation="activate";
        console.appendText("\nEntrez le nom de l'objet concerné :\n");
        removeSideButtonsVision();
        comboBox.setVisible(true);
        ButtonBar.setVisible(true);
    }

    /**
     * Manage an ask of explication on an item
     */
    public void manageKnowMore(){
        loadInventory();
        currentSituation="knowMore";
        console.appendText("\nEntrez le nom de l'objet concerné :\n");
        removeSideButtonsVision();
        comboBox.setVisible(true);
        ButtonBar.setVisible(true);
    }

    /**
     * Show the first message
     */
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

    /**
     * Manage the Game Over
     */
    public void manageGameOver() {
        if(Game.characters.get(0).getNbDice()==0){
            disableMainButton();
            console.appendText("---GAME OVER---");
        }
    }

    public void disableMainButton(){
        speakButton.setDisable(true);
        takeButton.setDisable(true);
        inventoryButton.setDisable(true);
        lookButton.setDisable(true);
        nextButton.setDisable(true);
        previousButton.setDisable(true);
        helpButton.setDisable(true);
        saveButton.setDisable(true);
    }

    public void loadInventory(){
        comboBox.getItems().clear();
        comboBox.setEditable(false);
        comboBox.setPromptText("Choose Item");
        for (Item c : Game.characters.get(0).getInventory()){
            comboBox.getItems().add(c.getName());
        }
    }

    public void loadItem(){
        comboBox.getItems().clear();
        comboBox.setEditable(false);
        comboBox.setPromptText("Choose Item");
        for (Item c : Game.characters.get(0).getCurrentRoom().getItems()){
            comboBox.getItems().add(c.getName());
        }
    }

    public void loadCharacter(){
        comboBox.getItems().clear();
        comboBox.setEditable(false);
        comboBox.setPromptText("Choose character");
        for (Character c : Game.characters.get(0).getCurrentRoom().getCharacters()){
            comboBox.getItems().add(c.getName());
        }
    }

    private void transitionScene(){
        FadeTransition transition = new FadeTransition();
        transition.setDuration(Duration.millis(1000));
        transition.setNode(rootPane);
        transition.setFromValue(0);
        transition.setToValue(1);
        transition.play();
    }

    public void comboBoxUdapte(){
        try {
            choixUtilisateur = comboBox.getValue().toString();
            System.out.println(comboBox.getValue().toString());
        }catch (Exception e){
            System.out.println("Choose a value");
        }
    }

}
