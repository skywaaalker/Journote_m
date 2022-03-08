import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;

public class journalInsert {
    String driver = "com.mysql.jdbc.Driver";
    String url = "jdbc:mysql://localhost:3306/Journote";
    String usr = "root";
    String pwd = "brunomars1998032";

    public boolean isjournalInsert(String username, String title, String content) {
        boolean isValid = false;

        String sql = "select * from journote where username='"+username+"'and title='"+title+"'and content='"+content+"'";
        try {
            Class.forName(driver);
            Connection connection = DriverManager.getConnection(url, usr, pwd);
            Statement statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery(sql);

            String date = GetDate.getDate().toString();
            String tag = String.valueOf(System.currentTimeMillis());

            if(!resultSet.next()) {
                String sql1 = "insert into journote(title, content,date , isjournal, hasnoti, tag, label, username) values('"+
                        title+"','"+content+"','"+date+"','1','0','"+tag+"','0','"+username+"')";
                statement.execute(sql1);
                isValid = true;
            }else {
                isValid = false;
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