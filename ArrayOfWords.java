import java.io.*;
import java.util.ArrayList;
import java.util.Collection;
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
    static void writeErr(String filename, Collection ks) {
        FileWriter writer = null;
        try {
            writer = new FileWriter(filename);
            for (Object str : ks) {
                writer.write(str + "\r\n");
            }
            writer.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
