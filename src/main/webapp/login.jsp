<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>

<%@ taglib prefix="s" uri="/struts-tags"%>

<!DOCTYPE html>

<html>
<head>
<meta charset="UTF-8">
<title>Job Portal - Login</title>

<link rel="stylesheet" href="css/style.css">
</head>

<body>

	<nav class="navbar">

		<div class="logo">JobPortal</div>

		<div>
			<a href="index.jsp">Home</a> <a href="register.jsp">Register</a>
		</div>

	</nav>


	<div class="page-container">

		<div class="page-header">

			<h1>Login</h1>

			<p>Login to your JobPortal account.</p>

		</div>


		<div class="form-card">

			<h2>Login to your account</h2>

			<s:if test="hasActionErrors()">

				<div class="alert alert-error">
					<s:actionerror />
				</div>

			</s:if>


			<form action="login" method="post">

				<div class="form-group">

					<label>Email</label> <input type="email" name="email"
						class="form-control" required>

				</div>


				<div class="form-group">

					<label>Password</label> <input type="password" name="password"
						class="form-control" required>

				</div>


				<button type="submit" class="btn btn-primary">Login</button>

			</form>


			<p>
				Don't have an account? <a href="register.jsp">Create an account</a>
			</p>

		</div>

	</div>

</body>
</html>