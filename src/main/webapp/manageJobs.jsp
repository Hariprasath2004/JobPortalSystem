<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>

<%@ taglib prefix="s" uri="/struts-tags"%>

<!DOCTYPE html>
<html>

<head>
<meta charset="UTF-8">
<title>Manage Jobs - JobPortal</title>

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


	<!-- Main Container -->
	<div class="container">

		<div class="page-header">

			<h1>Manage Jobs</h1>

			<p>View, manage and track the jobs you have posted.</p>

		</div>


		<!-- Error Messages -->
		<s:actionerror />


		<!-- Jobs List -->
		<s:if test="jobs != null && !jobs.isEmpty()">

			<div class="job-list">

				<s:iterator value="jobs">

					<div class="card job-card">

						<!-- Job Title -->
						<h2 class="job-title">
							<s:property value="jobTitle" />
						</h2>


						<!-- Job Information -->
						<div class="job-info">

							<p>
								<strong>Company:</strong>
								<s:property value="companyName" />
							</p>

							<p>
								<strong>Location:</strong>
								<s:property value="location" />
							</p>

							<p>
								<strong>Job Type:</strong>
								<s:property value="jobType" />
							</p>

							<p>
								<strong>Experience:</strong>
								<s:property value="experienceRequired" />
							</p>

							<p>
								<strong>Required Skills:</strong>
								<s:property value="requiredSkills" />
							</p>

							<p>
								<strong>Salary:</strong> ₹
								<s:property value="salaryMin" />
								- ₹
								<s:property value="salaryMax" />
							</p>

							<p>
								<strong>Posted On:</strong>
								<s:property value="createdAt" />
							</p>

						</div>


						<!-- Job Description -->
						<div class="description-box">

							<h3>Job Description</h3>

							<p>
								<s:property value="jobDescription" />
							</p>

						</div>


						<!-- Actions -->
						<div class="job-actions">

							<!-- View Applicants -->
							<form action="viewApplicants" method="get">

								<input type="hidden" name="jobId"
									value="<s:property value='jobId' />">

								<button type="submit" class="btn btn-primary">View
									Applicants</button>

							</form>


							<!-- Delete Job -->
							<form action="deleteJob" method="post"
								onsubmit="return confirm('Are you sure you want to delete this job?');">

								<input type="hidden" name="jobId"
									value="<s:property value='jobId' />">

								<button type="submit" class="btn btn-danger">Delete Job
								</button>

							</form>

						</div>

					</div>

				</s:iterator>

			</div>

		</s:if>


		<!-- No Jobs -->
		<s:else>

			<div class="card empty-state">

				<h2>No Jobs Posted</h2>

				<p>You have not posted any jobs yet.</p>

				<a href="postJob" class="btn btn-primary"> Post Your First Job </a>

			</div>

		</s:else>


		<!-- Bottom Navigation -->
		<div class="bottom-actions">

			<a href="postJob" class="btn btn-primary"> + Post New Job </a> <a
				href="recruiterDashboard" class="btn btn-secondary"> Back to
				Dashboard </a>

		</div>

	</div>

</body>
</html>