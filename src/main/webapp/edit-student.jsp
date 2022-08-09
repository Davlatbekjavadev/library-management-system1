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
    <title>Edit Student</title>
</head>
<body>
<c: items="${user}">
<form action="/edit?id=${user.getId()}" method="post" >


    <div class="form-group">
        <label >Name:</label>
      <input placeholder="${user.getName()}" name="name">
    </div>



    <div class="form-group">
        <label>Email:</label>
        <input placeholder="${user.getEmail()}" name="email">
    </div>


    <div class="form-group">
        <label>Password:</label>
        <input placeholder="${user.getPassword()}" name="password">
    </div>


    <button type="submit" class="btn btn-primary">Saqlash</button>

</form>
</c:>
</body>
</html>
