package Classes;

import java.io.*;

public class Sauvegarde {

    final static String DefaultPathCharacter = "src/Sauvegarde/Default/Character.csv";
    final static String DefaultPathItem = "src/Sauvegarde/Default/Item.csv";
    final static String DefaultPathRoom = "src/Sauvegarde/Default/Room.csv";

    final static String PathCharacter = "src/Sauvegarde/Character.csv";
    final static String PathItem = "src/Sauvegarde/Item.csv";
    final static String PathRoom = "src/Sauvegarde/Room.csv";



    public static void saveGame(){
        sauvegardeItems(PathItem);
        sauvegardeCharacter(PathCharacter);
        sauvegardeRoom(PathRoom);
    }

    public static void importNewGame(){
        importItem(DefaultPathItem);
        importCharacter(DefaultPathCharacter);
        importRoom(DefaultPathRoom);
        Game.characters.get(0).setCurrentRoom(Game.rooms.get(0));
    }

    public static void importGame(){
        importItem(PathItem);
        importCharacter(PathCharacter);
        importRoom(PathRoom);
    }

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
