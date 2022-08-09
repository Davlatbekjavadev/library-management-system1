<%--
  Created by IntelliJ IDEA.
  User: abror
  Date: 01/08/22
  Time: 15:32
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <title>Admin Page</title>
    <!--    <link rel="stylesheet" href="styles.css">-->

    <link rel="stylesheet" href="webjars/bootstrap/5.1.3/css/bootstrap.min.css">
</head>

<body>

<%@include file="includes/navbar.jsp" %>

<!--SAYTNI MENYUDAN PASTKI QISMI KONTENTLAR-->
<section class="container mt-4 ">


    <c:if test="${message != null}">
        <h1>${message}</h1>
    </c:if>

    <a href="/add-admin">+ add new admin</a>
    <br><br>


    <table border='1' width="100%">
        <tr>
            <th>Id</th>
            <th>Name</th>
            <th>Password</th>
            <th>Email</th>
            <th>Role</th>
            <th>Edit</th>
            <th>Delete</th>
        </tr>


        <c:forEach items="${adminList}" var="admin">

            <tr>
                <th>${admin.getId()}</th>
                <th>${admin.getName()}</th>
                <th>${admin.getPassword()}</th>
                <th>${admin.getEmail()}</th>
                <th>${admin.getRole()}</th>
                <th> <a href="/edit?id=${admin.getId()}">Edit</a></th>
                <th> <a href="/deleteUser?id=${admin.getId()}">Delete</a></th>

            </tr>

        </c:forEach>

    </table>
</section>
</body>
</html>
