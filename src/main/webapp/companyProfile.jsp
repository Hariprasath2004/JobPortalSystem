<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>

<%@ taglib prefix="s" uri="/struts-tags"%>

<!DOCTYPE html>
<html>

<head>
<meta charset="UTF-8">
<title>Company Profile - JobPortal</title>

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
				Applicants </a> <a href="logout"> Logout </a>

		</div>

	</div>


	<!-- ================= MAIN CONTENT ================= -->

	<div class="container">

		<div class="page-header">

			<h1>Company Profile</h1>

			<p>Manage your company information and help job seekers learn
				more about your organization.</p>

		</div>


		<!-- ================= ERROR MESSAGE ================= -->

		<s:actionerror />


		<!-- ================= COMPANY PROFILE FORM ================= -->

		<div class="form-card">

			<h2>Company Information</h2>

			<form action="companyProfile" method="post">

				<!-- Company Name -->

				<div class="form-group">

					<label for="companyName"> Company Name </label> <input type="text"
						id="companyName" name="companyProfile.companyName"
						value="<s:property value='companyProfile.companyName'/>"
						placeholder="Enter company name" required>

				</div>


				<!-- Company Description -->

				<div class="form-group">

					<label for="companyDescription"> Company Description </label>

					<textarea id="companyDescription"
						name="companyProfile.companyDescription" rows="6"
						placeholder="Tell job seekers about your company"><s:property
							value="companyProfile.companyDescription" /></textarea>

				</div>


				<!-- Website -->

				<div class="form-group">

					<label for="website"> Website </label> <input type="text"
						id="website" name="companyProfile.website"
						value="<s:property value='companyProfile.website'/>"
						placeholder="https://example.com">

				</div>


				<!-- Industry -->

				<div class="form-group">

					<label for="industry"> Industry </label> <input type="text"
						id="industry" name="companyProfile.industry"
						value="<s:property value='companyProfile.industry'/>"
						placeholder="Information Technology">

				</div>


				<!-- Company Size -->

				<div class="form-group">

					<label for="companySize"> Company Size </label> <select
						id="companySize" name="companyProfile.companySize">

						<option value="">Select Company Size</option>

						<option value="1-10"
							<s:if test="companyProfile.companySize == '1-10'">
                                selected
                            </s:if>>
							1-10</option>

						<option value="11-50"
							<s:if test="companyProfile.companySize == '11-50'">
                                selected
                            </s:if>>
							11-50</option>

						<option value="51-200"
							<s:if test="companyProfile.companySize == '51-200'">
                                selected
                            </s:if>>
							51-200</option>

						<option value="201-500"
							<s:if test="companyProfile.companySize == '201-500'">
                                selected
                            </s:if>>
							201-500</option>

						<option value="501-1000"
							<s:if test="companyProfile.companySize == '501-1000'">
                                selected
                            </s:if>>
							501-1000</option>

						<option value="1000+"
							<s:if test="companyProfile.companySize == '1000+'">
                                selected
                            </s:if>>
							1000+</option>

					</select>

				</div>


				<!-- Location -->

				<div class="form-group">

					<label for="location"> Location </label> <input type="text"
						id="location" name="companyProfile.location"
						value="<s:property value='companyProfile.location'/>"
						placeholder="Chennai" required>

				</div>


				<!-- Submit -->

				<div class="form-actions">

					<button type="submit" class="btn-primary">Save Company
						Profile</button>

				</div>

			</form>

		</div>


		<!-- ================= BACK ================= -->

		<div class="back-link">

			<a href="recruiterDashboard"> ← Back to Dashboard </a>

		</div>

	</div>

</body>

</html>