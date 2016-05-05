package com.negociosit.negociosdevops;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 * Servlet implementation class LoginController
 */
@WebServlet("/LoginController")
public class LoginController extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public LoginController() {
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
		response.setContentType("text/html");
		PrintWriter out = response.getWriter();
		HttpSession session = request.getSession();
		String password = request.getParameter("password");
		String email = request.getParameter("emailid");

		try {
			Class.forName("com.mysql.jdbc.Driver"); 
			java.sql.Connection con = DriverManager.getConnection("jdbc:mysql://127.0.0.1:3307/negociosit","admin7bhj6jv","aNThLVjmYSle"); 
			Statement st= con.createStatement(); 
			ResultSet rs=st.executeQuery("select * from user where email='"+email+"'"); 
			if(rs.next()) 
			{ 
				if(rs.getString(2).equals(password)) 
				{ 
					//out.println("welcome"+userid); 
					out.println("Welcome " + email + " <a href=\"index.jsp\">Back to main</a>");
					session.setAttribute("username", email);
					session.setAttribute("password", password);
					session.setAttribute("yourName", rs.getString(3));
					session.setAttribute("role", rs.getString(4));
					session.setAttribute("usertoken","authenticated");

					response.sendRedirect("Home.jsp");

				} 
				else 
				{ 
					//out.println("Invalid password try again"); 
					out.println("<script type=\"text/javascript\">");
					out.println("alert('Invalid username or password');");
					out.println("location='Login.jsp';");
					out.println("</script>");
				} 
			} 
			else 
			{ 
				//out.println("Invalid email id or password"); 
				out.println("<script type=\"text/javascript\">");
				out.println("alert('Invalid username or password');");
				out.println("location='Login.jsp';");
				out.println("</script>");
			} 

		} catch (Exception e2) {
			System.out.println(e2);
		}

		out.close();
	}
}
