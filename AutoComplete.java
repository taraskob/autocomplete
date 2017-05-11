import java.util.ArrayList;

class AutoComplete {
    static int FirstSymbolCode = (int) 'a';
    static int LastSymbolCode = (int) 'z';
    static ArrayList<String> words_array = ArrayOfWords.readInArray();
    static ArrayList<Integer> firstLevelInd = create_index(0, words_array.size(), 0);
    static String inputWord = ArrayOfWords.getWord();

    public static void main(String[] args) {
        inputWord ="aba";
        long startTime = System.currentTimeMillis();
        getWords(inputWord);
        long timeSpent = System.currentTimeMillis();
        System.out.println("run time is " + (timeSpent - startTime));
    }

    static ArrayList<String> getWords(String inputWord) {
        int startIndex = firstLevelInd.get((int) inputWord.charAt(0) - FirstSymbolCode);
        int finishIndex = words_array.size();
        if ((int) inputWord.charAt(0) < LastSymbolCode)
            finishIndex = getFinishIndex(firstLevelInd, inputWord, 0);
        if (inputWord.length() == 1)
            return fillArray(startIndex, finishIndex, inputWord);
        int wordlength = inputWord.length();
        if (wordlength > 3)
            wordlength = 3;
        for (int i = 1; i < wordlength; i++) {
            ArrayList<Integer> nextLevelInd = create_index(startIndex, finishIndex, i);
            startIndex = nextLevelInd.get((int) inputWord.charAt(i) - FirstSymbolCode);
            finishIndex = words_array.size();
            if ((int) inputWord.charAt(i) < LastSymbolCode)
                finishIndex = getFinishIndex(nextLevelInd, inputWord, i);
        }
        int[] arrayRange;
        if (inputWord.length() < 4)
            return fillArray(startIndex, finishIndex, inputWord);
        else
            arrayRange = getArrayRange(startIndex, finishIndex, inputWord);
        return fillArray(arrayRange[0], arrayRange[1], inputWord);
    }

    static int[] getArrayRange(int startIndex, int finishIndex, String inputWord) {
        int code4symbol = (int) inputWord.charAt(3);
        int middle = startIndex + (finishIndex - startIndex) / 2;
        int right_ind = finishIndex;
        int left_ind = startIndex;
        if (code4symbol < (int) (words_array.get(middle) + "   ").charAt(3)) {
            left_ind = startIndex;
            right_ind = middle - 1;
        }

        if (code4symbol > (int) (words_array.get(middle) + "   ").charAt(3)) {
            left_ind = startIndex;
            right_ind = finishIndex;
        } else {
            for (int i = middle; i < finishIndex; i++)
                if (!words_array.get(i).startsWith(inputWord)) {
                    right_ind = i;
                    break;
                }

            for (int i = middle - 1; i > startIndex; i--) {
                if (!words_array.get(i).startsWith(inputWord))
                    break;
                left_ind = i;
            }
        }
        return new int[]{left_ind, right_ind};

    }

    static int getFinishIndex(ArrayList<Integer> array_index, String inputWord, int letterposition) {
        int finishIndex = -1;
        int next = 1;
        while (finishIndex == -1) {
            finishIndex = array_index.get((int) inputWord.charAt(letterposition) - FirstSymbolCode + next);
            if (next < LastSymbolCode - ((int) inputWord.charAt(letterposition) - FirstSymbolCode))
                next++;
            else {
                finishIndex = words_array.size();
                break;
            }
        }
        return finishIndex;
    }

    static ArrayList<Integer> create_index(int start_index, int finish_index, int letterPosition) {
        ArrayList<Integer> ind_al = new ArrayList<>(LastSymbolCode - FirstSymbolCode + 1);
        for (int i = 0; i <= LastSymbolCode - FirstSymbolCode; i++)
            ind_al.add(-1);
        int ind_al_index = -1;
        for (int i = start_index; i < finish_index; i++) {
            String word = words_array.get(i);
            if (word.length() > letterPosition)
                if ((int) word.charAt(letterPosition) - FirstSymbolCode != ind_al_index) {
                    ind_al_index = (int) word.charAt(letterPosition) - FirstSymbolCode;
                    if (ind_al_index >= 0 && ind_al_index <= (LastSymbolCode - FirstSymbolCode))
                        ind_al.set(ind_al_index, words_array.indexOf(word));
                }
        }

        return ind_al;
    }

    static ArrayList<String> fillArray(int startIndex, int finishIndex, String inputWord) {
        ArrayList<String> words_al = new ArrayList<>();
        for (int i = startIndex; i < finishIndex; i++) {
            if (startIndex == -1) {
                break;
            }
            if (inputWord.length() < 4)
                words_al.add(words_array.get(i));
            else {
                if (words_array.get(i).startsWith(inputWord))
                    words_al.add(words_array.get(i));
            }
        }

        return words_al;
    }
}
