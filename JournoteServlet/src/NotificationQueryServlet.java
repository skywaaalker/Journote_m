import net.sf.json.JSON;
import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;

@WebServlet(name = "/notificationqueryservlet.do")
public class NotificationQueryServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doGet(request, response);
        response.setContentType("text/plain;charset=utf-8");
        response.setCharacterEncoding("UTF-8");
        request.setCharacterEncoding("UTF-8");

        List<NotificationBean> mNotificationBeanList = new ArrayList<NotificationBean>();
        NotificationQuery mNotificationQuery = new NotificationQuery();

        String journoteTag = request.getParameter("journoteTag");
        String result = "";

        mNotificationBeanList = mNotificationQuery.getNotification(journoteTag);
        JSONArray jsonArray = new JSONArray();
        for(NotificationBean bean:mNotificationBeanList){
            JSONObject jsonObject = new JSONObject();
            try {
                jsonObject.put("journoteTag", bean.getJournoteTag());
                jsonObject.put("date_str", bean.getDateStr());
            } catch (Exception e){
                e.printStackTrace();
            }
            jsonArray.add(jsonObject);
        }
        PrintWriter out = response.getWriter();
        out.write(jsonArray.toString());
        out.flush();
        out.close();
        System.out.println(result);
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }
}
