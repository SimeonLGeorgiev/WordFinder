import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.URL;
import java.util.*;
import java.util.stream.Collectors;

public class Main {
    static Set<String> words;
    static Trie trie;

    public static void main(String[] args) throws IOException {
        words = loadAllWords();
        trie = loadWordsInTrie(words);
        Set<String> nineLetterWords = getAllNineLetterWords(words);

        long startTime = System.currentTimeMillis();
        List<String> solution = extractSolutionWords(nineLetterWords);
        long endTime = System.currentTimeMillis();
        long totalTime = endTime - startTime;

        System.out.println("All solution words are: " + solution);
        System.out.println("All solution word count is : " + solution.size());
        System.out.println("Total execution time in milliseconds:" + totalTime);
    }

    private static List<String> extractSolutionWords(Set<String> nineLetterWords) {
        return nineLetterWords.stream().filter(Main::findWords).toList();
    }


    private static boolean findWords(String childWord) {
        if (childWord.length() > 9) {
            return false;
        }
        if (childWord.length() == 1) {
            return childWord.contains("A") || childWord.contains("I");
        }
        for (int i = 0; i < childWord.length(); i++) {
            String currentWord = removeOneLetterFromTheWord(childWord, i);
            if (trie.search(currentWord)) {
                if (findWords(currentWord)) {
                    return true;
                }
            }
        }
        return false;
    }

    private static String removeOneLetterFromTheWord(String childWord, int i) {
        StringBuilder sb = new StringBuilder(childWord);
        sb.deleteCharAt(i);
        return sb.toString();
    }

    private static Set<String> getAllNineLetterWords(Set<String> allWords) {
        return allWords.parallelStream()
                .filter(word -> word.length() == 9 && (word.toUpperCase().contains("A") || word.toUpperCase().contains("I")))
                .collect(Collectors.toCollection(HashSet::new));
    }

    private static Trie loadWordsInTrie(Set<String> words) {
        Trie trie = new Trie();
        words.forEach(trie::insert);
        return trie;
    }

    private static Set<String> loadAllWords() throws IOException {
        Set<String> allWords = new HashSet<>(280000);
        URL wordsUrl = new URL("https://raw.githubusercontent.com/nikiiv/JavaCodingTestOne/master/scrabble-words.txt");
        BufferedReader br = new BufferedReader(new InputStreamReader(wordsUrl.openConnection().getInputStream()));
        br.lines().skip(2).filter(word -> word.contains("A") || word.toUpperCase().contains("I"))
                .forEach(allWords::add);
        return allWords;
    }
}