import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.StringJoiner;
import java.util.stream.Collectors;

import static java.util.Collections.reverseOrder;
import static java.util.Comparator.comparingInt;

public class WordFrequencyGame {
    public static final String WHITE_SPACE = "\\s+";

    public String getFrequencyResult(String sentence) {
        try {
            List<WordInfo> wordInfos = calculateWordFrequency(sentence);
            sortWordCounts(wordInfos);
            return joinWordCounts(wordInfos);
        } catch (Exception e) {
            return "Calculate Error";
        }
    }

    private String joinWordCounts(List<WordInfo> wordInfos) {
        StringJoiner joinedWordCounts = new StringJoiner("\n");
        wordInfos.forEach(wordInfo -> {
            joinedWordCounts.add(wordInfo.getValue() + " " + wordInfo.getWordCount());
        });
        return joinedWordCounts.toString();
    }

    private void sortWordCounts(List<WordInfo> wordInfos) {
        wordInfos.sort(reverseOrder(comparingInt(WordInfo::getWordCount)));
    }

    private List<WordInfo> calculateWordFrequency(String sentence) {

        List<String> words = Arrays.asList(sentence.split(WHITE_SPACE));
        List<String> uniqueWords = words.stream().distinct().collect(Collectors.toList());

        List<WordInfo> wordInfos = new ArrayList<>();
        uniqueWords.forEach(uniqueWord -> {
            int count = (int) words.stream().filter(uniqueWord::equals).count();
            wordInfos.add(new WordInfo(uniqueWord, count));
        });
        return wordInfos;
    }

}
