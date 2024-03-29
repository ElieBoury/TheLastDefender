package Classes;

public class Generator {

    /**
     * simulates a roll of dice between 2 bounds and a number of dices
     * @param min the lower bound
     * @param max the upper bound
     * @param nbDice number of dices
     * @return the best score obtained
     */
    public static int generateScore(int min, int max, int nbDice) {
        int bestScore = 0, a;
        for(int i=0; i<nbDice; i++){
            a = (int) (Math.random() * (max + 1 - min)) + min;
            if(a>bestScore){
                bestScore=a;
            }
        }
        return bestScore;
    }

}
