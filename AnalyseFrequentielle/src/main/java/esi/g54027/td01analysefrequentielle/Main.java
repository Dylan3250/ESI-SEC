package esi.g54027.td01analysefrequentielle;

import esi.g54027.td01analysefrequentielle.crypto.Cesar;
import esi.g54027.td01analysefrequentielle.crypto.Vigenere;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.FileSystems;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Arrays;
import java.util.StringJoiner;
import java.util.stream.Stream;

public class Main {
    public static void main(String[] args) {
        String text = null;
        Boolean isCesar = null;

        try {
            for (var arg : args) {
                if (arg.equalsIgnoreCase("--help")) {
                    showHelp();
                    System.exit(0);
                } else if (arg.toLowerCase().contains("--text=")) {
                    text = loadValue(arg, "--text=", "File to crypt/encrypt not found.");
                } else if (arg.equalsIgnoreCase("--cesar")) {
                    isCesar = true;
                } else if (arg.equalsIgnoreCase("--vigenere")) {
                    isCesar = false;
                }
            }

            if (isCesar == null || text == null) {
                System.err.println("Please indicate a method --cesar OR --vigenere with a --text=.");
                System.exit(-1);
            }

            StringJoiner joiner = new StringJoiner("");
            Arrays.stream(args).forEach(joiner::add);
            String argsString = joiner.toString();
            var result = new StringBuilder();
            boolean dico = false;
            String lang = "";

            if (isCesar) {
                var cesar = new Cesar();
                if (argsString.contains("--decode")) {
                    for (var arg : args) {
                        if (arg.toLowerCase().contains("--offset=")) {
                            int offset = Integer.parseInt(arg.replace("--offset=", ""));
                            System.out.println(cesar.decode(Math.abs(offset), text));
                            System.exit(0);
                        } else if (arg.equalsIgnoreCase("--dico")) {
                            dico = true;
                        } else if (arg.toLowerCase().contains("--lang=")) {
                            lang = arg.replace("--lang=", "");
                        }
                    }
                    result.append(result.length() == 0 ? cesar.decode(text) : cesar.decode(text, lang, dico));
                } else if (argsString.contains("--encode")) {
                    for (var arg : args) {
                        if (arg.toLowerCase().contains("--offset=")) {
                            int offset = Integer.parseInt(arg.replace("--offset=", ""));
                            System.out.println(cesar.encode(Math.abs(offset), text));
                            System.exit(0);
                        }
                    }
                    if (result.length() == 0) {
                        showHelp();
                        System.err.println("When you use --encode, please indicate --offset=.");
                        System.exit(-1);
                    }
                } else {
                    showHelp();
                    System.err.println("With CESAR, use --encode or --decode.");
                    System.exit(-1);
                }
            } else {
                var vigenere = new Vigenere();
                if (argsString.contains("--decode")) {
                    for (var arg : args) {
                        if (arg.toLowerCase().contains("--key=")) {
                            String key = loadValue(arg, "--key=", "File where the key is to decode is not found.");
                            System.out.println(vigenere.decode(key, text));
                            System.exit(0);
                        } else if (arg.equalsIgnoreCase("--dico")) {
                            dico = true;
                        } else if (arg.toLowerCase().contains("--lang=")) {
                            lang = arg.replace("--lang=", "");
                        }
                    }
                    result.append(result.length() == 0 ? vigenere.decode(text) : vigenere.decode(text, lang, dico));
                } else if (argsString.contains("--encode")) {
                    for (var arg : args) {
                        if (arg.toLowerCase().contains("--key=")) {
                            String key = loadValue(arg, "--key=", "File where the key is to encode is not found.");
                            System.out.println(vigenere.encode(key, text));
                            System.exit(0);
                        }
                    }
                    if (result.length() == 0) {
                        showHelp();
                        System.err.println("When you use --encode, please indicate --key=.");
                        System.exit(-1);
                    }
                } else {
                    showHelp();
                    System.err.println("With VIGENERE, use --encode or --decode.");
                    System.exit(-1);
                }
            }
            System.out.println(result.toString());
        } catch (Exception e) {
            showHelp();
            System.err.println("Error when execution : " + e.getMessage());
        }
    }

    private static String loadValue(String arg, String delimiter, String errorMsg) {
        String value = arg.replace(delimiter, "");
        if (value.contains(FileSystems.getDefault().getSeparator()) || value.contains("/")) {
            Path path = Paths.get(value);
            StringBuilder contentBuilder = new StringBuilder();
            try (Stream<String> stream = Files.lines(path, StandardCharsets.UTF_8)) {
                stream.forEach(contentBuilder::append);
            } catch (IOException e) {
                System.err.println(errorMsg);
                System.exit(-1);
            }
            value = contentBuilder.toString();
        }
        return value;
    }

    public static void showHelp() {
        var str = new StringBuilder();
        str.append("Voici l'aide pour utiliser le programme qui permet de crypter/décrypter un message avec Cesar ou Vigenère.\n\n");

        str.append("Liste des attributs :\n");
        str.append("\t* : --cesar : type de cryptage/décryptage.\n");
        str.append("\t* : --vigenere : type de cryptage/décryptage.\n");
        str.append("\t* : --encode : action à effectuer (encrypter).\n");
        str.append("\t* : --decode : action à effectuer (décrypter).\n");
        str.append("\t* : --offset=2 : [cesar] si connu ou pour encoder, le déplacement.\n");
        str.append("\t* : --key=\"String ou chemin\" : [vigenere] si connu ou pour encoder, la clé.\n");
        str.append("\t* : --text=\"String ou chemin avec un séparateur\" : défini le chemin où chercher le text à crypter/décrypter ou une simple chaine de caractère.\n\n");
        str.append("\t* : --lang : optionnel - [fr par défaut] modifie la langue du texte pour le dictionnaire ou la fréquence des lettres.\n");
        str.append("\t* : --dico : optionnel - [cesar-vigenere] vérifie l'existence de mots dans le dictionnaire à 15% de la taille de la chaine.\n");

        str.append("Liste d'exemple :\n");
        str.append("\t* : --cesar --encode --offset=2 --text=\"D:/Cours/TDSecuProjects/TD01AnalyseFrequentielle/data/text.txt\"\n");
        str.append("\t* : --cesar --encode --offset=6 --text=\"Bonjour, voici un code !\"\n");
        str.append("\t* : --cesar --decode --lang=fr --text=\"Erqmrxurlflxqfrgh\"\n");
        str.append("\t* : --cesar --decode --lang=en --dico --text=\"Erqmrxurlflxqfrgh\"\n");
        str.append("\t* : --cesar --decode --dico --text=\"Erqmrxurlflxqfrgh\"\n");
        str.append("\t* : --vigenere --encode --key=\"data/key.txt\" --text=\"data/text.txt\"\n");
        str.append("\t* : --vigenere --decode --key=\"ECOLE\" --text=\"FQBUSYTOESYU\"\n");
        str.append("\t* : --vigenere --decode --text=\"D:/Cours/TDSecuProjects/TD01AnalyseFrequentielle/data/text.txt\"\n");
        str.append("\t* : --vigenere --decode --key=\"data/key.txt\" --text=\"data/text.txt\"\n");
        str.append("\t* : --vigenere --decode --lang=fr --text=\"data/ciphered.txt\"\n");
        str.append("\t* : --vigenere --decode --lang=fr --dico --text=\"data/ciphered.txt\"\n");
        str.append("\t* : --vigenere --decode --text=\"data/ciphered.txt\"\n");
        System.out.println(str.toString());
    }
}
