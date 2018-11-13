<%-- 
    Document   : valid_authentification
    Created on : Oct 19, 2018, 8:39:02 PM
    Author     : carolek
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Change Password</title>
        <link type="text/css" rel="stylesheet" href="style2.css" />
        <link href="https://stackpath.bootstrapcdn.com/bootstrap/4.1.3/css/bootstrap.min.css" rel="stylesheet" 
              integrity="sha384-MCw98/SFnGE8fJT3GXwEOngsV7Zt27NXFoaoApmYm81iuXoPkFOJwJ8ERdknLPMO" crossorigin="anonymous">
    </head>
    <body>
    <h1>Kosta PasswortaenderungTool</h1>    
        <div class="wrapper">
            <form name="Password Form" action="changePWServlet" method="POST" class="form-signin">
                <h3 class="form-signin-heading">Password Form</h3>
                <div class="container">
                    <p>${message_error}</p>
                </div>
                <p>${message_success}</p>
                <input type="password" placeholder="Enter New Password" id="new_pwd1" name="new_password1" class="form-control" required autofocus><br>
                <input type="password" placeholder="Re-enter New Password" id="new_pwd2" name="new_password2" class="form-control" required><br>
                <button class="btn btn-lg btn-primary btn-block" type="submit">Submit</button>
            </form>
        </div>
    </body>
</html>
