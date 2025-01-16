<%@ page contentType="text/html; charset=UTF-8" language="java" %>
<html>
<head>
    <title>Add New Employee</title>
</head>
<body>
    <h1>Add New Employee</h1>

    <form action="addEmployee" method="post">
        <label for="name">Name:</label>
        <input type="text" id="name" name="name"><br><br>

        <label for="age">Age:</label>
        <input type="number" id="age" name="age"><br><br>

        <label for="salary">Salary:</label>
        <input type="number" id="salary" name="salary" step="0.01"><br><br>

        <label for="department">Department ID:</label>
        <input type="number" id="department" name="department"><br><br>

        <input type="submit" value="Add Employee">
    </form>

    <br>
    <a href="employees">Back to Employee List</a>
</body>
</html>
