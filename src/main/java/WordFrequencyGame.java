import java.util.*;
import java.util.stream.Collectors;

public class WordFrequencyGame {
    public static final String WHITE_SPACE = "\\s+";

    public String getResult(String sentence) {


        if (sentence.split(WHITE_SPACE).length == 1) { //todo: remove if
            return sentence + " 1";
        } else {

            try {
                List<WordCount> wordCounts = sortWordCounts(calculateWordFrequency(sentence)); //todo:
                return joinWordCounts(wordCounts);
            } catch (Exception e) {

                return "Calculate Error";
            }
        }
    }

    private String joinWordCounts(List<WordCount> wordCounts) {
        StringJoiner joinedWordCounts = new StringJoiner("\n");
        wordCounts.forEach(wordCount -> {
            joinedWordCounts.add(wordCount.getValue() + " " + wordCount.getWordCount());
        });
        return joinedWordCounts.toString();
    }

    private List<WordCount> sortWordCounts(List<WordCount> wordCountList) {
        wordCountList.sort((w1, w2) -> w2.getWordCount() - w1.getWordCount());
        return wordCountList;
    }

    private List<WordCount> calculateWordFrequency(String sentence) {

        List<String> words = Arrays.asList(sentence.split(WHITE_SPACE));
        List<String> uniqueWords = words.stream().distinct().collect(Collectors.toList());

        List<WordCount> wordCounts = new ArrayList<>();
        uniqueWords.forEach(uniqueWord -> {
            int count = (int) words.stream().filter(uniqueWord::equals).count();
            wordCounts.add(new WordCount(uniqueWord, count));
        });
        return wordCounts;
    }

}
