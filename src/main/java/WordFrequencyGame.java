import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.StringJoiner;

public class WordFrequencyGame {
    public static final String WHITE_SPACE = "\\s";

    public String getResult(String sentence){


        if (sentence.split(WHITE_SPACE + "+").length==1) {
            return sentence + " 1";
        } else {

            try {

                List<WordCount> list = calculateWordFrequency(sentence);
                List<WordCount> wordCountList;
                wordCountList = list;

                wordCountList.sort((w1, w2) -> w2.getWordCount() - w1.getWordCount());

                StringJoiner joiner = new StringJoiner("\n");
                for (WordCount w : wordCountList) {
                    String s = w.getValue() + " " +w.getWordCount();
                    joiner.add(s);
                }
                return joiner.toString();
            } catch (Exception e) {


                return "Calculate Error";
            }
        }
    }

    private List<WordCount> calculateWordFrequency(String sentence) {
        String[] arr = sentence.split(WHITE_SPACE + "+");

        List<WordCount> wordCountList = new ArrayList<>();
        for (String s : arr) {
            WordCount wordCount = new WordCount(s, 1);
            wordCountList.add(wordCount);
        }

        //get the map for the next step of sizing the same word
        Map<String, List<WordCount>> map = getListMap(wordCountList);

        List<WordCount> list = new ArrayList<>();
        for (Map.Entry<String, List<WordCount>> entry : map.entrySet()) {
            WordCount wordCount = new WordCount(entry.getKey(), entry.getValue().size());
            list.add(wordCount);
        }
        return list;
    }




    private Map<String,List<WordCount>> getListMap(List<WordCount> wordCountList) {
        Map<String, List<WordCount>> map = new HashMap<>();
        for (WordCount wordCount : wordCountList){
//       map.computeIfAbsent(input.getValue(), k -> new ArrayList<>()).add(input);
            if (!map.containsKey(wordCount.getValue())){
                ArrayList arr = new ArrayList<>();
                arr.add(wordCount);
                map.put(wordCount.getValue(), arr);
            }

            else {
                map.get(wordCount.getValue()).add(wordCount);
            }
        }


        return map;
    }


}
