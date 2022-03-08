import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

@WebServlet(name = "/notificationdeleteservlet.do")
public class NotificationDeleteServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doGet(request, response);
        response.setContentType("text/html;charset=utf-8");
        response.setCharacterEncoding("UTF-8");
        request.setCharacterEncoding("UTF-8");

        NotificationDelete mNotificationDelete = new NotificationDelete();
        String journoteTag = request.getParameter("journoteTag");
        String result = "success";
        mNotificationDelete.isNotificationDelete(journoteTag);

        PrintWriter out = response.getWriter();
        out.write(result);
        //out.write(journoteTag);
        out.flush();
        out.close();
        System.out.println(result);
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }
}
