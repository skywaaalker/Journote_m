import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.DataInput;
import java.io.IOException;
import java.io.PrintWriter;
import java.text.SimpleDateFormat;
import java.util.Date;

@WebServlet(name = "/notificationupdateservlet.do")
public class NotificationUpdateServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doGet(request, response);
        response.setContentType("text/html;charset=utf-8");
        response.setCharacterEncoding("UTF-8");
        request.setCharacterEncoding("UTF-8");

        NotificationUpdate mNotificationUpdate = new NotificationUpdate();
        String journoteTag = request.getParameter("journoteTag");
        String date_str = request.getParameter("date_str");
        //Date date = new Date();

        String result = "success";
        mNotificationUpdate.isNotificationUpdate(journoteTag, date_str);

        PrintWriter out = response.getWriter();
        out.write(result);
        out.flush();
        out.close();
        System.out.println(result);

    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }
}
