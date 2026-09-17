<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>

<%@ taglib prefix="s" uri="/struts-tags"%>

<!DOCTYPE html>
<html>

<head>

<meta charset="UTF-8">

<title>My Applications - JobPortal</title>

<link rel="stylesheet" type="text/css"
	href="${pageContext.request.contextPath}/css/style.css">

</head>

<body>

	<!-- Navbar -->

	<div class="navbar">

		<div class="logo">JobPortal</div>

		<div class="nav-links">

			<a href="seekerDashboard"> Dashboard </a> <a href="jobSearch">
				Search Jobs </a> <a href="myApplications"> My Applications </a> <a
				href="logout"> Logout </a>

		</div>

	</div>


	<!-- Main Content -->

	<div class="container">

		<h1 class="page-title">My Applications</h1>

		<p class="welcome-text">Welcome, ${sessionScope.fullName}!</p>


		<!-- Error Message -->

		<s:if test="hasActionErrors()">

			<div class="error-message">

				<s:actionerror />

			</div>

		</s:if>


		<!-- Applications -->

		<s:if test="applications != null && !applications.isEmpty()">

			<s:iterator value="applications">

				<div class="application-card">

					<h2 class="job-title">

						<s:property value="jobTitle" />

					</h2>


					<div class="job-details">

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
							<strong>Applied On:</strong>
							<s:property value="appliedAt" />
						</p>

					</div>


					<div class="application-status">

						<strong> Application Status: </strong>


						<s:if test="applicationStatus != null">

							<s:if test="applicationStatus.equalsIgnoreCase('PENDING')">

								<span class="status pending"> Pending </span>

							</s:if>


							<s:elseif
								test="applicationStatus.equalsIgnoreCase('SHORTLISTED')">

								<span class="status shortlisted"> Shortlisted </span>

							</s:elseif>


							<s:elseif test="applicationStatus.equalsIgnoreCase('REJECTED')">

								<span class="status rejected"> Rejected </span>

							</s:elseif>


							<s:elseif test="applicationStatus.equalsIgnoreCase('SELECTED')">

								<span class="status selected"> Selected </span>

							</s:elseif>


							<s:else>

								<span class="status default-status"> <s:property
										value="applicationStatus" />

								</span>

							</s:else>

						</s:if>


						<s:else>

							<span class="status default-status"> Not Available </span>

						</s:else>

					</div>

				</div>

			</s:iterator>

		</s:if>


		<!-- No Applications -->

		<s:else>

			<div class="empty-state">

				<h2>No applications yet</h2>

				<p>You have not applied for any jobs yet.</p>

				<a href="jobSearch" class="btn btn-primary"> Search Jobs </a>

			</div>

		</s:else>


		<!-- Bottom Navigation -->

		<div class="page-actions">

			<a href="jobSearch" class="btn btn-primary"> Search Jobs </a> <a
				href="seekerDashboard" class="btn btn-secondary"> Back to
				Dashboard </a>

		</div>

	</div>

</body>

</html>