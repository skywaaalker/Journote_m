import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.text.SimpleDateFormat;
import java.util.Date;

@WebServlet(name = "/notificationinsertservlet.do")
public class NotificationInsertServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doGet(request, response);
        response.setContentType("text/html;charset=utf-8");
        response.setCharacterEncoding("UTF-8");
        request.setCharacterEncoding("UTF-8");


        NotificationInsert mNotificationInsert =  new NotificationInsert();
        boolean b;
        String result;
        String journoteTag = request.getParameter("journoteTag");
        String date_str = request.getParameter("date_str");
        //从表单中获得的是date的字符串格式，需要再一次转换；
        //Date date = new Date();
        b = mNotificationInsert.isNotificationInsert(journoteTag, date_str);
        if (b) {result = "success";} else {result = "fail";}
        PrintWriter out = response.getWriter();
        out.write(result);

        out.flush();
        out.close();
        System.out.println(result);
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }
}
