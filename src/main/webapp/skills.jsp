<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>

<%@ taglib prefix="s" uri="/struts-tags"%>

<!DOCTYPE html>

<html>

<head>
<title>My Skills</title>
</head>

<body>

	<h1>My Skills</h1>

	<s:actionerror />

	<p>Logged-in User ID: ${sessionScope.userId}</p>

	<hr>

	<h2>Add Skill</h2>

	<form action="skill" method="post">

		<label>Skill Name:</label> <input type="text" name="skill.skillName"
			required> <br>
		<br> <label>Proficiency:</label> <select name="skill.skillLevel">

			<option value="">Select Proficiency</option>

			<option value="Beginner">Beginner</option>

			<option value="Intermediate">Intermediate</option>

			<option value="Advanced">Advanced</option>

		</select> <br>
		<br>

		<button type="submit">Add Skill</button>

	</form>

	<hr>

	<h2>My Skills</h2>

	<s:if test="skills != null && !skills.isEmpty()">

		<s:iterator value="skills">

			<div>

				<h3>
					<s:property value="skillName" />
				</h3>

				<p>
					<strong>Proficiency:</strong>
					<s:property value="skillLevel" />
				</p>

			</div>

			<hr>

		</s:iterator>

	</s:if>

	<s:else>

		<p>No skills added yet.</p>

	</s:else>

	<br>

	<a href="seekerDashboard"> Back to Dashboard </a>

</body>

</html>