<%-- 
    Document   : index
    Created on : Mar 18, 2021, 6:54:49 PM
    Author     : ENVY
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
        <style>
            #ln{
                floar: right;
            }
            body{
                background-image: url('img/bkg.jpg');
                background-repeat: no repeat;
            }
            td{
                background-color: white;
            }
        </style>
    </head>
    <body>
        <h1>Registration form</h1>
        <img src="img/logo.jpg"/>
            <form name="login" action="Check" method="POST">
                    <table border="1" width="500" cellspacing="3">
                        <tbody>
                            <tr>
                                <td>Enter login:</td>
                                <td><input type="text" name="login" value="" /></td>
                                <td><input type="submit" value="Register" name="register" /></td>
                            </tr>
                            <tr>
                                <td>Enter password:</td>
                                <td><input type="password" name="psw" value="" /></td>
                                <td><input type="submit" value="Get message:" name="getmsg" /></td>
                            </tr>
                        </tbody>
                    </table>
            </form>
        
            <%
            String registered = (String) request.getAttribute("registered");
            //String loginName = (String) request.getAttribute("loginName");
            String loginName = request.getParameter("login");
            //String getmsg = request.getParameter("getmsg");
            String message = (String) request.getAttribute("message");
            %>
            
            <%
            if (request.getParameter("getmsg") != null ) { //&& "t".equals(registered)
                %>
                    <h2> Вам сообщение:  <%= message%></h2>
                <%
            }
             else {
                if ("t".equals(registered)) {
                %> 
                <h2> Приветствуем, пользователь  <%= loginName%> ! </h2>
                <%
                }
                %>  
 
                <%    
                    if("t".equals(registered)){
                %> 
                <h2> Вы успешно зарегистрировались</h2>

                <%    }
                    if("f".equals(registered)){
                %>
                <h2> Ошибка в логине или пароле</h2>
                <%
                    }
                %>  

        <%
            }
        %>
    </body>
</html>
