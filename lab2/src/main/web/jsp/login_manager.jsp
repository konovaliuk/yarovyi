<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html lang="en">
    <head>
        <meta charset="UTF-8">
        <title>Servlet Lab App</title>

    </head>
    <body>
        <h3>Login as Manager</h3>

        <form name="loginForm" method="POST" action="Controller">
            <input type="hidden" name="command" value="login_manager"/>
            Login:<br>
            <input type="text" name="login" value=""/>

        </form>
        <a href="login_user.jsp">Login as User</a>
        <a href="login_master.jsp">Login as Master</a>
    </body>
</html>