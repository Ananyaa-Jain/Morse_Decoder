//Java code for Morse code string to plain text string
import java.util.HashMap;
import java.util.Scanner;

public class MorseStringToText {
    public static void main(String[] args) {
        // Mapping of Morse code to text
        HashMap<String, Character> morseToText = new HashMap<>();
        morseToText.put(".-", 'A');
        morseToText.put("-...", 'B');
        morseToText.put("-.-.", 'C');
        morseToText.put("-..", 'D');
        morseToText.put(".", 'E');
        morseToText.put("..-.", 'F');
        morseToText.put("--.", 'G');
        morseToText.put("....", 'H');
        morseToText.put("..", 'I');
        morseToText.put(".---", 'J');
        morseToText.put("-.-", 'K');
        morseToText.put(".-..", 'L');
        morseToText.put("--", 'M');
        morseToText.put("-.", 'N');
        morseToText.put("---", 'O');
        morseToText.put(".--.", 'P');
        morseToText.put("--.-", 'Q');
        morseToText.put(".-.", 'R');
        morseToText.put("...", 'S');
        morseToText.put("-", 'T');
        morseToText.put("..-", 'U');
        morseToText.put("...-", 'V');
        morseToText.put(".--", 'W');
        morseToText.put("-..-", 'X');
        morseToText.put("-.--", 'Y');
        morseToText.put("--..", 'Z');

        System.out.println("Enter Morse code (separate characters with space):");
        Scanner sc = new Scanner(System.in);
        String morseInput = sc.nextLine();
        String[] morseWords = morseInput.split(" ");

        //Use StringBuilder to append
        StringBuilder textOutput = new StringBuilder();
        for (String morse : morseWords) {
            textOutput.append(morseToText.getOrDefault(morse, ' '));
        }
        System.out.println("Decoded text: " + textOutput);
    }
}
