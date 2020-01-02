package mysql.benchmark;


import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.logging.Level;
import java.util.logging.Logger;

public class VersionTest {
    public static void main(String[] args) {

        String url = "jdbc:mysql://localhost:3306/ycsb?useSSL=false&useUnicode=true&useJDBCCompliantTimezoneShift=true&useLegacyDatetimeCode=false&serverTimezone=UTC";
        String user = "root";
        String password = "lwh791209";

        String query = "SELECT VERSION()";

        try {
            Connection con = DriverManager.getConnection(url, user, password);
            Statement st = con.createStatement();
            long begin = System.currentTimeMillis();
            for (int i = 0; i < 1000000; i++) {
                ResultSet rs = st.executeQuery(query);

                if (rs.next() && (i % 10000 == 0)) {
                    System.out.println(i + "\t" + rs.getString(1) + "\t" + (System.currentTimeMillis() - begin));
                    begin = System.currentTimeMillis();
                }
            }

        } catch (SQLException ex) {
            Logger lgr = Logger.getLogger(VersionTest.class.getName());
            lgr.log(Level.SEVERE, ex.getMessage(), ex);
        }
    }
}