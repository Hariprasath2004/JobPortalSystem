<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>

<%@ taglib prefix="s" uri="/struts-tags"%>

<!DOCTYPE html>

<html>

<head>

    <meta charset="UTF-8">

    <title>Job Portal - Register</title>

    <link rel="stylesheet" href="css/style.css">

</head>

<body>

    <!-- ================= NAVBAR ================= -->

    <nav class="navbar">

        <div class="logo">
            JobPortal
        </div>

        <div>
            <a href="index.jsp">Home</a>
            <a href="login.jsp">Login</a>
        </div>

    </nav>


    <!-- ================= PAGE ================= -->

    <div class="page-container">

        <div class="page-header">

            <h1>Create Account</h1>

            <p>
                Create your JobPortal account and start your journey.
            </p>

        </div>


        <!-- ================= REGISTER FORM ================= -->

        <div class="form-card">

            <h2>Create your account</h2>


            <!-- Struts Errors -->

            <s:if test="hasActionErrors()">

                <div class="alert alert-error">

                    <s:actionerror />

                </div>

            </s:if>


            <!-- ================= FORM ================= -->

            <form action="register" method="post">


                <!-- Full Name -->

                <div class="form-group">

                    <label>Full Name</label>

                    <input
                        type="text"
                        name="user.fullName"
                        class="form-control"
                        placeholder="Enter your full name"
                        required>

                </div>


                <!-- Email -->

                <div class="form-group">

                    <label>Email</label>

                    <input
                        type="email"
                        name="user.email"
                        class="form-control"
                        placeholder="Enter your email"
                        required>

                </div>


                <!-- Password -->

                <div class="form-group">

                    <label>Password</label>

                    <input
                        type="password"
                        name="user.password"
                        class="form-control"
                        placeholder="Create a password"
                        required>

                </div>


                <!-- Role -->

                <div class="form-group">

                    <label>Account Type</label>

                    <select
                        name="user.role"
                        class="form-control">

                        <option value="JOB_SEEKER">
                            Job Seeker
                        </option>

                        <option value="EMPLOYER">
                            Employer
                        </option>

                    </select>

                </div>


                <!-- Register -->

                <button
                    type="submit"
                    class="btn btn-primary">

                    Create Account

                </button>

            </form>


            <p>

                Already have an account?

                <a href="login.jsp">
                    Login here
                </a>

            </p>


        </div>

    </div>

</body>

</html>