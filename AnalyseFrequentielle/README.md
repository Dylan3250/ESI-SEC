# Analyse fréquentielle [CESAR & VIGENERE]

## Introduction
Le but est de permettre de chiffrer et déchiffrer un code en utilisant la méthode de Cesar (décalage de caractères dans l'alphabet) ou celui de Vigenere qui décale les lettres de manière différente en fonction de la clé donnée. Le logiciel doit permettre de décrypter le texte donné à l'aide d'un fichier ou d'une simple chaîne de caractère que ce soit Cesar oui Vigenere avec ou sans le décalage/la clé d'indiqué.

## Comment ça marche
Voici une commande avec tous les attributs possibles. Si une commande est erronée une aide est affichée dans la console.

```bash
java -jar anaFreq.jar --[cesar|vigenere] --[encode|decode] --key=["string"|"chemin/vers-la-cle"] --offset=[nombre] --text=["string"|"chemin/vers-le-texte"] --lang=["data/fichier_frequence"] --dico=["data/fichier_dictionnaire"]
```

### Liste des attributs :
- `--cesar` : type de cryptage/décryptage.
- `--vigenere` : type de cryptage/décryptage.
- `--encode` : action à effectuer (encrypter).
- `--decode` : action à effectuer (décrypter).
- `--offset=2` : [cesar] si connu ou pour encoder, le déplacement.
- `--key="String ou chemin"` : [vigenere] si connu ou pour encoder, la clé.
- `--text="String ou chemin avec un séparateur"` : défini le chemin où chercher le text à crypter/décrypter ou une simple chaine de caractère.
- `--lang` : optionnel - [fr par défaut] modifie la langue du texte pour le dictionnaire ou la fréquence des lettres.
- `--dico` : optionnel - [cesar-vigenere] vérifie l'existence de mots dans le dictionnaire à 15% de la taille de la chaine.

### Liste d'exemple :
- `--cesar --encode --offset=2 --text="D:/Cours/TDSecuProjects/TD01AnalyseFrequentielle/data/text.txt"`
- `--cesar --encode --offset=6 --text="Bonjour, voici un code !"`
- `--cesar --decode --lang=fr --text="Erqmrxurlflxqfrgh"`
- `--cesar --decode --lang=en --dico --text="Erqmrxurlflxqfrgh"`
- `--cesar --decode --dico --text="Erqmrxurlflxqfrgh"`
- `--vigenere --encode --key="data/key.txt" --text="data/text.txt"`
- `--vigenere --decode --key="ECOLE" --text="FQBUSYTOESYU"`
- `--vigenere --decode --text="D:/Cours/TDSecuProjects/TD01AnalyseFrequentielle/data/text.txt"`
- `--vigenere --decode --key="data/key.txt" --text="data/text.txt"`
- `--vigenere --decode --lang=fr --text="data/ciphered.txt"`
- `--vigenere --decode --lang=fr --dico --text="data/ciphered.txt"`
- `--vigenere --decode --text="data/ciphered.txt"`

## Crédits
Code développé en JAVA par Dylan BRICAR (54027) dans le contexte du cours de sécurité donné par monsieur Romain Absil et monsieur Moussa Wahid à l'HE2B ESI.
Le code " Preprocess " provient de monsieur Absil, le code du Chi² provient du site https://www.baeldung.com/