<%-- 
    Document   : add-student-jsp
    Created on : 07-Apr-2023, 2:23:29 pm
    Author     : Acer
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Add Student</title>
        <link type="text/css" rel="stylesheet" href="css/style.css" />
        <link type="text/css" rel="stylesheet" href="css/add-student-style.css" />
    </head>
    <body>
        <h1>Add Student</h1>
        <div id="wrapper">
            <div id="header">
            <h2>User Management Application</h2>
            </div>
        </div>
        <div id="container">
            <h3>Add Student</h3>
            <form action="StudentControllerServlet" method= "POST">
                <input type="hidden" name="command" value="ADD" />
                <table>
                    <tbody>
                        <tr>
                            <td><label>First Name:</label></td>
                            <td><input type="text" name="firstname" /></td>
                        </tr>
                        <tr>
                            <td><label>Last Name:</label></td>
                            <td><input type="text" name="lastname" /></td>
                        </tr>
                        <tr>
                            <td><label>Email:</label></td>
                            <td><input type="text" name="email" /></td>
                        </tr>
                        <tr>
                            <td><label></label></td>
                            <td><input type="submit" name="submit" class="save" /></td>
                        </tr>
                    </tbody>
                </table>
                
            </form>
            
            <div style="clear: both;">
                <p>
                    <a href="StudentControllerServlet">Back to List</a>
                </p>
                
            </div>
        </div>
    </body>
</html>
