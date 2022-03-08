import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.Statement;

public class LabelUpdate {
    String driver = "com.mysql.jdbc.Driver";
    String url = "jdbc:mysql://localhost:3306/Journote";
    String usr = "root";
    String pwd = "brunomars1998032";

    public void isLabelUpdate(String journoteTag, int label){
        try{
            Class.forName(driver);
            Connection connection =  DriverManager.getConnection(url, usr, pwd);
            Statement statement = connection.createStatement();
            String sql = "update journote set label='"+label+"'where tag = '"+journoteTag+"'";
            statement.execute(sql);
            statement.close();
            connection.close();

        } catch (Exception e){
            e.printStackTrace();
        }
    }
}
