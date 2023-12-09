
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>扫雷大作战</title>
    <link rel="stylesheet" href="css/index.css">
    <style>
        .images
        {
            position: center;
        }
        .upload-form {
            position: center;
            display: flex;
            align-items: center;
            margin-top: 20px;
        }

        .upload-label {
            position: center;
            font-size: 18px;
            margin-right: 10px;
        }

        .upload-input {
            position: center;
            padding: 5px;
        }

        .upload-button {
            position: center;
            padding: 8px 16px;
            background-color: #4CAF50;
            color: white;
            border: none;
            cursor: pointer;
        }



        .upload-button:hover {
            background-color: #45a049;

        }
        td>div.mine {
            background: #ccc url(data:image/*;base64,${base64Image}) center/cover no-repeat;
            opacity: 0.3;
        }
        td>div.flag {
            background: #ccc url(data:image/*;base64,${base64Image2}) center/cover no-repeat;
            opacity: 1;
        }
        td>div.error{
            background-color: rgb(216,0,0);
        }
    </style>
</head>
<body>

<!-- 游戏最外层容器 -->
<div class="container1">
    <!-- 游戏标题 -->
    <h1>扫雷大作战</h1>
    <!-- 游戏难度选择 -->
    <div class="level">
        <button class="active">初级</button>
        <button>中级</button>
        <button>高级</button>
    </div>
    <div class="big">
        <div class="info1">分数: <span class="score"></span></div>
        <div class="info2">用时:<span class="time"></span></div>
        <!-- 扫雷区域，应该通时 JS 动态来生成 -->
        <div class="mineArea"></div>
        <div class="Rank2">
            <h2 style="
                text-align: center;
                color: #333;
                margin-bottom: 5px;">用时排行榜</h2>
            <h3 style="text-align: center;" id="RankList2">时间管理十强</h3>


            <table id="RankListTable2" style=" width: 100%;border-collapse: collapse;">
                <tr>
                    <th>排名</th>
                    <th>玩家</th>
                    <th>用时</th>
                </tr>
                <tr>
                    <td>1</td>
                    <td>${user21}</td>
                    <td>${MinTime1}</td>
                </tr>
                <tr>
                    <td>2</td>
                    <td>${user22}</td>
                    <td>${MinTime2}</td>
                </tr>
                <tr>
                    <td>3</td>
                    <td>${user23}</td>
                    <td>${MinTime3}</td>
                </tr>
                <tr>
                    <td>4</td>
                    <td>${user24}</td>
                    <td>${MinTime4}</td>
                </tr>
                <tr>
                    <td>5</td>
                    <td>${user25}</td>
                    <td>${MinTime5}</td>
                </tr>
                <tr>
                    <td>6</td>
                    <td>${user26}</td>
                    <td>${MinTime6}</td>
                </tr>
                <tr>
                    <td>7</td>
                    <td>${user27}</td>
                    <td>${MinTime7}</td>
                </tr>
                <tr>
                    <td>8</td>
                    <td>${user28}</td>
                    <td>${MinTime8}</td>
                </tr>
                <tr>
                    <td>9</td>
                    <td>${user29}</td>
                    <td>${MinTime9}</td>
                </tr>
                <tr>
                    <td>10</td>
                    <td>${user210}</td>
                    <td>${MinTime10}</td>
                </tr>
            </table>
        </div>
        <div class="Rank">
            <h2 style="
                text-align: center;
                color: #333;
                margin-bottom: 5px;">分数排行榜</h2>
            <h3 style="text-align: center;" id="RankList">分数十强</h3>


            <table id="RankListTable" style=" width: 100%;border-collapse: collapse;">
                <tr>
                    <th>排名</th>
                    <th>玩家</th>
                    <th>分数</th>
                </tr>
                <tr>
                    <td>1</td>
                    <td>${user1}</td>
                    <td>${MaxScore1}</td>
                </tr>
                <tr>
                    <td>2</td>
                    <td>${user2}</td>
                    <td>${MaxScore2}</td>
                </tr>
                <tr>
                    <td>3</td>
                    <td>${user3}</td>
                    <td>${MaxScore3}</td>
                </tr>
                <tr>
                    <td>4</td>
                    <td>${user4}</td>
                    <td>${MaxScore4}</td>
                </tr>
                <tr>
                    <td>5</td>
                    <td>${user5}</td>
                    <td>${MaxScore5}</td>
                </tr>
                <tr>
                    <td>6</td>
                    <td>${user6}</td>
                    <td>${MaxScore6}</td>
                </tr>
                <tr>
                    <td>7</td>
                    <td>${user7}</td>
                    <td>${MaxScore7}</td>
                </tr>
                <tr>
                    <td>8</td>
                    <td>${user8}</td>
                    <td>${MaxScore8}</td>
                </tr>
                <tr>
                    <td>9</td>
                    <td>${user9}</td>
                    <td>${MaxScore9}</td>
                </tr>
                <tr>
                    <td>10</td>
                    <td>${user10}</td>
                    <td>${MaxScore10}</td>
                </tr>
            </table>
        </div>
    </div>
    <div class="images">
        <form action="loadImage" method="post" enctype="multipart/form-data" class="upload-form">
            <label for="image" class="upload-label">选择雷的皮肤:</label>
            <input type="file" name="image" id="image" accept="image/*" class="upload-input">
            <input type="submit" value="穿戴" class="upload-button">
        </form>
        <form action="loadImage2" method="post" enctype="multipart/form-data" class="upload-form">
            <label for="image" class="upload-label">选择旗的皮肤:</label>
            <input type="file" name="image1" id="image1" accept="image/*" class="upload-input">
            <input type="submit" value="穿戴" class="upload-button">
        </form>
    </div>
    <div class="history">
        <h1 style="text-align: center;
            color: #333;
            margin-bottom: 30px;">历史记录</h1>
        <h2 style="text-align: center;" id="historycount">最近10次对局</h2>


        <table id="historytable" style=" width: 100%;border-collapse: collapse;">
            <tr>
                <th>玩家:${user}</th>
                <th>分数</th>
                <th>用时</th>
            </tr>
            <tr>
                <td>1</td>
                <td>${score1}</td>
                <td>${time1}</td>
            </tr>
            <tr>
                <td>2</td>
                <td>${score2}</td>
                <td>${time2}</td>
            </tr>
            <tr>
                <td>3</td>
                <td>${score3}</td>
                <td>${time3}</td>
            </tr>
            <tr>
                <td>4</td>
                <td>${score4}</td>
                <td>${time4}</td>
            </tr>
            <tr>
                <td>5</td>
                <td>${score5}</td>
                <td>${time5}</td>
            </tr>
            <tr>
                <td>6</td>
                <td>${score6}</td>
                <td>${time6}</td>
            </tr>
            <tr>
                <td>7</td>
                <td>${score7}</td>
                <td>${time7}</td>
            </tr>
            <tr>
                <td>8</td>
                <td>${score8}</td>
                <td>${time8}</td>
            </tr>
            <tr>
                <td>9</td>
                <td>${score9}</td>
                <td>${time9}</td>
            </tr>
            <tr>
                <td>10</td>
                <td>${score10}</td>
                <td>${time10}</td>
            </tr>
        </table>
    </div>

</div>
<%--    <a href="History.jsp" style="width: 140px;margin-left: 20px;"> | &nbsp;注册</a>--%>


<form  action="RankList" method="post" id="submit">
    <input type="hidden" id="score" name="score">
    <input type="hidden" id="TimeLong" name="TimeLong">
</form>
<%--    <form  action="lookupHistory" method="post" class="upload-form">--%>
<%--        <input type="submit" value="查看历史记录" class="upload-button">--%>
<%--    </form>--%>

<script src="js/tool.js"></script>
<script src="js/config.js"></script>
<script src="js/index.js"></script>

<script>

</script>
</body>
</html>