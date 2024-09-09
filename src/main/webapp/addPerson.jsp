<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Add Person</title>
</head>
<body>
<h2>Add New Person</h2>
<form action="persons" method="post">
    First Name: <input type="text" name="firstName"><br>
    Last Name: <input type="text" name="lastName"><br>
    Person Number: <input type="text" name="personNumber"><br>
    Email: <input type="text" name="email"><br>
    <input type="submit" value="Submit">
</form>
</body>
</html>