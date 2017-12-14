<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <style>
            input {
                padding: 0.2em; 
                box-sizing: border-box;
                width: 100% 
            } 

        </style>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <meta charset="utf-8"/>
        <title>Admin Page</title>
        <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/4.0.0-beta.2/css/bootstrap.min.css" integrity="sha384-PsH8R72JQ3SOdhVi3uxftmaW6Vc51MKb0q5P2rRUpPvrszuE4W1povHYgTpBfshb" crossorigin="anonymous"> 
        <script src="http://code.jquery.com/jquery-latest.min.js"></script>
        <script src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.12.3/umd/popper.min.js" integrity="sha384-vFJXuSJphROIrBnz7yo7oB41mKfc8JzQZiCq4NCceLEaO4IHwicKwpJf9c9IpFgh" crossorigin="anonymous"></script>
        <script src="https://maxcdn.bootstrapcdn.com/bootstrap/4.0.0-beta.2/js/bootstrap.min.js" integrity="sha384-alpBpkh1PFOepccYVYDB4do5UnbKysX5WZXm3XxPqe5iKTfUKjNkCk9SaVuEZflJ" crossorigin="anonymous"></script>    </head>
    <body>
        <c:if test="${!connected}">
            <p>not connected</p>
            <a href="index.jsp">Retourner à la page d'accueil</a>
        </c:if>
        <c:if test="${connected}">
            <table id="userlist" class="table table-striped">
            </table>

            <!--<button name='update'>add user</button>-->
            <button name='edit'>add</button><br>
            <button name='user'>show user list</button><br>
            <button name='sport'>show sport list</button><br>
            <button name='video'>show video list</button>
            
            
            <p>connected</p>
            <script>

                function loadTable() {
                    $("#userlist").html("");
                    $.ajax({
                        type: "GET",
                        async: true,
                        contentType: 'application/json',
                        url: "api/modele.utilisateurentity",
                        success: function (response) {
                            if (response[0] !== null) {
                                var thHTML = '';
                                var trHTML = '';
                                $.each(Object.getOwnPropertyNames(response[0]), function (i, key) {
                                    thHTML += '<th scope="col">' + key + '</th>';
                                });
                                thHTML += '<th>Editer</th><th>Supprimer</th><';
                                $('#userlist').append(thHTML);
                                trHTML += '<tbody>';
                                $.each(response, function (i, item) {
                                    trHTML += '<tr>';
                                    $.each(Object.getOwnPropertyNames(response[0]), function (j, key) {
                                        trHTML += '<td>' + item[key] + '</td>';
                                    });
                                    trHTML += '<td><button name="edit" value=' + item["id"] + '>edit</button></td>' +
                                            '<td><button name="del" value=' + item["id"] + '>supprimer</button></td></tr>';
                                });
                                trHTML += '</tbody>';
                                $('#userlist').append(trHTML);
                            }
                        }
                    });
                }

                function delUser(i) {
                    $.ajax({
                        type: "DELETE",
                        async: true,
                        contentType: 'application/json',
                        url: "api/modele.utilisateurentity/" + i,
                        success: function (data) {
                            console.log(data);


                        },
                            complete: function(){
                            loadTable();
                            }
                    });
                }



                function addUser() {
                    var myjson = '{';
                    $.each(document.getElementsByName("myform")[0].childNodes, function (i, val) {
                        if ((val.childNodes[0].name != 'id' && val.childNodes[0].name !== "update") ||
                                (val.childNodes[0].name == 'id' && val.childNodes[0].value != 'undefined')) {
                            myjson += '"' + val.childNodes[0].name + '":"' + val.childNodes[0].value + '"';
                            if (i < document.getElementsByName("myform")[0].childNodes.length - 2) {
                                myjson += ',';
                            }
                        }
                    });
                    myjson += '}';
                    if ($.parseJSON(myjson).id == '') {
                        console.log($.parseJSON(myjson).id);

                        $.ajax({
                            type: "POST",
                            async: true,
                            contentType: 'application/json',
                            url: "api/modele.utilisateurentity",
                            data: myjson,
                            success: function (response) {
                                console.log("OK");
                                console.log(response);
                            },
                            error: function (code) {
                                console.log("error d'executtion");
                                console.log(code);
                            },
                            complete: function(){
                            loadTable();
                            }

                        });
                    } else {
                        $.ajax({
                            type: "put",
                            async: true,
                            contentType: 'application/json',
                            url: "api/modele.utilisateurentity/" + $.parseJSON(myjson).id,
                            data: myjson,
                            success: function (response) {
                                console.log("OK");
                            },
                            error: function (code) {
                                console.log("error d'executtion");
                                console.log(code);
                            },
                            complete: function(){
                            loadTable();
                            }
                        });
                    }



                }

                function editUser(id) {
                    if (id !== "") {
                        $.ajax({
                            type: "GET",
                            async: true,
                            contentType: 'application/json',
                            url: "api/modele.utilisateurentity/" + id,
                            success: function (response) {
                                console.log(response);
                                var form = '<tr name="myform">';
                                if (response !== null) {
                                    $.each(response, function (key, value) {
                                        if (key == 'id')
                                            form += '<td><input type="hidden" name="' + key + '" value="' + value + '"></td>';
                                        else
                                            form += '<td><input name="' + key + '"value="' + value + '"/></td>';

                                    })
                                    form += '<td><button name="update">update</button></td></tr>';
                                    $("#userlist").append(form);

                                }
                            }
                            
                        });
                    } else {
                        $.ajax({
                            type: "GET",
                            async: true,
                            contentType: 'application/json',
                            url: "api/modele.utilisateurentity",
                            success: function (response) {
                                var form = '<tr name="myform">';
                                if (response[0] !== null) {
                                    $.each(Object.getOwnPropertyNames(response[0]), function (i, champ) {
                                        if (i === 0) {
                                            form += '<td><input type="hidden" name=' + champ + ' value=""></input></td>';
                                        } else {
                                            form += '<td><input name=' + champ + ' value=""></input></td>';
                                        }
                                    });
                                    form += '<td><button name="update">update</button></td></tr>';
                                    $("#userlist").append(form);
                                }
                            }
                        });
                    }
                }

                $(document).ready(function () {

                    //                   $("button[name=add]").hide();
                    $("body").delegate("button", "click", function () {
                        if ($(this).attr("name") === 'edit')
                        {
                            editUser($(this).val());
                            alert($(this).val() + " " + $(this).attr("name"));
                        } else if ($(this).attr("name") === 'del')
                        {
                            delUser($(this).val());
                            alert("del done");
                        } else if ($(this).attr("name") === 'update') {
                            alert("add user");
                            addUser();
                        } else {
                            alert($(this).attr("value"));
                        }

                    });
                    loadTable();

                });
               

            </script>
        </c:if>




    </body>
</html>
