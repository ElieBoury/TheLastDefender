public class Generator {

    static int generateScore(int min, int max) {
        return (int) ((Math.random() * (max + 1 - min)) + min);
    }

}
