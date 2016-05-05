<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<link rel="stylesheet" type="text/css" href="stylesheet.css">
<title>Negocios</title>
<SCRIPT type="text/javascript">
    window.history.forward();
    function noBack() { window.history.forward(); }
</SCRIPT>
</head>
<body onload="noBack();"
    onpageshow="if (event.persisted) noBack();" onunload="">
	<div
		style="width: 100%; font-size: 36px; line-height: 48px;  background-color: #185b99; color: white"><img src="https://d2f2vj6i7hhqf6.cloudfront.net/server/na-va-app-1/account/negociosit/logo?20151018070220" style="width: 50px;height: 40px;"/> <span>Negocios
		IT Solutions</span></div>
		
		
		<ul>
		<li><a href="Home.jsp">Home</a></li>
		<li><a href="TimeSheet.jsp" class="active">Time Sheet</a></li>
		 <li><a href="RequestLeave.jsp">Request Leave</a></li>
		<div class="dropdown">
			<li class="dropbtn"><a href="#news">Profile</a>
				<div class="dropdown-content">
					<a href="ChangePassword.jsp">Change Password</a>
				</div></li>
		</div>

		<ul style="float: right; list-style-type: none;">
			<li id="myName"><a href="#about">About</a></li>
			<li><a href="LogoutController">Logout</a></li>
		</ul>
	</ul>
		
		
		<%
		if ("authenticated".equals(session.getAttribute("usertoken"))) {
			//out.println("Welcome "+session.getAttribute("username")); 
		} else {
			response.sendRedirect("Login.jsp");
		}
	%>
	<div
		style="position: absolute; top: 40%; left: 50%; margin-top: -40px; margin-left: -50px;">
		<p align="center">
			<font color="Blue"> <b>Change your password</b></font>
		</p>
	</div>
	<div
		style="position: absolute; top: 50%; left: 50%; margin-top: -50px; margin-left: -50px;">
		<form action="changepassword" method="post">

			<table style="width: 100%" >
				<tr>
					<td>Current password:</td>
					<td><input type="password" name="currentpassword" /></td>
				</tr>
				<tr>
					<td>New password:</td>
					<td><input type="password" name="newpassword" /></td>
				</tr>
				<tr>
					<td>Confirm password:</td>
					<td><input type="password" name="confirmpassword" /></td>
				</tr>
				<tr>
				<td></td>
				</tr>
				<tr>
					<td></td>
					<td><input type="submit" value="Change password" /></td>
				</tr>
			</table>
		</form>
	</div>
</body>
</html>