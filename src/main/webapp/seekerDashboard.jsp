<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>

<!DOCTYPE html>

<html>

<head>

<meta charset="UTF-8">

<title>Job Seeker Dashboard</title>

<link rel="stylesheet" type="text/css"
	href="${pageContext.request.contextPath}/css/style.css">

</head>

<body>

	<!-- Navbar -->

	<div class="navbar">

		<div class="logo">JobPortal</div>

		<div>

			<a href="seekerDashboard"> Dashboard </a> <a href="jobSearch">
				Search Jobs </a> <a href="myApplications"> My Applications </a> <a
				href="logout"> Logout </a>

		</div>

	</div>


	<!-- Main Content -->

	<div class="container">

		<!-- Welcome -->

		<div class="dashboard-header">

			<h1>Job Seeker Dashboard</h1>

			<p>
				Welcome back, <strong>${sessionScope.fullName}</strong>!
			</p>

			<p>Email: ${sessionScope.email}</p>

			<p>Role: ${sessionScope.role}</p>

		</div>


		<!-- Dashboard Cards -->

		<div class="dashboard-cards">

			<!-- Profile -->

			<a href="candidateProfile" class="dashboard-card">

				<h3>My Profile</h3>

				<p>View and update your personal, education, experience and
					skills information.</p>

			</a>


			<!-- Search Jobs -->

			<a href="jobSearch" class="dashboard-card">

				<h3>Search Jobs</h3>

				<p>Find job opportunities that match your skills and experience.
				</p>

			</a>


			<!-- Applications -->

			<a href="myApplications" class="dashboard-card">

				<h3>My Applications</h3>

				<p>View the jobs you have applied for and track your application
					status.</p>

			</a>

		</div>

	</div>

</body>

</html>