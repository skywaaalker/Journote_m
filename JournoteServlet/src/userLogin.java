import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;

public class userLogin {

    String driver = "com.mysql.jdbc.Driver";
    String url = "jdbc:mysql://localhost:3306/Journote";
    String usr = "root";
    String pwd = "brunomars1998032";

    public boolean isuserLogin(String username, String password) {
        boolean isValid = false;

        String sql = "select * from usrInfo where username='"+username+"'and password='"+password+"'";
        try {
            Class.forName(driver);
            Connection connection = DriverManager.getConnection(url, usr, pwd);
            Statement statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery(sql);

            if(resultSet.next()) {
                isValid = true;
            }
            resultSet.close();
            statement.close();
            connection.close();
        }catch (Exception e) {
            e.printStackTrace();
            System.out.println(e);
        }
        if(isValid) {
            return true;
        }else {
            return false;
        }
    }
}
