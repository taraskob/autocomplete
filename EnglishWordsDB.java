import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.Statement;
import java.util.ArrayList;

class EnglishWordsDB {

    public static void main(String[] args) {

      //  CreateTableAndIndex();
     //   insertEnglishWords();

    }

    static void CreateTableAndIndex() {
        Connection c = null;
        Statement stmt = null;
        try {
            Class.forName("org.sqlite.JDBC");
            c = DriverManager.getConnection("jdbc:sqlite:EnglishWords.db");
            System.out.println("Opened database successfully");

            stmt = c.createStatement();
            String sql = "CREATE TABLE Words (word VARCHAR PRIMARY KEY NOT NULL)";
            stmt.executeUpdate(sql);
            stmt.close();
            stmt = c.createStatement();
             sql = "CREATE UNIQUE INDEX Alphabet ON Words(word)";
            stmt.executeUpdate(sql);
            stmt.close();
            c.close();
        } catch (Exception e) {
            System.err.println(e.getClass().getName() + ": " + e.getMessage());
            System.exit(0);
        }
        System.out.println("Table created successfully");
    }
    static void insertEnglishWords() {
        Connection c = null;
        Statement stmt = null;
        ArrayList<String> words_array = ArrayOfWords.readInArray();
        try {
            Class.forName("org.sqlite.JDBC");
            c = DriverManager.getConnection("jdbc:sqlite:EnglishWords.db");
            c.setAutoCommit(false);
            System.out.println("Opened database successfully");

            stmt = c.createStatement();
            String sql;
            for(String word:words_array) {
            sql = "INSERT INTO Words (word) VALUES ('"+word+"');";
            stmt.executeUpdate(sql);}

            stmt.close();
            c.commit();
            c.close();
        } catch (Exception e) {
            System.err.println(e.getClass().getName() + ": " + e.getMessage());
            System.exit(0);
        }
        System.out.println("Records created successfully");
    }


}
