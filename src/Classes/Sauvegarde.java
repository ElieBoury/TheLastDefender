package Classes;

import java.io.*;

public class Sauvegarde {

    final private static String DefaultPathCharacter = "src/Sauvegarde/Default/Character.csv";
    final private static String DefaultPathItem = "src/Sauvegarde/Default/Item.csv";
    final private static String DefaultPathRoom = "src/Sauvegarde/Default/Room.csv";

    final private static String PathCharacter = "src/Sauvegarde/Character.csv";
    final private static String PathItem = "src/Sauvegarde/Item.csv";
    final private static String PathRoom = "src/Sauvegarde/Room.csv";


    /**
     * To save a game
     */
    public static void saveGame(){
        sauvegardeItems(PathItem);
        sauvegardeCharacter(PathCharacter);
        sauvegardeRoom(PathRoom);
    }

    /**
     * To import a new game from default CSV
     */
    public static void importNewGame(){
        importItem(DefaultPathItem);
        importCharacter(DefaultPathCharacter);
        importRoom(DefaultPathRoom);
        Game.characters.get(0).setCurrentRoom(Game.rooms.get(0));
    }

    /**
     * To import a game from CSV
     */
    public static void importGame(){
        importItem(PathItem);
        importCharacter(PathCharacter);
        importRoom(PathRoom);
        Game.characters.get(0).setCurrentRoom(Game.rooms.get(0));
    }

    /**
     * create the CSV with game's characters to implement Character.csv
     * @param path where is the CSV
     */
    public static void sauvegardeCharacter(String path) {
        BufferedWriter myFile = null;
        try {
            myFile = new BufferedWriter(new FileWriter(new File(path)));
            myFile.write("Name;Wicked;Description;Player;LowerDice;UpperDice;nbDice;Items;");
            myFile.newLine();
            for (Character myCharacter: Game.characters) {
                myFile.write(myCharacter.characterToCSV());
                myFile.newLine();
            }
            myFile.close();
        } catch (FileNotFoundException e) {
            e.toString();
            System.out.println("Wrong path");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * create the CSV with game's items to implement item.csv
     * @param path where is the CSV
     */
    public static void sauvegardeItems(String path) {
        BufferedWriter myFile = null;
        try {
            myFile = new BufferedWriter(new FileWriter(new File(path)));

            myFile.write("Name;Bonus;Malus;toActivate;Taken;Description;");
            myFile.newLine();
            for (Item myItem: Game.items) {
                myFile.write(myItem.itemToCSV());
                myFile.newLine();
            }
            myFile.close();
        } catch (FileNotFoundException e) {
            e.toString();
            System.out.println("Wrong path");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * create the CSV with game's room to implement room.csv
     * @param path where is the CSV
     */
    public static void sauvegardeRoom(String path) {
        BufferedWriter myFile = null;
        try {
            myFile = new BufferedWriter(new FileWriter(new File(path)));
            myFile.write("ID;Name; Description;IsUnlocked; Items; Characters; LockedCharacters; lockedItems;");
            myFile.newLine();
            for (Room myRoom: Game.rooms) {
                myFile.write(myRoom.roomToCSV());
                myFile.newLine();
            }
            myFile.close();
        } catch (FileNotFoundException e) {
            e.toString();
            System.out.println("Wrong path");
        }
        catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * import CSV for character
     * @param path where is the CSV
     */
    public static void importCharacter(String path) {
        BufferedReader myFile = null;
        try {
            myFile = new BufferedReader(new FileReader(path));
            String line = myFile.readLine();
            while ((line = myFile.readLine()) != null) {
                Character.CSVToCharacter(line);
            }
        } catch (FileNotFoundException e) {
            e.toString();
            System.out.println("Wrong path");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * import CSV for item
     * @param path where is the CSV
     */
    public static void importItem(String path) {
        BufferedReader myFile = null;
        try {
            myFile = new BufferedReader(new FileReader(path));
            String line = myFile.readLine();
            while ((line = myFile.readLine()) != null) {
                Item.CSVToItem(line);
            }
        } catch (FileNotFoundException e) {
            e.toString();
            System.out.println("Wrong path");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * import CSV for room
     * @param path where is the CSV
     */
    public static void importRoom(String path) {
        BufferedReader myFile = null;
        try {
            myFile = new BufferedReader(new FileReader(path));
            String line = myFile.readLine();
            while ((line = myFile.readLine()) != null) {
                Room.CSVToRoom(line);
            }
        } catch (FileNotFoundException e) {
            e.toString();
            System.out.println("Wrong path");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}
