<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>

<%@ taglib prefix="s" uri="/struts-tags"%>

<!DOCTYPE html>

<html>

<head>

<title>Add Education</title>

</head>

<body>

	<h1>Add Education</h1>

	<s:actionerror />

	<p>Logged-in User ID: ${sessionScope.userId}</p>

	<hr>

	<form action="education" method="post">

		<label>Degree:</label> <input type="text" name="education.degree"
			required> <br> <br> <label>Institution:</label> <input
			type="text" name="education.institution" required> <br>
		<br> <label>Specialization:</label> <input type="text"
			name="education.specialization"> <br> <br> <label>Start
			Year:</label> <input type="number" name="education.startYear" min="1950"
			max="2100"> <br> <br> <label>End Year:</label> <input
			type="number" name="education.endYear" min="1950" max="2100">
		<br> <br> <label>Grade / CGPA:</label> <input type="text"
			name="education.grade"> <br> <br>


		<button type="submit">Save Education</button>

	</form>

	<hr>

	<a href="seekerDashboard"> Back to Dashboard </a>

</body>

</html>