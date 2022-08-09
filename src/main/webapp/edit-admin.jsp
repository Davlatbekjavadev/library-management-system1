<%--
  Created by IntelliJ IDEA.
  User: User
  Date: 08.08.2022
  Time: 5:38
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Edit Admin</title>
</head>
<body>
<c: items="${admin}">
    <form action="/editAdmin?id=${admin.getId()}" method="post" >


        <div class="form-group">
            <label >Name:</label>
            <input placeholder="${admin.getName()}" name="name">
        </div>



        <div class="form-group">
            <label>Email:</label>
            <input placeholder="${admin.getEmail()}" name="email">
        </div>


        <div class="form-group">
            <label>Password:</label>
            <input placeholder="${admin.getPassword()}" name="password">
        </div>


        <button type="submit" class="btn btn-primary">Saqlash</button>

    </form>
</c:>
</body>
</html>
