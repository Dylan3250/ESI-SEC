package esi.g54027.td01analysefrequentielle.crypto;

import esi.g54027.td01analysefrequentielle.utils.Preprocess;

import java.io.IOException;
import java.util.*;

public class Vigenere {
    private final char[] alphabet = new char[]{'A', 'B', 'C', 'D', 'E', 'F', 'G', 'H', 'I', 'J', 'K', 'L', 'M', 'N',
            'O', 'P', 'Q', 'R', 'S', 'T', 'U', 'V', 'W', 'X', 'Y', 'Z'};

    public String encode(String key, String plainText) {
        return action(key, plainText, true);
    }

    public String decode(String key, String ciphered) {
        return action(key, ciphered, false);
    }

    private String action(String key, String text, boolean encode) {
        text = Preprocess.preprocessLine(text).toUpperCase();
        key = Preprocess.preprocessLine(key).toUpperCase();
        int[] keyToInt = alignKey(keyToInt(key), text);
        var plainText = new StringBuilder();
        var cesar = new Cesar();

        for (int charKey = 0; charKey < keyToInt.length; charKey++) {
            plainText.append(encode ? cesar.encode(keyToInt[charKey], Character.toString(text.charAt(charKey))) :
                    cesar.decode(keyToInt[charKey], Character.toString(text.charAt(charKey))));
        }
        return plainText.toString();
    }

    private int[] alignKey(int[] key, String plainText) {
        var repeatKey = new int[plainText.length()];
        for (int i = 0; i < repeatKey.length; i++) {
            repeatKey[i] = key[i % key.length];
        }
        return repeatKey;
    }

    private int[] keyToInt(String key) {
        int[] keyInteger = new int[key.length()];
        for (int charKey = 0; charKey < key.length(); charKey++) {
            for (int i = 0; i < alphabet.length; i++) {
                if (alphabet[i] == key.charAt(charKey)) {
                    keyInteger[charKey] = i;
                    break;
                }
            }
        }
        return keyInteger;
    }

    public String decode(String ciphered) throws IOException {
        return decode(ciphered, "fr", false);
    }

    public String decode(String ciphered, String lang, boolean dico) throws IOException {
        ciphered = Preprocess.preprocessLine(ciphered).toUpperCase();
        String cipheredCopy = ciphered;

        // Counts the number of times each string with more than 3 characters is repeated and computes the distance.
        HashMap<Integer, Integer> countOffset = new HashMap<>();
        for (int i = 0; i + 3 < cipheredCopy.length() && i < cipheredCopy.length(); i += 3) {
            String word = cipheredCopy.substring(i, i + 3);
            if (word.contains("*")) {
                continue;
            }
            int previous = i;
            int count = 0;
            for (int j = i; j + 3 < cipheredCopy.length() && j < cipheredCopy.length(); j++) {
                if (j - previous > 0 && j != i && cipheredCopy.substring(j, j + 3).equals(word)) {
                    // Counts against the previous channel found.
                    countOffset.put(j - previous, countOffset.getOrDefault(j - previous, 0) + 1);
                    if (previous != i) {
                        // Checks against the first index.
                        countOffset.put(j - i, countOffset.getOrDefault(j - i, 0) + 1);
                    }
                    count++;
                    previous = j - previous;
                }
                if (count > 4) {
                    cipheredCopy = cipheredCopy.replaceAll(word, "***");
                    break;
                }
            }
        }

        List<Integer> calculGCD = new ArrayList<>();
        while (calculGCD.size() < 5) {
            Map.Entry<Integer, Integer> entry =
                    countOffset.entrySet().stream().max(Comparator.comparingInt(Map.Entry::getValue)).orElse(null);
            if (entry == null) {
                break;
            }
            countOffset.remove(entry.getKey());
            calculGCD.add(entry.getKey());
        }
        if (calculGCD.size() < 2) {
            throw new IllegalArgumentException("There is not enough repetition to be able to find the key.");
        }
        int gcd = gcd(calculGCD);

        // Creates an array with an offset of GCD containing all the characters of the encrypted string
        String[] cutString = new String[gcd];
        for (int i = 0; i < cutString.length; i++) {
            for (int j = i; j < ciphered.length(); j += gcd) {
                if (cutString[i] == null) {
                    cutString[i] = Character.toString(ciphered.charAt(j));
                } else {
                    cutString[i] += ciphered.charAt(j);
                }
            }
        }

        // Decode with CESAR each of the parameters with the offset corresponding to the letter indicated
        var cesar = new Cesar();
        for (int i = 0; i < cutString.length; i++) {
            cutString[i] = cesar.decode(cutString[i], lang, dico);
        }

        // Reassemble the cut paintings in the correct order
        StringBuilder decrypted = new StringBuilder();
        for (int col = 0; col < cutString[0].length(); col++) {
            for (int ln = 0; ln < cutString.length && col < cutString[ln].length(); ln++) {
                decrypted.append(cutString[ln].charAt(col));
            }
        }
        return decrypted.toString();
    }

    private int gcd(List<Integer> list) {
        int result = list.get(0);
        for (var value : list) {
            result = gcd(result, value);
        }
        return result;
    }

    private int gcd(int a, int b) {
        if (b == 0) {
            return a;
        }
        return gcd(b, a % b);
    }
}
