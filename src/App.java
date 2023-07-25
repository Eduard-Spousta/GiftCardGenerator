import view.Frame;

import java.util.Random;

public class App {
    static Random random = new Random();
    public static void main(String[] args) {

        String randomSequence = generateRandomSequence();
        System.out.println("Random sequence: " + randomSequence);
        new Frame();
    }

    public static String generateRandomSequence() {
        StringBuilder sequenceBuilder = new StringBuilder();
        for (int i = 0; i < 9; i++) {
            if (i == 4) {
                sequenceBuilder.append("-");
            } else {
                if (random.nextInt(2) == 0) {
                    sequenceBuilder.append(generateRDMNumber());
                } else {
                    sequenceBuilder.append(generateRDMSymbol());
                }
            }
        }
        return sequenceBuilder.toString();
    }

    public static char generateRDMSymbol() {
        int letter = random.nextInt(26) + 'a';
        return Character.toUpperCase((char) letter);
    }
    public static int generateRDMNumber() {
        int number = random.nextInt(10);
        return number;
    }
}
