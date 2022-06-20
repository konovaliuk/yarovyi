<% String header = "Apache Tomcat";%>
<!DOCTYPE html>
<html>
    <head>
        <meta charset="UTF-8" />
        <title>Servlet Lab App</title>

    </head>
    <body>
        <h3>Login as User</h3>
        <form name="loginForm" method="POST" action="Controller">
                <input type="hidden" name="command" value="login_manager"/>
                Login:<br>
                <input type="text" name="login" value=""/>
                Password:<br>
                <input type="text" name="pass" value=""/>

            </form>
            <a href="login_master.jsp">Login as Master</a>
            <a href="login_manager.jsp">Login as Manager</a>
    </body>
</html>