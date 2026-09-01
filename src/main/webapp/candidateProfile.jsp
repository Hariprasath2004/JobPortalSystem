<%@ page contentType="text/html;charset=UTF-8" %>
<%@ taglib prefix="s" uri="/struts-tags" %>

<!DOCTYPE html>
<html>
<head>

    <title>My Profile - Job Portal</title>

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

        /* ================= HEADER ================= */

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

        /* ================= MAIN ================= */

        .container {
            width: 75%;
            max-width: 1100px;
            margin: 40px auto;
        }

        /* ================= CARD ================= */

        .card {
            background: white;
            border-radius: 12px;
            padding: 32px;
            margin-bottom: 28px;

            box-shadow: 0 3px 12px rgba(0, 0, 0, 0.08);
        }

        .card h1 {
            margin-top: 0;
            margin-bottom: 10px;
            font-size: 36px;
        }

        .card h2 {
            margin-top: 0;
            font-size: 24px;
            border-bottom: 1px solid #e5e7eb;
            padding-bottom: 15px;
        }

        .description {
            color: #6b7280;
            margin-bottom: 25px;
        }

        /* ================= PROFILE COMPLETION ================= */

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

        /* ================= FORM ================= */

        .form-group {
            margin-bottom: 22px;
        }

        .form-group label {
            display: block;
            font-weight: bold;
            margin-bottom: 8px;
        }

        .form-control {
            width: 100%;
            padding: 12px 14px;

            border: 1px solid #d1d5db;
            border-radius: 7px;

            font-size: 15px;
            background: white;
        }

        .form-control:focus {
            outline: none;
            border-color: #2563eb;
        }

        textarea.form-control {
            min-height: 130px;
            resize: vertical;
        }

        select.form-control {
            cursor: pointer;
        }

        /* ================= TWO COLUMN ================= */

        .form-row {
            display: grid;
            grid-template-columns: 1fr 1fr;
            gap: 25px;
        }

        /* ================= BUTTON ================= */

        .button-container {
            margin-top: 10px;
        }

        .btn-primary {
            background: #2563eb;
            color: white;

            border: none;
            border-radius: 7px;

            padding: 13px 25px;

            font-size: 16px;
            font-weight: bold;

            cursor: pointer;
        }

        .btn-primary:hover {
            background: #1d4ed8;
        }

        /* ================= RESUME ================= */

        .resume-note {
            color: #6b7280;
            font-size: 14px;
            margin-top: 8px;
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

        @media (max-width: 768px) {

            .container {
                width: 92%;
            }

            .navbar {
                padding: 0 20px;
            }

            .nav-links {
                gap: 12px;
            }

            .form-row {
                grid-template-columns: 1fr;
            }
        }

    </style>

</head>

<body>

<!-- ================= NAVBAR ================= -->

<div class="navbar">

    <div class="logo">
        JobPortal
    </div>

    <div class="nav-links">

        <a href="seekerDashboard">Dashboard</a>

<a href="jobSearch">Search Jobs</a>

<a href="myApplications">My Applications</a>

<a href="logout">Logout</a>

    </div>

</div>


<!-- ================= MAIN ================= -->

<div class="container">

    <!-- ================= PROFILE HEADER ================= -->

    <div class="card">

        <h1>My Profile</h1>

        <p class="description">
            Keep your profile updated to improve your job opportunities.
        </p>

        <div class="completion-header">

            <span>
                Profile Completion
            </span>

            <span>
                <s:property value="profile.profileCompletion"/>%
            </span>

        </div>

        <div class="progress-container">

            <div class="progress-bar"
                 style="width:<s:property value='profile.profileCompletion'/>%;">
            </div>

        </div>

    </div>


    <!-- ================= ERRORS ================= -->

    <s:if test="hasActionErrors()">

        <div class="error-box">

            <s:actionerror/>

        </div>

    </s:if>


    <!-- ================= PROFILE FORM ================= -->

    <s:form action="candidateProfile"
            method="post"
            theme="simple">

        <!-- ================= PERSONAL INFORMATION ================= -->

        <div class="card">

            <h2>
                Personal Information
            </h2>

            <div class="form-row">

                <!-- PHONE -->

                <div class="form-group">

                    <label>
                        Phone Number
                    </label>

                    <s:textfield
                        name="profile.phone"
                        value="%{profile.phone}"
                        cssClass="form-control"
                        placeholder="Enter your phone number"
                    />

                </div>


                <!-- DATE OF BIRTH -->

                <div class="form-group">

                    <label>
                        Date of Birth
                    </label>

                    <s:textfield
                        name="profile.dateOfBirth"
                        value="%{profile.dateOfBirth}"
                        cssClass="form-control"
                        type="date"
                    />

                </div>

            </div>


            <div class="form-row">

                <!-- GENDER -->

                <div class="form-group">

                    <label>
                        Gender
                    </label>

                    <s:select
                        name="profile.gender"
                        value="%{profile.gender}"
                        list="#{
                            'Male':'Male',
                            'Female':'Female',
                            'Other':'Other'
                        }"
                        headerKey=""
                        headerValue="Select Gender"
                        cssClass="form-control"
                    />

                </div>


                <!-- LOCATION -->

                <div class="form-group">

                    <label>
                        Location
                    </label>

                    <s:textfield
                        name="profile.location"
                        value="%{profile.location}"
                        cssClass="form-control"
                        placeholder="Enter your location"
                    />

                </div>

            </div>

        </div>


        <!-- ================= PROFESSIONAL INFORMATION ================= -->

        <div class="card">

            <h2>
                Professional Information
            </h2>


            <!-- HEADLINE -->

            <div class="form-group">

                <label>
                    Professional Headline
                </label>

                <s:textfield
                    name="profile.headline"
                    value="%{profile.headline}"
                    cssClass="form-control"
                    placeholder="Example: Java Backend Developer"
                />

            </div>


            <!-- SUMMARY -->

            <div class="form-group">

                <label>
                    Professional Summary
                </label>

                <s:textarea
                    name="profile.summary"
                    value="%{profile.summary}"
                    cssClass="form-control"
                    placeholder="Tell recruiters about yourself, your skills and experience..."
                />

            </div>


            <!-- RESUME -->

            <div class="form-group">

                <label>
                    Resume Path
                </label>

                <s:textfield
                    name="profile.resumePath"
                    value="%{profile.resumePath}"
                    cssClass="form-control"
                    placeholder="Enter resume file path"
                />

                <div class="resume-note">
                    Resume upload functionality will be added in the next stage.
                </div>

            </div>


            <!-- SAVE BUTTON -->

            <div class="button-container">

                <s:submit
                    value="Save / Update Profile"
                    cssClass="btn-primary"
                />

            </div>

        </div>

    </s:form>

</div>

</body>

</html>