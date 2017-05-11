import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;

class EnglishWordsSelect {
    public static void main(String[] args) {
        selectWord("cell");
    }

    static void selectWord(String inputWord) {
        Connection c = null;
        Statement stmt = null;
        try {
            Class.forName("org.sqlite.JDBC");
            c = DriverManager.getConnection("jdbc:sqlite:EnglishWords.db");
            c.setAutoCommit(false);
            stmt = c.createStatement();
            String sql = "SELECT * FROM Words where word LIKE '" + inputWord + "%';";
            long startTime = System.currentTimeMillis();
            ResultSet rs = stmt.executeQuery(sql);
            long timeSpent = System.currentTimeMillis();

            while (rs.next()) {
                System.out.println(rs.getString("word"));
            }
            System.out.println("run time is " + (timeSpent - startTime));
            rs.close();
            stmt.close();
            c.close();
        } catch (Exception e) {
            System.err.println(e.getClass().getName() + ": " + e.getMessage());
            System.exit(0);
        }
    }
}

