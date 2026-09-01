<%@ page language="java"
    contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>

<%@ taglib prefix="s" uri="/struts-tags" %>

<!DOCTYPE html>
<html>

<head>

    <meta charset="UTF-8">

    <title>Post Job - JobPortal</title>

    <link rel="stylesheet"
          href="${pageContext.request.contextPath}/css/style.css">

</head>

<body>

    <!-- ================= NAVBAR ================= -->

    <div class="navbar">

        <div class="logo">
            JobPortal
        </div>

        <div class="nav-links">

            <a href="recruiterDashboard">
                Dashboard
            </a>

            <a href="companyProfile">
                Company Profile
            </a>

            <a href="postJob">
                Post Job
            </a>

            <a href="manageJobs">
                Manage Jobs
            </a>

            <a href="viewApplicants">
                Applicants
            </a>

            <a href="logout">
                Logout
            </a>

        </div>

    </div>


    <!-- ================= MAIN CONTENT ================= -->

    <div class="container">

        <div class="page-header">

            <h1>Post a New Job</h1>

            <p>
                Create a new job opportunity and find the right candidate.
            </p>

        </div>


        <!-- ================= ERROR MESSAGE ================= -->

        <s:actionerror />


        <!-- ================= POST JOB FORM ================= -->

        <div class="form-card">

            <h2>Job Details</h2>

            <form action="postJob" method="post">

                <!-- Job Title -->

                <div class="form-group">

                    <label for="jobTitle">
                        Job Title
                    </label>

                    <input
                        type="text"
                        id="jobTitle"
                        name="job.jobTitle"
                        placeholder="e.g. Java Developer"
                        required>

                </div>


                <!-- Company Name -->

                <div class="form-group">

                    <label for="companyName">
                        Company Name
                    </label>

                    <input
                        type="text"
                        id="companyName"
                        name="job.companyName"
                        placeholder="Enter company name"
                        required>

                </div>


                <!-- Job Description -->

                <div class="form-group">

                    <label for="jobDescription">
                        Job Description
                    </label>

                    <textarea
                        id="jobDescription"
                        name="job.jobDescription"
                        rows="6"
                        placeholder="Describe the role, responsibilities and requirements..."
                        required></textarea>

                </div>


                <!-- Required Skills -->

                <div class="form-group">

                    <label for="requiredSkills">
                        Required Skills
                    </label>

                    <input
                        type="text"
                        id="requiredSkills"
                        name="job.requiredSkills"
                        placeholder="Java, SQL, Spring"
                        required>

                </div>


                <!-- Location -->

                <div class="form-group">

                    <label for="location">
                        Location
                    </label>

                    <input
                        type="text"
                        id="location"
                        name="job.location"
                        placeholder="Chennai"
                        required>

                </div>


                <!-- Job Type -->

                <div class="form-group">

                    <label for="jobType">
                        Job Type
                    </label>

                    <select
                        id="jobType"
                        name="job.jobType"
                        required>

                        <option value="">
                            Select Job Type
                        </option>

                        <option value="Full Time">
                            Full Time
                        </option>

                        <option value="Part Time">
                            Part Time
                        </option>

                        <option value="Internship">
                            Internship
                        </option>

                        <option value="Contract">
                            Contract
                        </option>

                    </select>

                </div>


                <!-- Experience -->

                <div class="form-group">

                    <label for="experienceRequired">
                        Experience Required
                    </label>

                    <input
                        type="text"
                        id="experienceRequired"
                        name="job.experienceRequired"
                        placeholder="0-2 years"
                        required>

                </div>


                <!-- Salary -->

                <div class="form-row">

                    <div class="form-group">

                        <label for="salaryMin">
                            Minimum Salary
                        </label>

                        <input
                            type="number"
                            id="salaryMin"
                            name="job.salaryMin"
                            step="0.01"
                            min="0"
                            placeholder="15000">

                    </div>


                    <div class="form-group">

                        <label for="salaryMax">
                            Maximum Salary
                        </label>

                        <input
                            type="number"
                            id="salaryMax"
                            name="job.salaryMax"
                            step="0.01"
                            min="0"
                            placeholder="30000">

                    </div>

                </div>


                <!-- Submit -->

                <div class="form-actions">

                    <button
                        type="submit"
                        class="btn-primary">

                        Post Job

                    </button>

                </div>

            </form>

        </div>


        <!-- ================= BACK ================= -->

        <div class="back-link">

            <a href="recruiterDashboard">
                ← Back to Dashboard
            </a>

        </div>

    </div>

</body>

</html>