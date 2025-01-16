<%@ page contentType="text/html; charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<html>
<head>
    <title>Department List</title>
</head>
<body>
    <h1>List of Departments</h1>

    <a href="addDepartmentForm">Add New Department</a> <!-- Ссылка на страницу добавления департамента -->

    <br><br>

    <table border="1">
        <tr>
            <th>ID</th>
            <th>Name</th>
            <th>Actions</th>
        </tr>
        <c:forEach var="department" items="${departments}">
            <tr>
                <td>${department.id}</td>
                <td>${department.name}</td>
                <td>
                    <a href="editDepartment?id=${department.id}">Edit</a>
                    <a href="deleteDepartment?id=${department.id}" onclick="return confirm('Are you sure you want to delete this department?');">Delete</a>
                </td>
            </tr>
        </c:forEach>
    </table>

</body>
</html>
