<%--
  Created by IntelliJ IDEA.
  User: 86189
  Date: 2023/6/13
  Time: 20:04
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>登录游戏</title>
    <style>
        body {
            background-color: #f5f5f5;
            font-family: Arial, sans-serif;
        }

        #bigLoginFrame {
            background-color: #ffffff;
            border: 2px solid #ccc;
            border-radius: 5px;
            width: 400px;
            margin: 0 auto;
            padding: 20px;
            box-shadow: 0 2px 4px rgba(0, 0, 0, 0.1);
        }

        h1 {
            margin: 0;
            font-size: 28px;
            color: #555555;
            text-align: center;
        }

        #innerLoginFrame {
            margin-top: 40px;
            font-size: 18px;
        }

        .myText {
            display: block;
            width: 100%;
            padding: 10px;
            font-size: 16px;
            border: 1px solid #ccc;
            border-radius: 3px;
            margin-bottom: 10px;
        }

        input[type="submit"], a {
            display: inline-block;
            width: 140px;
            padding: 10px;
            text-align: center;
            text-decoration: none;
            background-color: #4caf50;
            color: #ffffff;
            border: none;
            border-radius: 3px;
            font-size: 16px;
            margin-top: 10px;
        }

        input[type="submit"]:hover, a:hover {
            background-color: #45a049;
        }

        #tip {
            color: red;
            font-size: 14px;
        }
    </style>
</head>
<body>
<div id="bigLoginFrame" style="border: grey 2px solid">
    <h1>登&nbsp;录&nbsp;游&nbsp;戏</h1>

    <div id="innerLoginFrame" style="font-size: 30px">
        <form action="userLogin" method="post">
            账户：<input type="text" style="font-size: 28px" id="userAccount" name="userAccount" class="myText"><br><br>
            密码：<input type="password" id="userPassword" name="userPassword" class="myText"><br><br>
            <input style="width: 140px;margin-left: 20px" type="submit" value="登录" onclick="return checkLogin()">
            <a href="register.jsp" style="width: 140px;margin-left: 20px;"> | &nbsp;注册</a><br>
            <span style="color: red;font-size: 15px" id="tip">${tip}</span>
        </form>
    </div>
</div>
</body>
</html>
<script>
    function checkLogin(){
        let userAccount = document.getElementById("userAccount").value;
        let userPassword =document.getElementById("userPassword").value;
        if(userAccount==null||userAccount.trim()==""){
            alert("账户不能为空");
            document.getElementById("tip").innerHTML = "账户不能为空";
            return false;
        }
        if(userPassword==null||userPassword.trim()==""){
            alert("密码不能为空");
            document.getElementById("tip").innerHTML = "密码不能为空";
            return false;
        }
    }
</script>