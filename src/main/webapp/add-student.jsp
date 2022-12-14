<%--
  Created by IntelliJ IDEA.
  User: User
  Date: 05.08.2022
  Time: 11:37
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Add student</title>

    <link rel="stylesheet" href="webjars/bootstrap/5.1.3/css/bootstrap.min.css">
    <link rel="stylesheet"
          href="https://cdnjs.cloudflare.com/ajax/libs/bootstrap-select/1.14.0-beta2/css/bootstrap-select.min.css">

    <script src="webjars/jquery/3.6.0/jquery.min.js"></script>
    <script src="webjars/bootstrap/5.1.3/js/bootstrap.bundle.min.js"></script>
    <script src="https://cdnjs.cloudflare.com/ajax/libs/bootstrap-select/1.14.0-beta2/js/bootstrap-select.min.js"></script>

</head>

<body>
<h1>ADD NEW Student</h1>
<br/>

<div class="row">
    <div class="col-md-6 offset-3">


        <form action="/add-student" method="post" > <%-- enctype="multipart/form-data"--%>

<%--            <div class="form-group">--%>
<%--                <label for="title">Title:</label>--%>
<%--                <input--%>
<%--                        id="title"--%>
<%--                        type="text"--%>
<%--                        name="title"--%>
<%--                        class="form-control"--%>
<%--                        placeholder="Input title..."--%>
<%--                >--%>
<%--            </div>--%>

            <div class="form-group">
                <label for="name">Name:</label>
                <textarea class="form-control" rows="3" id="name" name="name"></textarea>
            </div>

<%-- Authors--%>
<%--            <div class="form-group">--%>
<%--                <label for="authorsIds">Authors:</label>--%>
<%--                <select id="authorsIds"--%>
<%--                        class="selectpicker form-control"--%>
<%--                        multiple--%>
<%--                        aria-label="Please select authors"--%>
<%--                        data-live-search="true"--%>
<%--                        name="authorsIds"--%>
<%--                >--%>
<%--                    <c:forEach items="${authorList}" var="author">--%>
<%--                        <option value="${author.getId()}">${author.getFullName()}</option>--%>
<%--                    </c:forEach>--%>
<%--                </select>--%>
<%--            </div>--%>



<%--Category--%>
<%--            <div class="form-group">--%>
<%--                <label for="categoryId">Category:</label>--%>
<%--                <select required class="form-control" id="categoryId" name="categoryId">--%>
<%--                    <option selected disabled value="0">Select category:</option>--%>
<%--                    <c:forEach items="${categoryList}" var="category">--%>
<%--                        <option value="${category.getId()}">${category.getName()}</option>--%>
<%--                    </c:forEach>--%>
<%--                </select>--%>
<%--            </div>--%>


            <div class="form-group">
                <label for="email">Email:</label>
                <input class="form-control" id="email" type="text" name="email">
            </div>


            <div class="form-group">
                <label for="password">Password:</label>
                <input class="form-control" id="password" type="number" name="password">
            </div>

<%--    Image--%>
<%--            <div class="form-group">--%>
<%--                <label for="image">Upload cover image:</label>--%>

<%--                <input class="form-control-file" id="image" type="file" name="image">--%>

<%--            </div>--%>

            <button type="submit" class="btn btn-primary">Saqlash</button>

        </form>

    </div>
</div>


<script>

    $(document).ready(function () {
        $('#framework').multiselect({
            nonSelectedText: 'Select Framework',
            enableFiltering: true,
            enableCaseInsensitiveFiltering: true,
            buttonWidth: '400px'
        });
    });
</script>
</body>

</html>
