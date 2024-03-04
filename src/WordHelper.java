import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.URL;
import java.util.HashSet;
import java.util.Set;
import java.util.stream.Collectors;

public class WordHelper {
    public static Set<String> loadAllWords() throws IOException {
        Set<String> allWords = new HashSet<>(280000);
        URL wordsUrl = new URL("https://raw.githubusercontent.com/nikiiv/JavaCodingTestOne/master/scrabble-words.txt");
        BufferedReader br = new BufferedReader(new InputStreamReader(wordsUrl.openConnection().getInputStream()));
        br.lines().skip(2).filter(word -> word.contains("A") || word.toUpperCase().contains("I"))
                .forEach(allWords::add);
        return allWords;
    }

    static Tree loadWordsInTree(Set<String> words) {
        Tree tree = new Tree();
        words.forEach(tree::insert);
        return tree;
    }

    static Set<String> getAllNineLetterWords(Set<String> allWords) {
        return allWords.parallelStream()
                .filter(word -> word.length() == 9 && (word.toUpperCase().contains("A") || word.toUpperCase().contains("I")))
                .collect(Collectors.toCollection(HashSet::new));
    }

    public static String removeOneLetterFromTheWord(String childWord, int i) {
        StringBuilder sb = new StringBuilder(childWord);
        sb.deleteCharAt(i);
        return sb.toString();
    }
}
