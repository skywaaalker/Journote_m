import javax.swing.plaf.nimbus.State;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class NotificationQuery {
    String driver = "com.mysql.jdbc.Driver";
    String url = "jdbc:mysql://localhost:3306/Journote";
    String usr = "root";
    String pwd = "brunomars1998032";

    public List<NotificationBean> getNotification(String journoteTag){
        List<NotificationBean> mNotificationList =  new ArrayList<NotificationBean>();
        String sql = "select * from notification where journoteTag='"+journoteTag+"'";
        try {
            Class.forName(driver);
            Connection connection = DriverManager.getConnection(url, usr, pwd);
            Statement statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery(sql);
            if(resultSet != null){
                while (resultSet.next()){
                    NotificationBean notificationBean = new NotificationBean();
                    notificationBean.setDateStr(resultSet.getString("date_str"));
                    notificationBean.setJournoteTag(resultSet.getString("journoteTag"));
                    mNotificationList.add(notificationBean);
                }
            }
            resultSet.close();
            statement.close();
            connection.close();
        } catch (Exception e){
            e.printStackTrace();
        }
        return mNotificationList;
    }
}
