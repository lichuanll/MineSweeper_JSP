import javax.servlet.*;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;


@WebServlet("/userSignUp")
public class Register extends HttpServlet {
    SQL_Server sql_server = new SQL_Server();
    boolean LookUpID(String UserName)
    {
        List<String> list;
        String sql="select * from Player where UserName='"+UserName+"'";
        list = sql_server.lookup(sql,1);
        //System.out.println(list+"1");
        if(list.size()==0)
        {
            //System.out.println(list);
            return false;
        }
        return true;
    }
    void AddID(String UserName,String PassWord)
    {
        String sql="insert into Player values ('"+UserName+"','"+PassWord+"')";
        sql_server.update(sql);
    }

    @Override
    protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        String UserName = req.getParameter("username");
        String PassWord = req.getParameter("password");
        String ConfirmPassword = req.getParameter("ConfirmPassword");
        boolean flag=LookUpID(UserName);
        req.setAttribute("username",55);
        //System.out.println(flag);
        if(flag)
        {
            req.setAttribute("tip","该用户名已存在");
            req.getRequestDispatcher("register.jsp").forward(req,resp);
        }
        else if(UserName.length()<2||UserName.length()>20)
        {
            req.setAttribute("tip","用户名长度需要在4-20个字符");
            req.getRequestDispatcher("register.jsp").forward(req,resp);
            //System.out.println("yh");
        }
        else if(PassWord.length()<6||PassWord.length()>16)
        {
            req.setAttribute("tip","密码长度需要在4-20个字符");
            req.getRequestDispatcher("register.jsp").forward(req,resp);
            //System.out.println("mima");
        }
        else if(!PassWord.equals(ConfirmPassword))
        {
            req.setAttribute("tip","两次输入的密码不一致");
            req.getRequestDispatcher("register.jsp").forward(req,resp);
            //System.out.println("no");
        }
        else
        {
            AddID(UserName,PassWord);
            req.getRequestDispatcher("userLogIn.jsp").forward(req,resp);
            //System.out.println("add");
        }
    }
}
