<%-- 
    Document   : index
    Created on : Dec 1, 2017, 3:57:57 PM
    Author     : z
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
            
         
            
    </head>
    <body>

            <table>
                <tr>
                    <td><label>Nom</label><input id="username" value=""/></td>
                    <td><label>Password</label><input id="password" type="password" value=""/></td>
                    <td><button id="add" >Entrer</button></td>
                    
                </tr>
            </table>
            
        
        <br>
        <br>
        <br>
        
        <button id="target" type="submit">Aficher la liste</button>
        

        <p id="commentaires">
            
        </p>
    
            
                 
        <script src="http://code.jquery.com/jquery-3.2.1.min.js"></script>
        <script src="jquery.md5.js"></script>
 
        
        
           <script>   
        $("#add").click(function(e) {
           e.preventDefault();
           var $name = $("#username").val();
           var $password  = $.md5($("#password").val());
//           $.post( "api/modele.adminentity/check", { username : $name, password : $password });
           
           //$user = new user($name,$password);
           $.ajax({
                    type: "POST",
                    contentType:'application/json',
                    url: "api/modele.adminentity/check",
                    data: JSON.stringify({
                        username : $name,
                        password : $password
                    }), // utliser objet location 
                    //success: success,
                    //dataType: "text"
                  }); 
           });
        
       </script>
    </body>
    
</html>
