<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>

<%@ taglib prefix="s" uri="/struts-tags" %>

<!DOCTYPE html>
<html>

<head>
    <title>Job Portal - Dashboard</title>
</head>

<body>

    <h2>Welcome to Job Portal</h2>

    <p>
        Welcome, ${sessionScope.fullName}!
    </p>

    <p>
        Email: ${sessionScope.email}
    </p>

    <p>
        Role: ${sessionScope.role}
    </p>

</body>

</html>