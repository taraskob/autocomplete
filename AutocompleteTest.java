import java.util.ArrayList;

class AutocompleteTest {

    static int FirstSymbolCode = 97;
    static ArrayList<String> words_array = ArrayOfWords.readInArray();
    static ArrayList<Integer> words_array_index1 = create_index(0,words_array.size(),0);
    public static void main(String[] args) {

        String inputWord = "a";
        for (int i = 0; i < inputWord.length(); i++)
            if (inputWord.charAt(i) > 'z' || inputWord.charAt(i) < 'a')
                return;
        long startTime = System.currentTimeMillis();
        getWords(inputWord);
        long timeSpent = System.currentTimeMillis();
        System.out.println("run time is " + (timeSpent - startTime));
    }

   static ArrayList<String> getWords(String inputWord) {
       ArrayList<String> wordsAl = new ArrayList<>();
       int startIndex = words_array_index1.get((int) inputWord.charAt(0) - FirstSymbolCode);
       int finishIndex=words_array.size();
       if(inputWord.charAt(0)<'z')
           finishIndex=words_array_index1.get((int) inputWord.charAt(0) - FirstSymbolCode+1);
       if(inputWord.length()==1)
       return fillArray(startIndex,finishIndex);
       for(int i=1;i<inputWord.length();i++) {
           ArrayList<Integer> nextLevelInd=create_index(startIndex,finishIndex,i);
           startIndex = nextLevelInd.get((int) inputWord.charAt(i) - FirstSymbolCode);
            finishIndex=words_array.size();
           if(inputWord.charAt(i)<'z')
               finishIndex=nextLevelInd.get((int) inputWord.charAt(i) - FirstSymbolCode+1);
       }
       return null;
    }

    static ArrayList<String> fillArray(int startIndex, int finishIndex) {
        return null;
    }



    static ArrayList<Integer> create_index(int start_index, int finish_index,int letterPosition) {
        ArrayList<Integer> ind_al = new ArrayList<>(26);
        for (int i = 0; i < 26; i++)
            ind_al.add(-1);

        int ind_al_index = -1;
        for (int i=start_index;i<finish_index;i++) {
String word=words_array.get(i);
            if ((int) word.charAt(letterPosition) - FirstSymbolCode != ind_al_index) {
                ind_al_index = (int) word.charAt(letterPosition) - FirstSymbolCode;
                ind_al.set(ind_al_index, words_array.indexOf(word));

            }
        }

      
        return ind_al;
    }


}
