<%@ page language="java"
    contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>

<%@ taglib prefix="s" uri="/struts-tags" %>

<!DOCTYPE html>

<html>

<head>

    <meta charset="UTF-8">

    <title>Search Jobs - JobPortal</title>

    <link rel="stylesheet"
          type="text/css"
          href="${pageContext.request.contextPath}/css/style.css">

</head>

<body>

    <!-- =========================
         NAVBAR
         ========================= -->

    <div class="navbar">

        <div class="logo">
            JobPortal
        </div>

        <div>

            <a href="seekerDashboard">
                Dashboard
            </a>

            <a href="jobSearch">
                Search Jobs
            </a>

            <a href="myApplications">
                My Applications
            </a>

            <a href="logout">
                Logout
            </a>

        </div>

    </div>


    <!-- =========================
         MAIN CONTENT
         ========================= -->

    <div class="container">

        <!-- Page Header -->

        <div class="page-header">

            <h1>
                Search Jobs
            </h1>

            <p>
                Find your next career opportunity.
            </p>

        </div>


        <!-- =========================
             SUCCESS / ERROR MESSAGES
             ========================= -->

        <s:if test="hasActionMessages()">

            <div class="alert alert-success">

                <s:actionmessage />

            </div>

        </s:if>


        <s:if test="hasActionErrors()">

            <div class="alert alert-error">

                <s:actionerror />

            </div>

        </s:if>


        <!-- =========================
             JOB LIST
             ========================= -->

        <s:if test="jobs != null && !jobs.isEmpty()">

            <s:iterator value="jobs">

                <div class="job-card">

                    <!-- Job Title -->

                    <h2>
                        <s:property value="jobTitle"/>
                    </h2>


                    <!-- Company -->

                    <p class="job-info">

                        <strong>
                            Company:
                        </strong>

                        <s:property value="companyName"/>

                    </p>


                    <!-- Location -->

                    <p class="job-info">

                        <strong>
                            Location:
                        </strong>

                        <s:property value="location"/>

                    </p>


                    <!-- Job Type -->

                    <p class="job-info">

                        <strong>
                            Job Type:
                        </strong>

                        <s:property value="jobType"/>

                    </p>


                    <!-- Experience -->

                    <p class="job-info">

                        <strong>
                            Experience:
                        </strong>

                        <s:property value="experienceRequired"/>

                    </p>


                    <!-- Skills -->

                    <p class="job-info">

                        <strong>
                            Required Skills:
                        </strong>

                        <s:property value="requiredSkills"/>

                    </p>


                    <!-- Salary -->

                    <p class="job-info">

                        <strong>
                            Salary:
                        </strong>

                        <s:if test="salaryMin != null">

                            ₹<s:property value="salaryMin"/>

                        </s:if>

                        <s:if test="salaryMax != null">

                            -

                            ₹<s:property value="salaryMax"/>

                        </s:if>

                    </p>


                    <!-- Description -->

                    <div class="job-description">

                        <strong>
                            Job Description
                        </strong>

                        <p>

                            <s:property value="jobDescription"/>

                        </p>

                    </div>


                    <br>


                    <!-- =========================
                         APPLY BUTTON
                         ========================= -->

                    <s:if test="appliedJobIds.contains(jobId)">

                        <button
                            type="button"
                            class="btn btn-disabled"
                            disabled>

                            Already Applied

                        </button>

                    </s:if>


                    <s:else>

                        <form
                            action="applyJob"
                            method="post">

                            <input
                                type="hidden"
                                name="jobId"
                                value="<s:property value='jobId'/>">

                            <button
                                type="submit"
                                class="btn btn-primary">

                                Apply Job

                            </button>

                        </form>

                    </s:else>

                </div>

            </s:iterator>

        </s:if>


        <!-- =========================
             NO JOBS
             ========================= -->

        <s:else>

            <div class="form-card">

                <h2>
                    No Jobs Available
                </h2>

                <p>
                    Recruiters have not posted any jobs yet.
                </p>

            </div>

        </s:else>


        <br>


        <!-- Back -->

        <a
            href="seekerDashboard"
            class="btn btn-secondary">

            Back to Dashboard

        </a>

    </div>

</body>

</html>