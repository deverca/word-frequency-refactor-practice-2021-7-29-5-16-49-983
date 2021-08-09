import java.util.*;
import java.util.stream.Collectors;

public class WordFrequencyGame {
    public static final String WHITE_SPACE = "\\s+";

    public String getResult(String sentence) {


        if (sentence.split(WHITE_SPACE).length == 1) {
            return sentence + " 1";
        } else {

            try {
                List<WordCount> wordCounts = sortWordCounts(calculateWordFrequency(sentence));
                StringJoiner joiner = new StringJoiner("\n");
                for (WordCount w : wordCounts) {
                    String s = w.getValue() + " " + w.getWordCount();
                    joiner.add(s);
                }
                return joiner.toString();
            } catch (Exception e) {

                return "Calculate Error";
            }
        }
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
