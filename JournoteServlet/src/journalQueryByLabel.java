import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class journalQueryByLabel {
    String driver = "com.mysql.jdbc.Driver";
    String url = "jdbc:mysql://localhost:3306/Journote";
    String usr = "root";
    String pwd = "brunomars1998032";
    //private List<JournoteBean> mJournoteBeanList;

    public List<JournoteBean> getJournalByLabel(String username, int label) {
        //boolean isValid = false;
        List<JournoteBean> mJournoteBeanList = new ArrayList<JournoteBean>();
        //List<JournoteBean> journallist =  new ArrayList<JournoteBean>();
        String sql = "select * from journote where isjournal = 1 and username='"+username+"'and label ="+label+" order by tag desc";
        try {
            Class.forName(driver);
            Connection connection = DriverManager.getConnection(url, usr, pwd);
            Statement statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery(sql);

            if(resultSet !=null){
                while (resultSet.next()){
                    JournoteBean journoteBean = new JournoteBean();
                    journoteBean.setTitle(resultSet.getString("title"));
                    journoteBean.setContent(resultSet.getString("content"));
                    journoteBean.setDate(resultSet.getString("date"));
                    journoteBean.setHasnoti(resultSet.getInt("hasnoti"));
                    journoteBean.setIsjournal(resultSet.getInt("isjournal"));
                    journoteBean.setLabel(resultSet.getInt("label"));
                    journoteBean.setTag(resultSet.getString("tag"));
                    journoteBean.setUsername(resultSet.getString("username"));
                    mJournoteBeanList.add(journoteBean);
                }
            }
            resultSet.close();
            statement.close();
            connection.close();
        }catch (Exception e) {
            e.printStackTrace();
            System.out.println(e);
        }
        //mJournoteBeanList = journallist;
        return mJournoteBeanList;
    }
}
