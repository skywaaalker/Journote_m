import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.Statement;

public class NotificationDelete {
    String driver = "com.mysql.jdbc.Driver";
    String url = "jdbc:mysql://localhost:3306/Journote";
    String usr = "root";
    String pwd = "brunomars1998032";

    public void isNotificationDelete(String journoteTag) {
        try {
            Class.forName(driver);
            Connection connection = DriverManager.getConnection(url, usr, pwd);
            Statement statement = connection.createStatement();
            String sql1 = "delete from notification where journoteTag='"+journoteTag+"'";
            String sql2 = "update journote set hasnoti = 0 where tag = '"+ journoteTag + "'";
            statement.execute(sql1);
            statement.execute(sql2);

            statement.close();
            connection.close();
        } catch (Exception e){
            e.printStackTrace();
        }
    }
}
