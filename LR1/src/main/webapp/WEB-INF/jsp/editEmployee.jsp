<%@ page contentType="text/html; charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<html>
<head>
    <title>Edit Employee</title>
</head>
<body>
    <h1>Edit Employee</h1>

    <form action="editEmployee" method="post">
        <input type="hidden" name="id" value="${employee.id}"/>

        <label for="name">Name:</label>
        <input type="text" id="name" name="name" value="${employee.name}"/><br><br>

        <label for="age">Age:</label>
        <input type="text" id="age" name="age" value="${employee.age}"/><br><br>

        <label for="salary">Salary:</label>
        <input type="text" id="salary" name="salary" value="${employee.salary}"/><br><br>

        <label for="department">Department:</label>
        <select id="department" name="department">
            <c:forEach var="department" items="${departments}">
                <option value="${department.id}"
                        ${department.id == employee.department.id ? 'selected' : ''}>
                    ${department.name}
                </option>
            </c:forEach>
        </select><br><br>

        <input type="submit" value="Update Employee"/>
    </form>

    <a href="employees">Back to Employee List</a>
</body>
</html>
