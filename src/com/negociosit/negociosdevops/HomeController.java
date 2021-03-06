package com.negociosit.negociosdevops;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.Vector;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 * Servlet implementation class HomeController
 */
@WebServlet("/HomeController")
public class HomeController extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public HomeController() {
		super();
		// TODO Auto-generated constructor stub
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		System.out.println("GET");
		String action = request.getParameter("action");
		String id = request.getParameter("id");

		if (action != null && id != null && action.trim().length() > 0 && id.trim().length() > 0) {
			try {
				Class.forName("com.mysql.jdbc.Driver");
				java.sql.Connection con = java.sql.DriverManager.getConnection("jdbc:mysql://localhost:3306/negodev",
						"root", "password");
				java.sql.Statement st = con.createStatement();
				if (action.equalsIgnoreCase("del"))
					st.executeUpdate("delete from leaves where id=" + id + "");
				else if (action.equalsIgnoreCase("approve")) {
					 st.executeUpdate("update leaves set status='Approved' where id='" + id + "'");
				} else if (action.equalsIgnoreCase("reject")) {
					 st.executeUpdate("update leaves set status='Rejected' where id='" + id + "'");
				}
			} catch (Exception ex) {
			}
		}
		response.sendRedirect("Home.jsp");

	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// TODO Auto-generated method stub
		// printTable(request, response);
		String task = null;
		String status = null;
		String comment = null;
		String effort = null;
		PrintWriter out = response.getWriter();

		try {
			HttpSession session = request.getSession();
			String from = (String) session.getAttribute("username");

			if (from != null && from.trim().length() > 0 && !from.equalsIgnoreCase("null")) {
				task = request.getParameter("taskname");
				status = request.getParameter("status");
				comment = request.getParameter("comment");
				effort = request.getParameter("effort");

				Class.forName("com.mysql.jdbc.Driver");
				java.sql.Connection con = java.sql.DriverManager.getConnection("jdbc:mysql://localhost:3306/negodev",
						"root", "password");
				java.sql.Statement st = con.createStatement();

				if (task != null && comment != null && effort != null && status != null) {
					int affectedRows = st.executeUpdate(
							"insert into status_details (`from`, `taskname`, `status`, `comment`, `effort`) values ('"
									+ from + "','" + task + "','" + status + "','" + comment + "','" + effort + "')");

					if (affectedRows == 0) {
						out.println("<script type=\"text/javascript\">");
						out.println("alert('Failed to add this task');");
						out.println("location='Login.jsp';");
						out.println("</script>");
					}
				} else {
					out.println("<script type=\"text/javascript\">");
					out.println("alert('Please enter all the fields');");
					out.println("location='Login.jsp';");
					out.println("</script>");
				}
				/*
				 * else { } ResultSet rs = st.executeQuery(
				 * "SELECT MAX(ID) from status_details WHERE `from` = '" + from
				 * + "'"); if (rs.next()) { if (rs.getInt(1) > 0) { int i =
				 * st.executeUpdate("delete from status_details where id = " +
				 * rs.getInt(1)); } }
				 * 
				 * }
				 */
				con.close();
			} else {
				// response.sendRedirect(request.getContextPath() +
				// "/index.jsp");

				out.println("<script type=\"text/javascript\">");
				out.println("alert('password and confirm password not matching');");
				out.println("location='Login.jsp';");
				out.println("</script>");
			}

		} catch (Exception ex) {
			System.out.println("Exception" + ex.getLocalizedMessage());
		}

		doGet(request, response);

		// getServletContext().getRequestDispatcher("/home").forward(request,
		// response);
	}

	public void printTable(HttpServletRequest request, HttpServletResponse response) throws IOException {

		response.setContentType("text/html");
		PrintWriter out = response.getWriter();

		HtmlUtils hu = new HtmlUtils();

		out.print(hu.createHtmlHeader("Print Table"));
		out.print(hu.getTableHead("center", 1));

		out.print(hu.getTH("center", "First Name"));
		out.print(hu.getTH("center", "Last Name"));
		out.print(hu.getTH("center", "Favorite Color"));
		out.print(hu.getTH("center", "Gender"));

		Vector av = new Vector();

		av.addElement("John");
		av.addElement("Sample");
		av.addElement("Purple");
		av.addElement("Male");

		av.addElement("Joe");
		av.addElement("Bloggs");

		av.addElement("Green");
		av.addElement("Male");

		av.addElement("Fanny");
		av.addElement("May");
		av.addElement("Blue");
		av.addElement("Female");

		av.addElement("Joeline");
		av.addElement("Bloggs");
		av.addElement("Red");
		av.addElement("Female");

		out.print(hu.getTableContents("center", av, 4));
		out.print(hu.getHtmlFooter());

	}

}
