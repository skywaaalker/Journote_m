import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.Date;

public class NotificationInsert {
    String driver = "com.mysql.jdbc.Driver";
    String url = "jdbc:mysql://localhost:3306/Journote";
    String usr = "root";
    String pwd = "brunomars1998032";

    public boolean isNotificationInsert(String journoteTag, String date_str){
        boolean isValid = false;

        String sql1 = "select * from notification where journoteTag ='"+journoteTag+"'";

        try {
            Class.forName(driver);
            Connection connection = DriverManager.getConnection(url, usr, pwd);
            Statement statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery(sql1);


            if(!resultSet.next()){
                String sql = "insert into notification(journoteTag, date_str) values('"+
                        journoteTag+"','"+ date_str + "')";

                statement.execute(sql);
                //是否需要设置两个statement?
                isValid = true;
            }else {
                isValid = false;
            }
            resultSet.close();
            statement.close();
            connection.close();
        }catch (Exception e){
            e.printStackTrace();
        }
        try {
            Class.forName(driver);
            Connection connection = DriverManager.getConnection(url, usr, pwd);
            Statement statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery(sql1);

            String sql = "update journote set hasnoti = 1 where tag='"+journoteTag+"'";
            statement.execute(sql);

            resultSet.close();
            statement.close();
            connection.close();
        }catch (Exception e){
            e.printStackTrace();
        }
        if (isValid){
            return true;
        } else {
            return false;
        }
    }
}
