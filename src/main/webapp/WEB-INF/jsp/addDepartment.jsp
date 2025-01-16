<%@ page contentType="text/html; charset=UTF-8" language="java" %>
<html>
<head>
    <title>Add New Department</title>
</head>
<body>
    <h1>Add New Department</h1>

    <form action="addDepartment" method="post">
        <label for="name">Department Name:</label>
        <input type="text" id="name" name="name" required/><br><br>

        <input type="submit" value="Add Department"/>
    </form>

    <a href="departments">Back to Department List</a>

    <br><br>

    <!-- Display error message if there's any -->
    <c:if test="${not empty errorMessage}">
        <div style="color: red;">
            <strong>${errorMessage}</strong>
        </div>
    </c:if>
</body>
</html>
