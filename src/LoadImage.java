import org.apache.tomcat.util.http.fileupload.*;
import org.apache.catalina.connector.RequestFacade;
import org.apache.tomcat.util.http.fileupload.disk.DiskFileItemFactory;
import org.apache.tomcat.util.http.fileupload.servlet.ServletFileUpload;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.InputStream;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.Base64;
import java.util.List;
import java.io.File;
@WebServlet("/loadImage")
public class LoadImage extends HttpServlet {
    static String username;
    static String base64Image;
    SQL_Server sql_server=new SQL_Server();

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String sql="DELETE FROM PalyerImage where Player = '"+username+"'";
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
                sql_server.update(username,fileContent);
                //System.out.println();
                // 处理文件内容
                // ...
            }
        } catch (FileUploadException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        byte[] imageData = sql_server.lookup(username);

        base64Image = Base64.getEncoder().encodeToString(imageData);

        request.setAttribute("base64Image", base64Image);
        request.setAttribute("base64Image2", LoadImage2.base64Image);
        //System.out.println("daozhelile");
        RequestDispatcher dispatcher = request.getRequestDispatcher("Minsweeper.jsp");
        dispatcher.forward(request, response);
        //doGet(request,response);
//        if (isMultipart) {
//            // 创建文件上传处理工厂和上传对象
//            DiskFileItemFactory factory = new DiskFileItemFactory();
//            ServletFileUpload upload = new ServletFileUpload(factory);
//
//            try {
//                // 解析上传的表单字段
//                List<FileItem> items = upload.parseRequest(request);
//
//                for (FileItem item : items) {
//                    if (!item.isFormField()) { // 处理文件字段
//                        InputStream fileContent=item.getInputStream();
//                        sql_server.update(username,fileContent);
//                        // 将文件路径保存到数据库
//
//                    }
//                }
//
//                response.getWriter().println("File uploaded successfully!");
//            } catch ( Exception e) {
//                response.getWriter().println("File upload failed!" + e.getMessage());
//            }
//        }

    }

//    @Override
//    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
//        InputStream imageData = sql_server.lookup(username);
//        String base64Image = convertImageToBase64(imageData);
//        req.setAttribute("base64Image", base64Image);
//        System.out.println("daozhelile");
//        RequestDispatcher dispatcher = req.getRequestDispatcher("Minsweeper.jsp");
//        dispatcher.forward(req, resp);
//    }
}

