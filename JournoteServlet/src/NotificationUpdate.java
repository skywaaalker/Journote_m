import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.Statement;
import java.util.Calendar;
import java.util.Date;

public class NotificationUpdate {
    String driver = "com.mysql.jdbc.Driver";
    String url = "jdbc:mysql://localhost:3306/Journote";
    String usr = "root";
    String pwd = "brunomars1998032";

    public void isNotificationUpdate(String journoteTag, String date_str){
        //boolean isValid = false;

        try{
            Class.forName(driver);
            Connection connection = DriverManager.getConnection(url, usr, pwd);
            Statement statement = connection.createStatement();
            String sql = "update notification set date_str = '"+ date_str + "'where journoteTag ='"+journoteTag+"'";
            statement.execute(sql);
            statement.close();
            connection.close();

        } catch (Exception e){
            e.printStackTrace();
        }
    }
}
