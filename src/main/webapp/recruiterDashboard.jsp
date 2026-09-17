<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>

<!DOCTYPE html>

<html>

<head>
<meta charset="UTF-8">
<title>Recruiter Dashboard</title>

<link rel="stylesheet" href="css/style.css">
</head>

<body>

	<!-- ================= NAVBAR ================= -->

	<nav class="navbar">

		<div class="logo">JobPortal</div>

		<div>
			<a href="recruiterDashboard"> Dashboard </a> <a href="companyProfile">
				Company Profile </a> <a href="job"> Post Job </a> <a href="manageJobs">
				Manage Jobs </a> <a href="manageJobs"> Applicants </a> <a href="logout">
				Logout </a>
		</div>

	</nav>


	<!-- ================= MAIN CONTENT ================= -->

	<div class="container">

		<!-- Page Header -->

		<div class="page-header">

			<h1>Recruiter Dashboard</h1>

			<p>Manage your company, jobs and applicants from one place.</p>

		</div>


		<!-- User Information -->

		<div class="dashboard-header">

			<h1>Welcome, ${sessionScope.fullName}!</h1>

			<p>
				<strong>Email:</strong> ${sessionScope.email}
			</p>

			<p>
				<strong>Role:</strong> ${sessionScope.role}
			</p>

		</div>


		<!-- Dashboard Cards -->

		<div class="dashboard-cards">


			<!-- Company Profile -->

			<a href="companyProfile" class="dashboard-card">

				<h3>Company Profile</h3>

				<p>Manage your company information and help job seekers learn
					more about your organization.</p>

			</a>


			<!-- Post Job -->

			<a href="job" class="dashboard-card">

				<h3>Post a Job</h3>

				<p>Create new job opportunities and find the right candidates
					for your company.</p>

			</a>


			<!-- Manage Jobs -->

			<a href="manageJobs" class="dashboard-card">

				<h3>Manage Jobs</h3>

				<p>View and manage all the jobs posted by your company.</p>

			</a>


			<!-- View Applicants -->

			<a href="manageJobs" class="dashboard-card">

				<h3>View Applicants</h3>

				<p>Review candidates who have applied for your posted jobs and
					update their status.</p>

			</a>

		</div>


		<!-- Logout -->

		<div style="margin-top: 30px;">

			<a href="logout" class="btn btn-danger"> Logout </a>

		</div>

	</div>

</body>

</html>