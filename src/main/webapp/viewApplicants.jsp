<%@ page contentType="text/html;charset=UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags"%>

<!DOCTYPE html>

<html>

<head>

<meta charset="UTF-8">

<title>View Applicants - JobPortal</title>

<link rel="stylesheet"
	href="${pageContext.request.contextPath}/css/style.css">

</head>

<body>

	<!-- ================= NAVBAR ================= -->

	<div class="navbar">

		<div class="logo">JobPortal</div>

		<div class="nav-links">

			<a href="recruiterDashboard"> Dashboard </a> <a href="companyProfile">
				Company Profile </a> <a href="postJob"> Post Job </a> <a
				href="manageJobs"> Manage Jobs </a> <a href="viewApplicants">
				Applicants </a>

			<!-- FIXED: logout must be lowercase -->
			<a href="logout"> Logout </a>

		</div>

	</div>


	<!-- ================= MAIN CONTENT ================= -->

	<div class="container">

		<div class="page-header">

			<h1>Job Applicants</h1>

			<p>Review candidates who have applied for this job.</p>

		</div>


		<!-- ================= ERRORS ================= -->

		<s:if test="hasActionErrors()">

			<div class="error-box">

				<s:actionerror />

			</div>

		</s:if>


		<!-- ================= APPLICANTS ================= -->

		<s:if test="applicants != null && !applicants.isEmpty()">

			<div class="applicants-card">

				<div class="table-wrapper">

					<table class="applicants-table">

						<thead>

							<tr>

								<th>Application ID</th>

								<th>Candidate ID</th>

								<th>Candidate Name</th>

								<th>Candidate Email</th>

								<th>Job Title</th>

								<th>Status</th>

								<th>Applied Date</th>

								<th>Candidate Profile</th>

								<th>Update Status</th>

							</tr>

						</thead>


						<tbody>

							<s:iterator value="applicants">

								<tr>

									<td><s:property value="applicationId" /></td>


									<td><s:property value="seekerId" /></td>


									<td class="candidate-name"><s:property value="seekerName" />

									</td>


									<td><s:property value="seekerEmail" /></td>


									<td><s:property value="jobTitle" /></td>


									<td><span class="status-badge"> <s:property
												value="applicationStatus" />

									</span></td>


									<td><s:property value="appliedAt" /></td>


									<!-- ================= VIEW PROFILE ================= -->

									<td><s:url action="recruiterCandidateProfile"
											var="candidateProfileUrl">

											<s:param name="userId" value="%{seekerId}" />

										</s:url> <a href="${candidateProfileUrl}" class="btn btn-outline">

											View Profile </a></td>


									<!-- ================= UPDATE STATUS ================= -->

									<td><s:form action="updateApplicationStatus" method="post"
											cssClass="status-form">

											<s:hidden name="applicationId" value="%{applicationId}" />

											<s:hidden name="jobId" value="%{jobId}" />


											<s:select name="status"
												list="#{
                                                    'SHORTLISTED':'SHORTLISTED',
                                                    'INTERVIEW':'INTERVIEW',
                                                    'SELECTED':'SELECTED',
                                                    'REJECTED':'REJECTED'
                                                }"
												value="%{applicationStatus}" cssClass="status-select" />


											<s:submit value="Update" cssClass="btn btn-primary" />

										</s:form></td>

								</tr>

							</s:iterator>

						</tbody>

					</table>

				</div>

			</div>

		</s:if>


		<!-- ================= NO APPLICANTS ================= -->

		<s:else>

			<div class="empty-state">

				<div class="empty-icon">👥</div>

				<h2>No Applicants Yet</h2>

				<p>No applicants have applied for this job yet.</p>

			</div>

		</s:else>


		<!-- ================= ACTIONS ================= -->

		<div class="page-actions">

			<a href="manageJobs" class="btn btn-secondary"> Back to Manage
				Jobs </a> <a href="recruiterDashboard" class="btn btn-outline"> Back
				to Dashboard </a>

		</div>

	</div>

</body>

</html>