<%-- 
    Document   : index
    Created on : Oct 23, 2018, 10:56:09 PM
    Author     : carolek
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>PasswortAenderungTool</title>
        <link type="text/css" rel="stylesheet" href="style2.css">
        <link href="https://stackpath.bootstrapcdn.com/bootstrap/4.1.3/css/bootstrap.min.css" rel="stylesheet" 
              integrity="sha384-MCw98/SFnGE8fJT3GXwEOngsV7Zt27NXFoaoApmYm81iuXoPkFOJwJ8ERdknLPMO" crossorigin="anonymous">
    </head>
    <body>
    <h1>Kosta PasswortaenderungTool</h1>
        <div class="wrapper">
            <form name="Authentification Form" action="authentificationServlet" method="POST" class="form-signin">
                <h3 class="form-signin-heading">Login Form</h3>
                <div class="container">
                    <p>${message_error}</p>
                </div>
                <input type="text" placeholder="Username" id="uname" name="username" class="form-control" required="" autofocus=""><br>
                <input type="password" placeholder="Password" id="pwd" name="password" class="form-control" required=""><br>
                <button class="btn btn-lg btn-primary btn-block" type="submit">Login</button>
            </form>
        </div>
    </body>
</html>
