<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <title>Person List</title>
</head>
<body>
<h2>Person List</h2>
<table border="1">
    <tr>
        <th>First Name</th>
        <th>Last Name</th>
        <th>Person Number</th>
        <th>Email</th>
    </tr>
    <c:forEach var="person" items="${persons}">
        <tr>
            <td>${person.firstName}</td>
            <td>${person.lastName}</td>
            <td>${person.personNumber}</td>
            <td><c:out value="${person.emailAddress}" /> </td>
        </tr>
    </c:forEach>
</table>
<a href="addPerson.jsp">Add New Person</a>
</body>
</html>