import javax.sound.sampled.*;
import java.util.HashMap;
import java.util.Scanner;

public class MorseToAudioAndText {
    private static final int SAMPLE_RATE = 44100;
    private static final int DOT_DURATION = 200;  // milliseconds
    private static final int DASH_DURATION = 600; // milliseconds
    private static final int GAP_DURATION = 200;  // milliseconds between dots/dashes
    private static final int FREQUENCY = 800;     // Frequency of the tone (Hz)

    public static void main(String[] args) {
        // Morse code dictionary: Text to Morse
        HashMap<Character, String> textToMorse = new HashMap<>();
        textToMorse.put('A', ".-");
        textToMorse.put('B', "-...");
        textToMorse.put('C', "-.-.");
        textToMorse.put('D', "-..");
        textToMorse.put('E', ".");
        textToMorse.put('F', "..-.");
        textToMorse.put('G', "--.");
        textToMorse.put('H', "....");
        textToMorse.put('I', "..");
        textToMorse.put('J', ".---");
        textToMorse.put('K', "-.-");
        textToMorse.put('L', ".-..");
        textToMorse.put('M', "--");
        textToMorse.put('N', "-.");
        textToMorse.put('O', "---");
        textToMorse.put('P', ".--.");
        textToMorse.put('Q', "--.-");
        textToMorse.put('R', ".-.");
        textToMorse.put('S', "...");
        textToMorse.put('T', "-");
        textToMorse.put('U', "..-");
        textToMorse.put('V', "...-");
        textToMorse.put('W', ".--");
        textToMorse.put('X', "-..-");
        textToMorse.put('Y', "-.--");
        textToMorse.put('Z', "--..");
        textToMorse.put('1', ".----");
        textToMorse.put('2', "..---");
        textToMorse.put('3', "...--");
        textToMorse.put('4', "....-");
        textToMorse.put('5', ".....");
        textToMorse.put('6', "-....");
        textToMorse.put('7', "--...");
        textToMorse.put('8', "---..");
        textToMorse.put('9', "----.");
        textToMorse.put('0', "-----");
        textToMorse.put(' ', "/"); // Space between words

        // Input from user
        System.out.println("Enter Plain Text to convert to Morse Code and Audio:");
        Scanner scanner = new Scanner(System.in);
        String input = scanner.nextLine().toUpperCase();

        StringBuilder morseOutput = new StringBuilder();

        // Convert text to Morse code
        for (char c : input.toCharArray()) {
            String morse = textToMorse.getOrDefault(c, "");
            morseOutput.append(morse).append(" ");
        }

        // Print Morse code output
        System.out.println("Morse Code: " + morseOutput.toString().trim());

        // Play Morse code as audio
        for (char c : morseOutput.toString().toCharArray()) {
            if (c == '.') {
                playTone(DOT_DURATION); // Play dot
            } else if (c == '-') {
                playTone(DASH_DURATION); // Play dash
            } else if (c == ' ') {
                sleep(GAP_DURATION); // Gap between symbols
            } else if (c == '/') {
                sleep(GAP_DURATION * 3); // Gap between words
            }
        }
    }

    // Method to play a tone of specified duration
    private static void playTone(int duration) {
        try {
            byte[] buffer = new byte[SAMPLE_RATE * duration / 1000];
            for (int i = 0; i < buffer.length; i++) {
                buffer[i] = (byte) (Math.sin(2 * Math.PI * i * FREQUENCY / SAMPLE_RATE) * 127);
            }
            AudioFormat format = new AudioFormat(SAMPLE_RATE, 8, 1, true, false);
            SourceDataLine line = AudioSystem.getSourceDataLine(format);
            line.open(format);
            line.start();
            line.write(buffer, 0, buffer.length);
            line.drain();
            line.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    // Method to pause the program for a given duration
    private static void sleep(int duration) {
        try {
            Thread.sleep(duration);
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        }
    }
}
