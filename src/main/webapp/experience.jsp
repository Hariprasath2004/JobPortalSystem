<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>

<%@ taglib prefix="s" uri="/struts-tags"%>

<!DOCTYPE html>

<html>

<head>

<title>My Experience</title>

</head>

<body>

	<h1>My Experience</h1>

	<s:actionerror />

	<p>Logged-in User ID: ${sessionScope.userId}</p>

	<hr>

	<h2>Add Experience</h2>

	<form action="experience" method="post">

		<label>Job Title:</label> <input type="text"
			name="experience.jobTitle" required> <br> <br> <label>Company
			Name:</label> <input type="text" name="experience.companyName" required>
		<br> <br> <label>Location:</label> <input type="text"
			name="experience.location"> <br> <br> <label>Start
			Date:</label> <input type="date" name="experience.startDate"> <br>
		<br> <label>End Date:</label> <input type="date"
			name="experience.endDate"> <br> <br> <label>
			<input type="checkbox" name="experience.currentlyWorking"
			value="true"> I currently work here
		</label> <br> <br> <label>Description:</label> <br>

		<textarea name="experience.description" rows="6" cols="60"></textarea>

		<br> <br>


		<button type="submit">Save Experience</button>

	</form>

	<hr>

	<h2>My Experiences</h2>

	<s:if test="experiences != null && !experiences.isEmpty()">

		<s:iterator value="experiences">

			<div>

				<h3>
					<s:property value="jobTitle" />
				</h3>

				<p>
					<strong>Company:</strong>
					<s:property value="companyName" />
				</p>

				<p>
					<strong>Location:</strong>
					<s:property value="location" />
				</p>

				<p>
					<strong>Start Date:</strong>
					<s:property value="startDate" />
				</p>

				<p>
					<strong>End Date:</strong>
					<s:if test="currentlyWorking">
                        Present
                    </s:if>
					<s:else>
						<s:property value="endDate" />
					</s:else>
				</p>

				<p>
					<strong>Description:</strong> <br>
					<s:property value="description" />
				</p>

			</div>

			<hr>

		</s:iterator>

	</s:if>

	<s:else>

		<p>No experience added yet.</p>

	</s:else>

	<br>

	<a href="seekerDashboard"> Back to Dashboard </a>

</body>

</html>