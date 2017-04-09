import java.util.ArrayList;

import static org.junit.Assert.*;

/**
 * Created by kob on 09.04.2017.
 */
public class AutoCompleteTest {
    @org.junit.Test
    public void getWords() throws Exception {
        String inputWord = ArrayOfWords.getWord();
        ArrayList<String> alTest = AutoComplete.getWords(inputWord);
        boolean result = true;
        for (String str : alTest) {
            if (!str.startsWith(inputWord)) {
                result = false;
                break;
            }
        }
        ArrayOfWords.writeErr(inputWord, "testResult.txt", alTest);
        if (result)
            System.out.println("TEST IS Ok...see testResult.txt");
        else
            System.out.println("TEST IS FAILED!  see testResult.txt");
    }

}