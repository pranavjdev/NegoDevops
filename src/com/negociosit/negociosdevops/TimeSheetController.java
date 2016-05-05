package com.negociosit.negociosdevops;

import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;


/**
 * Servlet implementation class TimeSheetController
 */
@WebServlet("/TimeSheetController")
public class TimeSheetController extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public TimeSheetController() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		response.getWriter().append("Served at: ").append(request.getContextPath());
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		PrintWriter out = response.getWriter();
		HttpSession session = request.getSession();
		String taskname = request.getParameter("taskname");
		String status = request.getParameter("status");
		String comment = request.getParameter("comment");
		String effort = request.getParameter("effort");
		String from =  (String) session.getAttribute("username");
		System.out.println("from"+from);
		
		try {
			Class.forName("com.mysql.jdbc.Driver");
			java.sql.Connection con = java.sql.DriverManager.getConnection("jdbc:mysql://localhost:3306/negodev",
					"root", "password");
			java.sql.Statement st = con.createStatement();

			if (taskname != null && comment != null && effort != null && status != null) {
				int affectedRows = st.executeUpdate(
						"insert into status_details (`from`, `taskname`, `status`, `comment`, `effort`) values ('"
								+ from + "','" + taskname + "','" + status + "','" + comment + "','" + effort + "')");
				if (affectedRows == 0) {
					out.println("<script type=\"text/javascript\">");
					out.println("alert('Failed to add this task');");
					out.println("location='Login.jsp';");
					out.println("</script>");
				}
			} else{
				out.println("<script type=\"text/javascript\">");
				out.println("alert('Please enter all the fields');");
				out.println("location='TimeSheet.jsp';");
				out.println("</script>");
			}
			
			response.sendRedirect("TimeSheet.jsp");
		} catch (Exception e2) {
			System.out.println(e2);
			
			out.println("<script type=\"text/javascript\">");
			out.println("alert('Task name contains maximum of 90 characters');");
			out.println("location='TimeSheet.jsp';");
			out.println("</script>");
			
			/*out.println("<script type=\"text/javascript\">");
			out.println("alert('Task name contains maximum of 90 characters');");
			out.println("location='TimeSheet.jsp';");
			out.println("</script>");*/
		}
		
		out.close();
		
		
	}

}
