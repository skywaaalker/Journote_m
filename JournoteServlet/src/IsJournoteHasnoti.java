import com.sun.org.apache.regexp.internal.RE;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;

public class IsJournoteHasnoti {
    String driver = "com.mysql.jdbc.Driver";
    String url = "jdbc:mysql://localhost:3306/Journote";
    String usr = "root";
    String pwd = "brunomars1998032";

    public boolean isJournoteHasnoti(String journoteTag){
        boolean hasnoti = false;
        String sql = "select * from journote where hasnoti = 1 and tag='"+journoteTag+"'";
        try {
            Class.forName(driver);
            Connection connection = DriverManager.getConnection(url, usr, pwd);
            Statement statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery(sql);
            if (resultSet.next()){
                hasnoti = true;
            } else {
                hasnoti = false;
            }
        } catch (Exception e){
            e.printStackTrace();
        }
        return hasnoti;
    }
}
