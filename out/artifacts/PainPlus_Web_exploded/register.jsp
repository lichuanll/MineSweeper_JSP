<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html>
<head>
    <title>用户注册</title>
    <style>
        body {
            font-family: Arial, sans-serif;
            background-color: #f2f2f2;
        }

        .container {
            max-width: 400px;
            margin: 0 auto;
            padding: 20px;
            background-color: #fff;
            box-shadow: 0 0 5px rgba(0, 0, 0, 0.1);

        }

        h1 {
            text-align: center;
            margin-bottom: 30px;
        }

        form {
            margin-bottom: 20px;
        }

        label {
            display: block;
            margin-bottom: 5px;
        }

        input[type="text"],
        input[type="password"],
        input[type="email"] {
            width: 100%;
            padding: 10px;
            margin-bottom: 10px;
            border: 1px solid #ccc;
            border-radius: 4px;
            box-sizing: border-box;
        }

        input[type="submit"] {
            width: 100%;
            padding: 10px;
            background-color: #4CAF50;
            color: #fff;
            border: none;
            border-radius: 4px;
            cursor: pointer;
        }

        input[type="submit"]:hover {
            background-color: #45a049;
        }
    </style>
</head>
<body>
<div class="container">
    <h1>用户注册</h1>
    <form action="userSignUp" method="post">
        <label for="username">用户名:</label>
        <input type="text" id="username" name="username" required>

        <label for="password">密码:</label>
        <input type="password" id="password" name="password" required>

        <label for="ConfirmPassword">确认密码:</label>
        <input type="password" id="ConfirmPassword" name="ConfirmPassword" required>

        <input type="submit" value="注册" onclick="return checkLogin()">
        <span style="color: red;font-size: 15px" id="tip">${tip}</span>
    </form>
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