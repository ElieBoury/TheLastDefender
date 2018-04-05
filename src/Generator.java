public class Generator {

    /**
     *
     simulates a roll of dice between 2 bounds
     * @param min the lower bound
     * @param max the upper bound
     * @return the score obtained
     */
    static int generateScore(int min, int max) {
        return (int) ((Math.random() * (max + 1 - min)) + min);
    }

}
