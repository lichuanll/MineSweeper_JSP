import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.*;

@WebServlet("/RankList")
public class HistoryUpdate extends HttpServlet {
    static String username;
    SQL_Server sql_server =new SQL_Server();
    void UpdateHistory(HttpServletRequest request, HttpServletResponse response){
        String sql = "select * from History_Score where Player="+"'"+username+"'";
        List<String> history = sql_server.lookup(sql,2);
        List<String> timeuse = sql_server.lookup(sql,3);
        //System.out.println(history);
        //System.out.println(timeuse);

        request.setAttribute("user",username);
        request.setAttribute("HistoryCount",Integer.toString(history.size()));
        request.setAttribute("data",history);
        int count=history.size();
        for(int i=0;i<count;i++)
        {
            String index =Integer.toString(i+1);
            request.setAttribute("score"+index,history.get(i));
            request.setAttribute("time"+index,timeuse.get(i));
           // System.out.println(history.get(i)+","+timeuse.get(i));
        }

    }
    void UpdateScoreRankList(HttpServletRequest request,HttpServletResponse response)
    {
        String sql="SELECT Player, MAX(CAST(score AS INT)) AS MaxScore FROM History_Score GROUP BY Player ORDER BY MaxScore DESC";
        Map<String, Integer> SocreRankList = sql_server.lookup1(sql,"MaxScore");

        //System.out.println(SocreRankList);
        List<Map.Entry<String, Integer>> sortedEntries = new ArrayList<Map.Entry<String,Integer>>(SocreRankList.entrySet());

         //使用Comparator和Collections.sort()对元素进行排序（从大到小）
        Collections.sort(sortedEntries, new Comparator<Map.Entry<String,Integer>>(){
            public int compare(Map.Entry<String,Integer>me1,Map.Entry<String,Integer>me2)
            {
                return me2.getValue().compareTo(me1.getValue());
            }
        });
        //System.out.println(sortedEntries);
        int index=1;
        for (Map.Entry<String, Integer> entry : sortedEntries) {
            String player = entry.getKey();
            Integer score = entry.getValue();
            request.setAttribute("user"+Integer.toString(index),player);
            request.setAttribute("MaxScore"+Integer.toString(index),score);
            //System.out.println(player+":"+score);
            index++;
            //System.out.println("Player: " + player + ", Score: " + score);
        }
//        for (Map.Entry<String, Integer> entry : SocreRankList.entrySet()) {
//            String player = entry.getKey();
//            int score = entry.getValue();
//            request.setAttribute("user"+Integer.toString(index),player);
//            request.setAttribute("MaxScore"+Integer.toString(index),score);
//            System.out.println(player+":"+score);
//            index++;
//        }
    }
    void UpdateTimeRankList(HttpServletRequest request,HttpServletResponse response)
    {
        String sql="SELECT Player, MAX(CASE WHEN TimeUse = 'fail' THEN NULL ELSE CAST(TimeUse AS INT) END) AS MinTimeUse FROM History_Score GROUP BY Player ORDER BY MinTimeUse ASC;";
        Map<String,Integer> TimeUseRankList=sql_server.lookup1(sql,"MinTimeUse");
        List<Map.Entry<String, Integer>> entries = new ArrayList<>(TimeUseRankList.entrySet());
        System.out.println(TimeUseRankList);
         //根据分数进行升序排序（将 null 视为最大值）
        Collections.sort(entries, Comparator.comparing(Map.Entry::getValue, (s1, s2) -> {
            if (s1 == 0 && s2 == 0) {
                return 0;
            } else if (s1 == 0) {
                return 1;  // 将 null 视为最大值，排在其他非 null 值之后
            } else if (s2 == 0) {
                return -1; // 将 null 视为最大值，排在其他非 null 值之后
            } else {
                return s1.compareTo(s2); // 从小到大排序
            }
        }));
        System.out.println(entries);
        int index=1;
        for (Map.Entry<String, Integer> entry : entries) {
            String player = entry.getKey();
            Integer time = entry.getValue();
            if(time==0)
            {
                request.setAttribute("MinTime"+Integer.toString(index),"fail");
            }
            else
            {
                request.setAttribute("MinTime"+Integer.toString(index),time);
            }

            request.setAttribute("user2"+Integer.toString(index),player);
            //System.out.println(player+":"+score);
            System.out.println(player+":"+time);
            index++;
        }

    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
    String score = request.getParameter("score");
    String TimeLong = request.getParameter("TimeLong");
    request.setAttribute("base64Image",LoadImage.base64Image);
    request.setAttribute("base64Image2", LoadImage2.base64Image);
    //System.out.println(score+" "+TimeLong);
    String sql ="insert into History_Score values ('"+username+"','"+score+"','"+TimeLong+"')";
    //System.out.println(sql1);
    sql_server.update(sql);
    UpdateHistory(request,response);
    UpdateScoreRankList(request,response);
    UpdateTimeRankList(request,response);
    //String sql2="select max(convert(int,score)) as MaxScore from History_Score where Player="+"'"+username+"'";
    //String sql1="DELETE FROM History_Score";
    //sql_server.update(sql1);

    RequestDispatcher dispatcher = request.getRequestDispatcher("Minsweeper.jsp");
    dispatcher.forward(request, response);
        //List<String> list = sql_server.lookup(sql2,4);
}
}
