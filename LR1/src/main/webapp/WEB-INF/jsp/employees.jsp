<%@ page contentType="text/html; charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<html>
<head>
    <title>Employee List</title>
</head>
<body>
    <h1>List of Employees</h1>

    <a href="addEmployeeForm">Add New Employee</a> <!-- Ссылка на страницу добавления сотрудника -->

    <br><br>

    <table border="1">
        <tr>
            <th>ID</th>
            <th>Name</th>
            <th>Age</th>
            <th>Salary</th>
            <th>Department</th>
            <th>Actions</th>
        </tr>
        <c:forEach var="employee" items="${employees}">
            <tr>
                <td>${employee.id}</td>
                <td>${employee.name}</td>
                <td>${employee.age}</td>
                <td>${employee.salary}</td>
                <td>${employee.department.name}</td>
                <td>
                    <a href="editEmployee?id=${employee.id}">Edit</a>
                    <a href="deleteEmployee?id=${employee.id}" onclick="return confirm('Are you sure you want to delete this employee?');">Delete</a>
                </td>
            </tr>
        </c:forEach>
    </table>

</body>
</html>
