<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Job Posted - JobPortal</title>

<link rel="stylesheet"
	href="${pageContext.request.contextPath}/css/style.css">
</head>

<body>

	<!-- Navigation Bar -->
	<div class="navbar">

		<div class="logo">JobPortal</div>

		<div class="nav-links">

			<a href="recruiterDashboard"> Dashboard </a> <a href="companyProfile">
				Company Profile </a> <a href="postJob"> Post Job </a> <a
				href="manageJobs"> Manage Jobs </a> <a href="viewApplicants">
				Applicants </a> <a href="Logout"> Logout </a>

		</div>

	</div>


	<!-- Main Content -->
	<div class="container">

		<div class="page-header">
			<h1>Job Posted Successfully!</h1>

			<p>Your job has been posted successfully.</p>
		</div>


		<!-- Success Card -->
		<div class="card success-card">

			<div class="success-icon">✓</div>

			<h2>Job Posted Successfully</h2>

			<p>Your job has been successfully published and is now available
				for job seekers.</p>

			<div class="button-group">

				<a href="postJob" class="btn btn-primary"> Post Another Job </a> <a
					href="manageJobs" class="btn btn-secondary"> Manage Jobs </a> <a
					href="recruiterDashboard" class="btn btn-secondary"> Back to
					Dashboard </a>

			</div>

		</div>

	</div>

</body>
</html>