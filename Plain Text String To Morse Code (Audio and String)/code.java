import javax.sound.sampled.*;
import java.util.*;

public class MorseCodeAudio {
    private static final int DOT_DURATION = 200; //200ms 
    private static final int DASH_DURATION = 600; //600ms
    private static final int GAP_DURATION = 200; //200ms - between letters
    private static final int FREQUENCY = 800; //800hz - defines how high or low the beeps sound
    private static final int SAMPLE_RATE = 44100; //44.1khz - define how clear the beep sounds

    private static final Map<Character, String> MORSE_MAP = Map.ofEntries(
        Map.entry('A', ".-"), Map.entry('B', "-..."), Map.entry('C', "-.-."), Map.entry('D', "-.."),
        Map.entry('E', "."), Map.entry('F', "..-."), Map.entry('G', "--."), Map.entry('H', "...."),
        Map.entry('I', ".."), Map.entry('J', ".---"), Map.entry('K', "-.-"), Map.entry('L', ".-.."),
        Map.entry('M', "--"), Map.entry('N', "-."), Map.entry('O', "---"), Map.entry('P', ".--."),
        Map.entry('Q', "--.-"), Map.entry('R', ".-."), Map.entry('S', "..."), Map.entry('T', "-"),
        Map.entry('U', "..-"), Map.entry('V', "...-"), Map.entry('W', ".--"), Map.entry('X', "-..-"),
        Map.entry('Y', "-.--"), Map.entry('Z', "--.."), Map.entry('1', ".----"), Map.entry('2', "..---"),
        Map.entry('3', "...--"), Map.entry('4', "....-"), Map.entry('5', "....."), Map.entry('6', "-...."),
        Map.entry('7', "--..."), Map.entry('8', "---.."), Map.entry('9', "----."), Map.entry('0', "-----"),
        Map.entry(' ', "/") // Space between words
    );

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        System.out.print("Enter text: ");
        String input = scanner.nextLine().toUpperCase();
        
        StringBuilder morseCode = new StringBuilder();
        for (char c : input.toCharArray()) {
            morseCode.append(MORSE_MAP.getOrDefault(c, "")).append(" ");
        }

        System.out.println("Morse Code: " + morseCode.toString().trim());
        playMorseCode(morseCode.toString());
    }

    private static void playMorseCode(String morse) {
        for (char c : morse.toCharArray()) {
            if (c == '.') playTone(DOT_DURATION);
            else if (c == '-') playTone(DASH_DURATION);
            else if (c == ' ') sleep(GAP_DURATION);
            else if (c == '/') sleep(GAP_DURATION * 3);
        }
    }

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

    private static void sleep(int duration) {
        try {
            Thread.sleep(duration);
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        }
    }
}
