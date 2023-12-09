import org.apache.tomcat.util.http.fileupload.FileItemIterator;
import org.apache.tomcat.util.http.fileupload.FileItemStream;
import org.apache.tomcat.util.http.fileupload.FileUploadException;
import org.apache.tomcat.util.http.fileupload.servlet.ServletFileUpload;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.InputStream;
import java.util.Base64;
@WebServlet("/loadImage2")
public class LoadImage2 extends HttpServlet {
    static String username;
    static String base64Image;
    SQL_Server sql_server = new SQL_Server();

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String sql = "DELETE FROM PlayerImage where Player = '" + username + "'";
        // 检查请求是否为 multipart/form-data 类型
        //boolean isMultipart = ServletFileUpload.isMultipartContent(request);
        ServletFileUpload upload = new ServletFileUpload();

        // 解析文件上传请求
        FileItemIterator iter = upload.getItemIterator(request);
        try {
            // 创建文件上传解析器
            sql_server.update(sql);

            while (iter.hasNext()) {
                FileItemStream item = iter.next();
                //String fieldName = item.getFieldName();
                InputStream fileContent = item.openStream();
                //System.out.println("filecontent="+fileContent);
                sql_server.update2(username, fileContent);
                //System.out.println();
                // 处理文件内容
                // ...
            }
        } catch (FileUploadException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        byte[] imageData = sql_server.lookup2(username);

        base64Image = Base64.getEncoder().encodeToString(imageData);
        request.setAttribute("base64Image",LoadImage.base64Image);
        request.setAttribute("base64Image2", base64Image);

        //System.out.println("daozhelile");
        RequestDispatcher dispatcher = request.getRequestDispatcher("Minsweeper.jsp");
        dispatcher.forward(request, response);
    }
}