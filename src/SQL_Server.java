import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.sql.*;
import java.util.*;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class SQL_Server {

    void update(String username, InputStream file)
    {
        String dbURL="jdbc:sqlserver://localhost:1433;DatabaseName=Minsweeper";
        String userName="sa";
        String userPwd="yesuaiwo123";
        //String sql="insert into PlayerImage (ImageData) values ('"+username+"',"+"?";
        String sql = "INSERT INTO PalyerImage (Player, ImageData) VALUES (?, ?)";
        Connection con;

        try {
            // 加载驱动程序
            Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }

        try {

            con = DriverManager.getConnection(dbURL,userName,userPwd);
            PreparedStatement statement = con.prepareStatement(sql);
            statement.setString(1, username); // 设置第一列的字符串值

            byte[] buffer = new byte[4096];
            ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
            int bytesRead;
            while ((bytesRead = file.read(buffer)) != -1) {
                byteArrayOutputStream.write(buffer, 0, bytesRead);
            }
            byte[] imageBytes = byteArrayOutputStream.toByteArray();
            //System.out.println("imgebyte"+imageBytes);
            statement.setBytes(2, imageBytes);
            int rowsInserted = statement.executeUpdate();

            if (rowsInserted > 0) {
                System.out.println("File saved to database successfully!");
            } else {
                System.out.println("Failed to save file to database.");
            }
            statement.close();
            con.close();
        } catch (SQLException e) {
            e.printStackTrace();
            System.out.println("link Error");
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

    }
    void update2(String username, InputStream file)
    {
        String dbURL="jdbc:sqlserver://localhost:1433;DatabaseName=Minsweeper";
        String userName="sa";
        String userPwd="yesuaiwo123";
        //String sql="insert into PlayerImage (ImageData) values ('"+username+"',"+"?";
        String sql = "INSERT INTO PlayerImage (Player, ImageData) VALUES (?, ?)";
        Connection con;

        try {
            // 加载驱动程序
            Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }

        try {

            con = DriverManager.getConnection(dbURL,userName,userPwd);
            PreparedStatement statement = con.prepareStatement(sql);
            statement.setString(1, username); // 设置第一列的字符串值

            byte[] buffer = new byte[4096];
            ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
            int bytesRead;
            while ((bytesRead = file.read(buffer)) != -1) {
                byteArrayOutputStream.write(buffer, 0, bytesRead);
            }
            byte[] imageBytes = byteArrayOutputStream.toByteArray();
            //System.out.println("imgebyte"+imageBytes);
            statement.setBytes(2, imageBytes);
            int rowsInserted = statement.executeUpdate();

            if (rowsInserted > 0) {
                System.out.println("File saved to database successfully!");
            } else {
                System.out.println("Failed to save file to database.");
            }
            statement.close();
            con.close();
        } catch (SQLException e) {
            e.printStackTrace();
            System.out.println("link Error");
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

    }

    void update(String sql)
    {
        String dbURL="jdbc:sqlserver://localhost:1433;DatabaseName=Minsweeper";
        String userName="sa";
        String userPwd="yesuaiwo123";
        Connection con;
        Statement statement;
        try {
            // 加载驱动程序
            Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
        try {
            con = DriverManager.getConnection(dbURL,userName,userPwd);
            statement = con.createStatement();
            statement.execute(sql);
            statement.close();
            con.close();
        }catch (SQLException e) {
            e.printStackTrace();
            System.out.println("link Error111");
        }

        //String sql2="insert into History_Score values('"+GameComponents.score+"')"
    }
    List<String> lookup(String sql,int col)
    {
        String dbURL="jdbc:sqlserver://localhost:1433;DatabaseName=Minsweeper";
        String userName="sa";
        String userPwd="yesuaiwo123";
        Connection con;
        Statement statement;
        ResultSet resultSet;
        List<String> list=new ArrayList<>();
        try {
            // 加载驱动程序
            Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
        try{
            con = DriverManager.getConnection(dbURL,userName,userPwd);
            statement = con.createStatement();
            resultSet=statement.executeQuery(sql);
            while (resultSet.next()){
                list.add(resultSet.getString(col));
            }
            resultSet.close();
            statement.close();
            con.close();
        }catch(SQLException e){
            e.printStackTrace();
            System.out.println("link Error333");
        }
        return list;
    }
    Map<String, Integer> lookup1(String sql,String ColName)
    {
        String dbURL="jdbc:sqlserver://localhost:1433;DatabaseName=Minsweeper";
        String userName="sa";
        String userPwd="yesuaiwo123";
        Connection con;
        Statement statement;
        ResultSet resultSet;
        Map<String, Integer> playerScores = new HashMap<>();
        try {
            // 加载驱动程序
            Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
        try{
            con = DriverManager.getConnection(dbURL,userName,userPwd);
            statement = con.createStatement();
            resultSet=statement.executeQuery(sql);
            while (resultSet.next()){
                String player = resultSet.getString("Player");
                Integer maxScore = resultSet.getInt(ColName);
                playerScores.put(player, maxScore);
            }
            resultSet.close();
            statement.close();
            con.close();
        }catch(SQLException e){
            e.printStackTrace();
            System.out.println("link Error333");
        }

        return playerScores;
    }

    byte[] lookup(String username)
    {
        String dbURL="jdbc:sqlserver://localhost:1433;DatabaseName=Minsweeper";
        String userName="sa";
        String userPwd="yesuaiwo123";
        Connection con;
        String sql="SELECT ImageData FROM PalyerImage WHERE Player = ?";
        byte[] imageData=null;
        try {
            // 加载驱动程序
            Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
        try {

            con = DriverManager.getConnection(dbURL,userName,userPwd);
            PreparedStatement statement = con.prepareStatement(sql);
            //String imageId = username;
            statement.setString(1,username);
            ResultSet resultSet = statement.executeQuery();
            if(resultSet.next())
            {
                imageData = resultSet.getBytes("ImageData");
            }

            resultSet.close();
            statement.close();
            con.close();
        } catch (SQLException e) {
            e.printStackTrace();
            System.out.println("link Error");
        }
        return imageData;
    }
    byte[] lookup2(String username)
    {
        String dbURL="jdbc:sqlserver://localhost:1433;DatabaseName=Minsweeper";
        String userName="sa";
        String userPwd="yesuaiwo123";
        Connection con;
        String sql="SELECT ImageData FROM PlayerImage WHERE Player = ?";
        byte[] imageData=null;
        try {
            // 加载驱动程序
            Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
        try {

            con = DriverManager.getConnection(dbURL,userName,userPwd);
            PreparedStatement statement = con.prepareStatement(sql);
            //String imageId = username;
            statement.setString(1,username);
            ResultSet resultSet = statement.executeQuery();
            if(resultSet.next())
            {
                imageData = resultSet.getBytes("ImageData");
            }

            resultSet.close();
            statement.close();
            con.close();
        } catch (SQLException e) {
            e.printStackTrace();
            System.out.println("link Error");
        }
        return imageData;
    }

//    void lookup()
//    {
//        int cnt=0;
//        String history_score="历史分数\n";
//        Connection con;
//        Statement st;
//        ResultSet rs;
//        String dbURL="jdbc:sqlserver://localhost:1433;DatabaseName=Minsweeper";
//        String userName="sa";
//        String userPwd="yesuaiwo123";
//        String sql="select * from History_Score";
//        System.out.println("\n");
//        try {
//            con = DriverManager.getConnection(dbURL, userName, userPwd);
//            st = con.createStatement();
//            rs = st.executeQuery(sql);
//            while (rs.next()) {
//                String sno = rs.getString(1);
//                cnt++;
//                history_score+=cnt+".分数:"+sno+"\n";
//            }
//            rs.close();
//            st.close();
//            con.close();
//        }catch(SQLException e){
//            e.printStackTrace();
//            System.out.println("数据库连接失败！！！");
//        }
//        //System.out.println(history_score)
//    }
}

