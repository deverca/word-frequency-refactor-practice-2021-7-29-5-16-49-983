import java.util.*;
import java.util.stream.Collectors;

public class WordFrequencyGame {
    public static final String WHITE_SPACE = "\\s+";

    public String getResult(String sentence) {
        try {
            List<WordInfo> wordInfos = sortWordCounts(calculateWordFrequency(sentence)); //todo:
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

    private List<WordInfo> sortWordCounts(List<WordInfo> wordInfos) {
        wordInfos.sort((w1, w2) -> w2.getWordCount() - w1.getWordCount());
        return wordInfos;
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
