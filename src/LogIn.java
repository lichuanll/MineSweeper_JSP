import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.util.List;

@WebServlet("/userLogin")
public class LogIn extends HttpServlet {
    SQL_Server sql_server = new SQL_Server();
    boolean IsCanLogIn(String UserName,String PassWord)
    {
        List<String> list;
        String sql = "select * from Player where UserName='" + UserName + "' and Password='" + PassWord + "'";
        list = sql_server.lookup(sql,1);
        if(list.size()==1)
        {
            LoadImage.username=UserName;
            LoadImage2.username=UserName;
            HistoryUpdate.username=UserName;
            return true;
        }
        return false;
    }


    @Override
    protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String userAccount = req.getParameter("userAccount");
        String userPassword = req.getParameter("userPassword");
        if(IsCanLogIn(userAccount,userPassword)){
            req.getRequestDispatcher("Minsweeper.jsp").forward(req,resp);

        }
        else {
            req.setAttribute("tip","账户不存在或密码错误");
            req.getRequestDispatcher("userLogIn.jsp").forward(req,resp);
        }
    }
}
