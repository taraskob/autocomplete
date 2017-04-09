import java.io.*;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Random;

class ArrayOfWords {
    public static void main(String[] args) {

    }
    static ArrayList<String> readInArray() {
        File file = new File("EnglishWords.txt");
        ArrayList<String> al = new ArrayList<>();
        try {
            BufferedReader in = new BufferedReader(new FileReader(file.getAbsoluteFile()));
            try {
                String s;
                char c;
                while ((s = in.readLine()) != null) {
                    if(s.length()>0)
                  /*  { if(s.charAt(0)<='z'&&s.charAt(0)>='a')
                        al.add(s);}*/
                  al.add(s.toLowerCase());
                }
            } finally {
                in.close();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return al;
    }
    static void writeErr(String inputWord,String filename, Collection ks) {
        FileWriter writer = null;
        try {
            writer = new FileWriter(filename);
            writer.write("words starting with "+"\""+inputWord +"\""+ "\r\n");
            for (Object str : ks) {
                writer.write(str + "\r\n");
            }
            writer.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    static String getWord() {
        ArrayList<String> al=AutoComplete.words_array;
        Random rnd = new Random();
        int word_index = rnd.nextInt(al.size());
        String inputWord=al.get(word_index).substring(0,rnd.nextInt(al.get(word_index).length()-1)+1);
    return inputWord;
    }
}
