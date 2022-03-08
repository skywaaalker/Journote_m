import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;

public class journoteDelete {
    String driver = "com.mysql.jdbc.Driver";
    String url = "jdbc:mysql://localhost:3306/Journote";
    String usr = "root";
    String pwd = "brunomars1998032";

    public void isjournalDelete(String tag) {

        //String sql = "select * from usrInfo where username='"+username+"'";
        try {
            Class.forName(driver);
            Connection connection = DriverManager.getConnection(url, usr, pwd);
            Statement statement = connection.createStatement();
            //ResultSet resultSet = statement.executeQuery(sql);
            //String sql = "update journote set content = "+content+",title="+title+"where tag="+tag;
            String sql = "delete from journote where tag ='"+tag+"'";
            statement.execute(sql);
//            if(!resultSet.next()) {
//                String sql1 = "insert into usrInfo(username, password) values('"+username+"','"+password+"')";
//                statement.execute(sql1);
//                isValid = true;
//            }
//            resultSet.close();
            statement.close();
            connection.close();
        }catch (Exception e) {
            e.printStackTrace();
            System.out.println(e);
        }
    }

}