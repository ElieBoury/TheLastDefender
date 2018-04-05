public class Générateur {

    static int generateScore(int min, int max) {
        return (int) ((Math.random() * (max + 1 - min)) + min);
    }

}
