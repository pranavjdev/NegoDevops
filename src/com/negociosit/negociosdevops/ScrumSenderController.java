 package com.negociosit.negociosdevops;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.ResultSet;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.Properties;

import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.google.android.gcm.server.Result;
import com.google.android.gcm.server.Sender;

/**
 * Servlet implementation class ScrumSenderController
 */
@WebServlet("/ScrumSenderController")
public class ScrumSenderController extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public ScrumSenderController() {
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
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		System.out.println("POST");
		PrintWriter out = response.getWriter();
		try {

			HttpSession session = request.getSession();
			String from = (String) session.getAttribute("username");
			String yourName = (String) session.getAttribute("yourName");
			Class.forName("com.mysql.jdbc.Driver");
			java.sql.Connection con = java.sql.DriverManager.getConnection("jdbc:mysql://localhost:3306/negodev",
					"root", "password");
			java.sql.Statement st = con.createStatement();
			ResultSet rs = st.executeQuery("SELECT * from status_details WHERE `from` = '" + from + "'");
			StringBuffer buffer = new StringBuffer();
			ArrayList<Scrum> list = new ArrayList<Scrum>();

			while (rs.next()) {
				Scrum scrum = new Scrum();

				scrum.setTaskName(rs.getString(2));
				scrum.setStatus(rs.getString(3));
				scrum.setComment(rs.getString(4));
				scrum.setEffortLabel(rs.getString(5));
				buffer.append(yourName);
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

			

			if ((list.size() > 0)) {
				final String username = "negodevops@gmail.com";
				final String password = "negociositsolutions";

				Properties props = new Properties();
				props.put("mail.smtp.auth", "true");
				props.put("mail.smtp.starttls.enable", "true");
				props.put("mail.smtp.host", "smtp.gmail.com");
				props.put("mail.smtp.port", "587");

				Session session1 = Session.getInstance(props, new GMailAuthenticator(username, password));
				try {

					Message message = new MimeMessage(session1);
					message.setFrom(new InternetAddress("negodevops@gmail.com"));
					message.setRecipients(Message.RecipientType.TO, InternetAddress.parse("pranavj@negociosit.com"));

					String subject = "Scrum Report- " + yourName;
					message.setSubject(subject);

					message.setText(buffer.toString());

					try {
						Thread.sleep(1000);
					} catch (InterruptedException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}

/*					ResultSet resultset = st.executeQuery("SELECT app_id from app_constants");
					while (resultset.next()) {

						String appID = resultset.getString("app_id");
						System.out.println("appID=="+appID);
						sendNotificationToAdmin(buffer.toString(), appID);
					}*/

					Transport.send(message);
					sendReportToMe(list, from);
					System.out.println("successfully send mail...");
					session.invalidate();
					st.executeUpdate("delete from status_details where `from`='" + from + "'");

					out.println("<script type=\"text/javascript\">");
					out.println("alert('Scrum successfully send');");
					out.println("location='Login.jsp';");
					out.println("</script>");

				} catch (MessagingException e1) {

				}
			} else {
				out.println("<script type=\"text/javascript\">");
				out.println("alert('Please add your task');");
				out.println("location='TimeSheet.jsp';");
				out.println("</script>");
			}
			con.close();
		} catch (Exception ex) {
			out.println("<script type=\"text/javascript\">");
			out.println("alert('Please check your internet connection');");
			out.println("location='TimeSheet.jsp';");
			out.println("</script>");
		}
		
	}

	private void sendNotificationToAdmin(String body, String id) {
		try {

			Sender sender = new Sender("AIzaSyBYSiAjphyXqqgGlCl2L61cVKNl-cKnDUQ");
			com.google.android.gcm.server.Message message = new com.google.android.gcm.server.Message.Builder()
					.timeToLive(3).delayWhileIdle(true).addData("message", body).build();
			System.out.println("regId: " + id);
			Result result = sender.send(message, id, 1);
			System.out.println(result.toString());
		} catch (IOException ioe) {
		} catch (Exception e) {
		}
	}

	private void sendReportToMe(ArrayList<Scrum> scrumList, String yourEmalId) {
		
		final String username = "negodevops@gmail.com";
		final String password = "negociositsolutions";

		Properties props = new Properties();
		props.put("mail.smtp.auth", "true");
		props.put("mail.smtp.starttls.enable", "true");
		props.put("mail.smtp.host", "smtp.gmail.com");
		props.put("mail.smtp.port", "587");
		props.put("mail.smtp.ssl.trust", "smtp.gmail.com");

		Session session = Session.getInstance(props, new GMailAuthenticator(username, password));
		try {

			Message message = new MimeMessage(session);
			message.setFrom(new InternetAddress("negodevops@gmail.com"));
			System.out.println("yourEmalId=" + yourEmalId);
			if (!yourEmalId.isEmpty()) {
				message.setRecipients(Message.RecipientType.TO, InternetAddress.parse(yourEmalId));
			} else {
				message.setRecipients(Message.RecipientType.TO, InternetAddress.parse("pranavj@negociosit.com"));
			}
			DateFormat dateFormat = new SimpleDateFormat("dd-MM-yyyy");
			Date date = new Date();
			String subject = "Scrum Report- " + dateFormat.format(date);
			message.setSubject(subject);

			StringBuffer buffer = new StringBuffer();
			buffer.append("<style>table, th, td {border: 1px solid black;border-collapse: collapse;}");
			buffer.append("th, td {padding: 5px;text-align: left;}</style>");
			buffer.append("<table style=\"width:100%;\">");
			buffer.append("<tr><th>TaskName</th><th>Status</th><th>Comment</th><th>Effort</th></tr>");
			for (Scrum scrum : scrumList) {

				buffer.append("<tr><td>" + scrum.getTaskName() + "</td><td>" + scrum.getStatus() + "</td><td>"
						+ scrum.getComment() + "</td><td>" + scrum.getEffortLabel() + "</td></tr>");

			}
			buffer.append("</table>");
			message.setContent(buffer.toString(), "text/html");
			Transport.send(message);

		} catch (MessagingException e1) {
		}
	}

}
