<%@ page contentType="text/html; charset=UTF-8" language="java" %>
<html>
<head>
    <title>Edit Department</title>
</head>
<body>
    <h1>Edit Department</h1>

    <form action="editDepartment" method="post">
        <input type="hidden" name="id" value="${department.id}"/>

        <label for="name">Department Name:</label>
        <input type="text" id="name" name="name" value="${department.name}" required/><br><br>

        <input type="submit" value="Update Department"/>
    </form>

    <a href="departments">Back to Department List</a>

</body>
</html>
