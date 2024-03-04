import java.io.IOException;
import java.util.*;

public class Main {
    static Set<String> allWords;
    static Tree tree;

    public static void main(String[] args) throws IOException {
        long startTime = System.currentTimeMillis();

        allWords = WordHelper.loadAllWords();
        tree = WordHelper.loadWordsInTree(allWords);
        Set<String> nineLetterWords = WordHelper.getAllNineLetterWords(allWords);
        List<String> solution = extractSolutionWords(nineLetterWords);

        long endTime = System.currentTimeMillis();
        printResults(endTime, startTime, solution);
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
            String currentWord = WordHelper.removeOneLetterFromTheWord(childWord, i);
            if (tree.search(currentWord)) {
                if (findWords(currentWord)) {
                    return true;
                }
            }
        }
        return false;
    }

    private static void printResults(long endTime, long startTime, List<String> solution) {
        long totalTime = endTime - startTime;
        System.out.println("All solution words are: " + solution);
        System.out.println("All solution word count is : " + solution.size());
        System.out.println("Total execution time in milliseconds:" + totalTime);
    }
}