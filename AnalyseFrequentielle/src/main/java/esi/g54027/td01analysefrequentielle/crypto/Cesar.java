package esi.g54027.td01analysefrequentielle.crypto;

import esi.g54027.td01analysefrequentielle.utils.Preprocess;
import org.apache.commons.math3.stat.inference.ChiSquareTest;
import org.json.simple.JSONArray;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

import java.io.FileReader;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Arrays;
import java.util.List;
import java.util.Locale;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

public class Cesar {

    private final char[] alphabet = new char[]{'A', 'B', 'C', 'D', 'E', 'F', 'G', 'H', 'I', 'J', 'K', 'L', 'M', 'N',
        'O', 'P', 'Q', 'R', 'S', 'T', 'U', 'V', 'W', 'X', 'Y', 'Z'};

    public String encode(int offset, String plainText) {
        offset = Math.abs(offset);
        plainText = Preprocess.preprocessLine(plainText).toUpperCase();
        var ciphered = new StringBuilder();
        for (int posLetter = 0; posLetter < plainText.length(); posLetter++) {
            for (int posAlpha = 0; posAlpha < alphabet.length; posAlpha++) {
                if (plainText.charAt(posLetter) == alphabet[posAlpha]) {
                    ciphered.append(alphabet[(posAlpha + offset) % alphabet.length]);
                    break;
                }
            }
        }
        return ciphered.toString();
    }

    public String decode(int offset, String ciphered) {
        return encode((alphabet.length - offset) % alphabet.length, ciphered);
    }

    public String decode(String ciphered, String lang) throws IOException {
        return decode(ciphered, lang, false);
    }

    public String decode(String ciphered) throws IOException {
        return decode(ciphered, "fr", false);
    }

    public String decode(String ciphered, String lang, boolean dico) throws IOException {
        ciphered = Preprocess.preprocessLine(ciphered).toUpperCase();
        var attempt = new StringBuilder();

        double[] listFreq = new double[26];
        try {
            Object obj = new JSONParser().parse(new FileReader("data/freq_" + lang + ".json"));
            JSONArray json = (JSONArray) obj;
            for (int i = 0; i < listFreq.length; i++) {
                listFreq[i] = (double) json.get(i);
            }
        } catch (ParseException e) {
            throw new IOException("File frequencies not found : data/freq_" + lang + ".json");
        }

        String finalCiphered = ciphered;
        double[] expectedLettersFrequencies = Arrays.stream(listFreq)
                .map(probability -> probability * finalCiphered.length())
                .toArray();

        double[] chiSquares = new double[26];
        for (int offset = 0; offset < chiSquares.length; offset++) {
            String decipheredMessage = decode(offset, ciphered);
            long[] lettersFrequencies = observedLettersFrequencies(decipheredMessage);
            double chiSquare = new ChiSquareTest().chiSquare(expectedLettersFrequencies, lettersFrequencies);
            chiSquares[offset] = chiSquare;
        }

        int probableOffset = 0;
        for (int offset = 0; offset < chiSquares.length; offset++) {
            if (chiSquares[offset] < chiSquares[probableOffset]) {
                probableOffset = offset;
            }
        }
        attempt.append(decode(probableOffset, ciphered));

        if (dico) {
            Path path = Paths.get("data/dico_" + lang + ".txt");
            List<String> wordsDico;
            try {
                wordsDico = Files.lines(path)
                        .map(line -> line.toUpperCase(Locale.ROOT))
                        .map(line -> Preprocess.preprocessLine(line).toUpperCase())
                        .collect(Collectors.toList());
            } catch (IOException e) {
                throw new IOException("File dictionary not found : " + path.toString());
            }

            if (attempt.length() > 0) {
                int nbWord = 0;
                for (var word : wordsDico) {
                    if (attempt.toString().contains(word)) {
                        nbWord++;
                        if (nbWord > Math.round(ciphered.length() * 0.15)) {
                            break;
                        }
                    }
                }
            } else {
                for (int offset = 0; offset < alphabet.length; offset++) {
                    String decrypted = decode(offset, ciphered);
                    int nbWord = 0;
                    for (var word : wordsDico) {
                        if (decrypted.contains(word)) {
                            nbWord++;
                            if (nbWord > Math.round(ciphered.length() * 0.15)) {
                                return decrypted;
                            }
                        }
                    }
                }
            }
        }
        return attempt.toString();
    }

    private long[] observedLettersFrequencies(String message) {
        return IntStream.rangeClosed('A', 'Z')
                .mapToLong(letter -> countLetter((char) letter, message))
                .toArray();
    }

    private long countLetter(char letter, String message) {
        return message.chars()
                .filter(character -> character == letter)
                .count();
    }
}
