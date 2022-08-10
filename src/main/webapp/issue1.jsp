<%--
  Created by IntelliJ IDEA.
  User: User
  Date: 10.08.2022
  Time: 8:25
  To change this template use File | Settings | File Templates.
--%>

<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <meta charset="UTF-8">
    <title>Issue</title>
    <link rel="stylesheet" href="webjars/bootstrap/5.1.3/css/bootstrap.min.css">
    <link rel="stylesheet"
          href="https://cdnjs.cloudflare.com/ajax/libs/bootstrap-select/1.14.0-beta2/css/bootstrap-select.min.css">

    <script src="webjars/jquery/3.6.0/jquery.min.js"></script>
    <script src="webjars/bootstrap/5.1.3/js/bootstrap.bundle.min.js"></script>
    <script src="https://cdnjs.cloudflare.com/ajax/libs/bootstrap-select/1.14.0-beta2/js/bootstrap-select.min.js"></script>


    <!--  jQuery -->
    <script type="text/javascript" src="https://code.jquery.com/jquery-1.11.3.min.js"></script>

    <!-- Isolated Version of Bootstrap, not needed if your site already uses Bootstrap -->
    <link rel="stylesheet" href="https://formden.com/static/cdn/bootstrap-iso.css"/>

    <!-- Bootstrap Date-Picker Plugin -->
    <script type="text/javascript"
            src="https://cdnjs.cloudflare.com/ajax/libs/bootstrap-datepicker/1.4.1/js/bootstrap-datepicker.min.js"></script>
    <link rel="stylesheet"
          href="https://cdnjs.cloudflare.com/ajax/libs/bootstrap-datepicker/1.4.1/css/bootstrap-datepicker3.css"/>
</head>
<body>
<br/>

<div class="row">
    <div class="col-md-6 offset-3">
        <form action="/issue" method="post">

            <div class="form-group">
                <label for="userId">User Name:</label>
                <select id="userId"
                        class="selectpicker form-control"
                <%-- multiple--%>
                        aria-label="Please select user"
                        data-live-search="true"
                        name="user">
                    <c:forEach items="${userList}" var="user">
                        <option value="${user.getId()}">${user.getName()}</option>
                    </c:forEach>
                </select>
            </div>


            <div class="form-group">
                <label for="booksId">Book:</label>
                <select id="booksId"
                        class="selectpicker form-control"
<%--                        multiple--%>  <%--Faqat bitta kitob qo'shadi --%>
                        aria-label="Please select books"
                        data-live-search="true"
                        name="book"
                >
                    <c:forEach items="${bookList}" var="book">
<%--                        <option value="${book.getId()}">${book.getTitle()}</option>--%> <%-- Id title--%>
                        <option value="${book.getTitle()}">${book.getTitle()}</option>
                    </c:forEach>
                </select>
            </div>


            <div class="form-group"> <!-- Date input -->
                <label class="control-label" for="date">Date</label>
                <input class="form-control" id="date" name="date" placeholder="MM/DD/YYY" type="text"/>
            </div>
            <div class="form-group"> <!-- Submit button -->
                <button class="btn btn-primary " name="submit" type="submit">Submit</button>
            </div>
        </form>
    </div>
</div>


<script>
    $(document).ready(function () {
        var date_input = $('input[name="date"]'); //our date input has the name "date"
        var container = $('.bootstrap-iso form').length > 0 ? $('.bootstrap-iso form').parent() : "body";
        var options = {
            format: 'mm/dd/yyyy',
            container: container,
            todayHighlight: true,
            autoclose: true,
        };
        date_input.datepicker(options);
    })


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
