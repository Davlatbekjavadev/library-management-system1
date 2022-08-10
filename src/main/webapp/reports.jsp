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
    <title>Reports Page</title>
    <!--    <link rel="stylesheet" href="styles.css">-->

    <link rel="stylesheet" href="webjars/bootstrap/5.1.3/css/bootstrap.min.css">
</head>

<body>

<%@include file="includes/navbar.jsp" %>

<!--SAYTNI MENYUDAN PASTKI QISMI KONTENTLAR-->
<section class="container mt-4 ">

    <br><br>

    <table border='1' width="100%">
        <tr>
            <th>Id</th>
            <th>User</th>
            <th>Book</th>
            <th>Date</th>
            <th>Status</th>


        </tr>


        <c:forEach items="${issueList}" var="issue">
            <c:if test="${issue.getStatus() == 'false'}">
                <tr style="color: lime">
                    <th>${issue.getId()}</th>
                    <th>${issue.getUser()}</th>
                    <th>${issue.getBook()}</th>
                    <th>${issue.getData()}</th>
                    <th>${issue.getStatus()}</th>
                </tr>
            </c:if>
            <c:if test="${issue.getStatus() == 'true'}">
                <tr>
                    <th>${issue.getId()}</th>
                    <th>${issue.getUser()}</th>
                    <th>${issue.getBook()}</th>
                    <th>${issue.getData()}</th>
                    <th><a href="/issueEdit?id=${issue.getId()}">${issue.getStatus()}</a></th>
                </tr>
            </c:if>
        </c:forEach>

    </table>
</section>
</body>
</html>
