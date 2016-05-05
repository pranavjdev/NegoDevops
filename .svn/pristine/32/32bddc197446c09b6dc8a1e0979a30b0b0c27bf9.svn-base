package com.negociosit.negociosdevops;

import java.io.IOException;
import java.sql.ResultSet;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.google.android.gcm.server.Result;
import com.google.android.gcm.server.Sender;

/**
 * Servlet implementation class TestController
 */
@WebServlet("/TestController")
public class TestController extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public TestController() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		response.getWriter().append("Served at: ").append(request.getContextPath());
		
		try {
			Class.forName("com.mysql.jdbc.Driver");
			java.sql.Connection con = java.sql.DriverManager.getConnection("jdbc:mysql://localhost:3306/negodev",
					"root", "password");
			java.sql.Statement st = con.createStatement();
			ResultSet rs = st.executeQuery("SELECT * from status_details WHERE `from` = 'pranavj@negociosit.com'");
			StringBuffer buffer = new StringBuffer();
			ArrayList<Scrum> list = new ArrayList<Scrum>();

			while (rs.next()) {
				Scrum scrum = new Scrum();

				scrum.setTaskName(rs.getString(2));
				scrum.setStatus(rs.getString(3));
				scrum.setComment(rs.getString(4));
				scrum.setEffortLabel(rs.getString(5));
				buffer.append("Pranav");
				buffer.append("#NAME");
				buffer.append(rs.getString(2));
				buffer.append(" #STATUS ");
				buffer.append(rs.getString(3));
				buffer.append(" #COMMENT ");
				buffer.append(rs.getString(4));
				buffer.append(" #EFFORT ");
				buffer.append(rs.getString(5));
				buffer.append("\n");
				list.add(scrum);
			}
			
			ResultSet resultset = st.executeQuery("SELECT app_id from app_constants");
			String appID  = null;
			while (resultset.next()) {

				appID	= resultset.getString("app_id");
			}
			Sender sender = new Sender("AIzaSyBYSiAjphyXqqgGlCl2L61cVKNl-cKnDUQ");
			com.google.android.gcm.server.Message message = new com.google.android.gcm.server.Message.Builder()
					.timeToLive(3).delayWhileIdle(true).addData("message", buffer.toString()).build();
			System.out.println("regId: " + appID);
			Result result = sender.send(message, appID, 1);
			System.out.println(result.toString());
		} catch (IOException ioe) {
		} catch (Exception e) {
		}
		
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
