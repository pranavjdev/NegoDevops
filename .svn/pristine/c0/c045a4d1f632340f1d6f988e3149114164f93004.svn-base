<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ page import="java.util.*"%>
<%@ page import="java.sql.DriverManager"%>
<%@ page import="java.sql.ResultSet"%>
<%@ page import="java.sql.Statement"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Negocios</title>
<link rel="stylesheet" type="text/css" href="stylesheet.css">
<script type="text/javascript">
	window.history.forward();
	function noBack() {
		window.history.forward();
		<%if ("authenticated".equals(session.getAttribute("usertoken"))) {
				Class.forName("com.mysql.jdbc.Driver");
				java.sql.Connection con = java.sql.DriverManager.getConnection("jdbc:mysql://localhost:3306/negodev",
						"root", "password");
				java.sql.Statement st = con.createStatement();
				String id = request.getParameter("id");
				if(id!=null && !id.equalsIgnoreCase("null") && id.trim().length()>0){
					int i = st.executeUpdate("delete from status_details where id = " + id);
					
				}
				
				String querry = "select * from status_details where `from`='"
						+ session.getAttribute("username") + "'";
				System.out.print("querry-----" + querry);
				ResultSet resultset = st.executeQuery(querry);

				int indexs = 0;
				while (resultset.next()) {
					indexs++;%>
	
	var Table = document.getElementById("outputTable");
	//Table.innerHTML = "";

	 var oRows = document.getElementById("outputTable")
			.getElementsByTagName('tr');
	var iRowCount = oRows.length; 
	
	
	
	var row = Table.insertRow(iRowCount);
	var cell1 = row.insertCell(0);
	var cell2 = row.insertCell(1);
	var cell3 = row.insertCell(2);
	var cell4 = row.insertCell(3);
	var cell5 = row.insertCell(4);
	var cell6 = row.insertCell(5);
	cell1.innerHTML =
		<%=indexs%>
			cell2.innerHTML ='<center><%=resultset.getString("taskname")%>'
				cell3.innerHTML ='<%=resultset.getString("status")%>'
					cell4.innerHTML ='<%=resultset.getString("comment")%>'
						cell5.innerHTML ='<%=resultset.getString("effort")%>'
							cell6.innerHTML ='<a href="TimeSheet.jsp?id=<%=resultset.getString("id")%>">delete</a>'
<%}con.close();
			} else {
				response.sendRedirect("Login.jsp");
			}
			
		
			%>
			
	}

	function validateForm() {
		var taskName = document.forms[0]["taskName"].value;
		if (taskName == null || taskName == "") {
			alert("Please enter task name");
			return false;
		}
		var comment = document.forms[0]["comment"].value;
		if (comment == null || comment == "") {
			alert("Please enter comment");
			return false;
		}
	}
</script>
</head>
<body onload="noBack();" onpageshow="if (event.persisted) noBack();"
	onunload="">
	<div
		style="width: 100%; font-size: 36px; line-height: 48px;  background-color: #185b99; color: white"><img src="https://d2f2vj6i7hhqf6.cloudfront.net/server/na-va-app-1/account/negociosit/logo?20151018070220" style="width: 50px;height: 40px;"/> <span>Negocios
		IT Solutions</span></div>
		


	<ul>
		<li><a href="Home.jsp">Home</a></li>
		<li><a  href="TimeSheet.jsp" class="active">Time Sheet</a></li>
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
<br>
<br>
<br>
	

	<br>
	<br>
	<div style="float: left;">
	   <fieldset id="action">
                  <legend><font color="blue"> Send your time sheet for today</font></legend>
		<form action="timesheet" method="post" id="addTaskForm"
			onsubmit="return validateForm()">
			<table style="width: 40%">
				<tr>
					<td>Task</td>
					<td><input type="text" id="taskName" name="taskname" /></td>
				</tr>
				<tr>
					<td>Status</td>
					<td><select id="status" name="status">
							<option value="OnGoing" selected>OnGoing</option>
							<option value="Done">Done</option>
							<option value="OnTesting">OnTesting</option>
					</select></td>
				</tr>
				<tr>
					<td>Comment</td>
					<td><input type="text" name="comment" id="comment" /></td>
				</tr>
				<tr>
					<td>Effort</td>
					<td><select id="effort" name="effort">
							<option value=".5hrs" selected>.5 Hrs</option>
							<option value="1hrs">1 Hrs</option>
							<option value="1.5hrs">1.5 Hrs</option>
							<option value="2hrs">2 Hrs</option>
							<option value="2.5hrs">2.5 Hrs</option>
							<option value="3hrs">3 Hrs</option>
							<option value="3.5hrs">3.5 Hrs</option>
							<option value="4hrs">4 Hrs</option>
							<option value="4.5hrs">4.5 Hrs</option>
							<option value="5hrs">5 Hrs</option>
							<option value="5.5hrs">5.5 Hrs</option>
							<option value="6hrs">6 Hrs</option>
							<option value="6.5hrs">6.5 Hrs</option>
							<option value="7hrs">7 Hrs</option>
							<option value="7.5hrs">7.5 Hrs</option>
							<option value="8hrs">8 Hrs</option>

					</select></td>
				</tr>
			</table>
			<br> <br> <input type="submit" value="Add Task" />
		</form>

		<!-- <button onclick="myCreateFunction()">Add task</button>  -->
</fieldset>
	</div>

	<div style="float: right;">


		<table id="outputTable" width="80%" border="1" name="outputTable">
			<tr>
				<th>Index</th>
				<th>Task</th>
				<th>Status</th>
				<th>Comment</th>
				<th>Effort</th>
				<th>Action</th>
			</tr>

		</table>

		<br> <br>
		<form action="scrum" method="post">
			<input type="submit" value="Send Scrum Report" />
		</form>
		<br> <br>
	</div>

</body>
</html>