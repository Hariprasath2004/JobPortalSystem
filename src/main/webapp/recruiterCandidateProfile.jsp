<%@ page contentType="text/html;charset=UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags"%>

<!DOCTYPE html>

<html>

<head>

<meta charset="UTF-8">

<title>Candidate Profile - JobPortal</title>

<style>
* {
	box-sizing: border-box;
}

body {
	margin: 0;
	font-family: Arial, Helvetica, sans-serif;
	background: #f4f6f9;
	color: #1f2937;
}

/* ================= NAVBAR ================= */
.navbar {
	height: 72px;
	background: white;
	border-bottom: 1px solid #e5e7eb;
	display: flex;
	align-items: center;
	justify-content: space-between;
	padding: 0 48px;
}

.logo {
	font-size: 28px;
	font-weight: bold;
	color: #2563eb;
}

.nav-links {
	display: flex;
	gap: 30px;
	align-items: center;
}

.nav-links a {
	text-decoration: none;
	color: #111827;
	font-size: 16px;
}

.nav-links a:hover {
	color: #2563eb;
}

/* ================= CONTAINER ================= */
.container {
	width: 75%;
	max-width: 1000px;
	margin: 40px auto;
}

/* ================= CARD ================= */
.card {
	background: white;
	border-radius: 12px;
	padding: 32px;
	margin-bottom: 25px;
	box-shadow: 0 3px 12px rgba(0, 0, 0, 0.08);
}

.card h1 {
	margin-top: 0;
	margin-bottom: 10px;
	font-size: 32px;
}

.card h2 {
	margin-top: 0;
	font-size: 23px;
	border-bottom: 1px solid #e5e7eb;
	padding-bottom: 15px;
}

/* ================= PROFILE ROW ================= */
.profile-row {
	display: grid;
	grid-template-columns: 200px 1fr;
	gap: 20px;
	padding: 15px 0;
	border-bottom: 1px solid #f0f0f0;
}

.profile-label {
	font-weight: bold;
	color: #374151;
}

.profile-value {
	color: #4b5563;
	word-break: break-word;
}

/* ================= COMPLETION ================= */
.completion-header {
	display: flex;
	justify-content: space-between;
	font-weight: bold;
	margin-bottom: 10px;
}

.progress-container {
	width: 100%;
	height: 20px;
	background: #e5e7eb;
	border-radius: 10px;
	overflow: hidden;
}

.progress-bar {
	height: 100%;
	background: #2563eb;
	border-radius: 10px;
}

/* ================= BUTTON ================= */
.page-actions {
	margin-top: 25px;
}

.btn {
	display: inline-block;
	text-decoration: none;
	border-radius: 7px;
	padding: 12px 20px;
	font-size: 15px;
	margin-right: 10px;
}

.btn-primary {
	background: #2563eb;
	color: white;
}

.btn-secondary {
	background: #6b7280;
	color: white;
}

.btn-outline {
	border: 1px solid #2563eb;
	color: #2563eb;
	background: white;
}

/* ================= ERROR ================= */
.error-box {
	background: #fee2e2;
	color: #991b1b;
	padding: 15px;
	border-radius: 7px;
	margin-bottom: 20px;
}

/* ================= RESPONSIVE ================= */
@media ( max-width : 768px) {
	.container {
		width: 92%;
	}
	.navbar {
		padding: 0 20px;
	}
	.nav-links {
		gap: 12px;
	}
	.profile-row {
		grid-template-columns: 1fr;
	}
}
</style>

</head>


<body>


	<!-- ================= NAVBAR ================= -->

	<div class="navbar">

		<div class="logo">JobPortal</div>

		<div class="nav-links">

			<a href="recruiterDashboard"> Dashboard </a> <a href="companyProfile">
				Company Profile </a> <a href="manageJobs"> Manage Jobs </a> <a
				href="viewApplicants"> Applicants </a>

			<!-- FIXED -->
			<a href="logout"> Logout </a>

		</div>

	</div>


	<!-- ================= MAIN ================= -->

	<div class="container">


		<!-- ================= ERROR ================= -->

		<s:if test="hasActionErrors()">

			<div class="error-box">

				<s:actionerror />

			</div>

		</s:if>


		<!-- ================= PROFILE HEADER ================= -->

		<div class="card">

			<h1>Candidate Profile</h1>

			<p>View candidate information and professional details.</p>


			<div class="completion-header">

				<span> Profile Completion </span> <span> <s:property
						value="profile.profileCompletion" />%
				</span>

			</div>


			<div class="progress-container">

				<div class="progress-bar"
					style="width:<s:property value='profile.profileCompletion' />%;">
				</div>

			</div>

		</div>


		<!-- ================= PERSONAL INFORMATION ================= -->

		<div class="card">

			<h2>Personal Information</h2>


			<div class="profile-row">

				<div class="profile-label">Candidate ID</div>

				<div class="profile-value">

					<s:property value="profile.userId" />

				</div>

			</div>


			<div class="profile-row">

				<div class="profile-label">Phone</div>

				<div class="profile-value">

					<s:property value="profile.phone" />

				</div>

			</div>


			<div class="profile-row">

				<div class="profile-label">Date of Birth</div>

				<div class="profile-value">

					<s:property value="profile.dateOfBirth" />

				</div>

			</div>


			<div class="profile-row">

				<div class="profile-label">Gender</div>

				<div class="profile-value">

					<s:property value="profile.gender" />

				</div>

			</div>


			<div class="profile-row">

				<div class="profile-label">Location</div>

				<div class="profile-value">

					<s:property value="profile.location" />

				</div>

			</div>

		</div>


		<!-- ================= PROFESSIONAL INFORMATION ================= -->

		<div class="card">

			<h2>Professional Information</h2>


			<div class="profile-row">

				<div class="profile-label">Professional Headline</div>

				<div class="profile-value">

					<s:property value="profile.headline" />

				</div>

			</div>


			<div class="profile-row">

				<div class="profile-label">Professional Summary</div>

				<div class="profile-value">

					<s:property value="profile.summary" />

				</div>

			</div>


			<div class="profile-row">

				<div class="profile-label">Resume</div>

				<div class="profile-value">

					<s:if
						test="profile.resumePath != null && !profile.resumePath.isEmpty()">

						<s:property value="profile.resumePath" />

					</s:if>

					<s:else>

                        Resume not provided

                    </s:else>

				</div>

			</div>

		</div>


		<!-- ================= ACTIONS ================= -->

		<div class="page-actions">

			<a href="viewApplicants" class="btn btn-secondary"> Back to
				Applicants </a> <a href="recruiterDashboard" class="btn btn-outline">

				Back to Dashboard </a>

		</div>


	</div>

</body>

</html>