package com.negociosit.negociosdevops;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.Properties;

import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.AddressException;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 * Servlet implementation class LeaveRequestController
 */
@WebServlet("/LeaveRequestController")
public class LeaveRequestController extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public LeaveRequestController() {
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
		String date = request.getParameter("date");
		String reason = request.getParameter("reason");
		
		
		   String substr = "\\\\'", regex = "'";
		    
		  String  replacedReason = reason.replaceAll(regex, substr);    
		
		
		String type = request.getParameter("type");
		String from =  (String) session.getAttribute("username");
		try {
			Class.forName("com.mysql.jdbc.Driver");
			java.sql.Connection con = java.sql.DriverManager.getConnection("jdbc:mysql://localhost:3306/negodev",
					"root", "password");
			java.sql.Statement st = con.createStatement();

			if (date != null && reason != null ) {
				st.executeUpdate(
						"insert into leaves (`from`, `date`, `reason`, `status`, `type`) values ('"
								+ from + "','" + date + "','" + replacedReason + "','Pending','"+type+"')");
				sendMailToAll(date,session,reason);
			}
			else 
			{ 
				//out.println("Invalid email id or password"); 
				out.println("<script type=\"text/javascript\">");
				out.println("alert('Please enter al the filelds');");
				out.println("location='RequestLeave.jsp';");
				out.println("</script>");
			} 

		} catch (Exception e2) {
			System.out.println(e2);
		}
		response.sendRedirect("RequestLeave.jsp");
		out.close();
		
		
	}

	
	private void sendMailToAll(String date,HttpSession session,String reason){
		final String username = "negodevops@gmail.com";
		final String password = "negociositsolutions";

		Properties props = new Properties();

		props.put("mail.smtp.auth", "true");
		props.put("mail.smtp.starttls.enable", "true");
		props.put("mail.smtp.host", "smtp.gmail.com");
		props.put("mail.smtp.port", "587");


		Session session1 = Session.getInstance(props, new GMailAuthenticator(username, password));
		try {
			String emailId = (String) session.getAttribute("username");
			Message message = new MimeMessage(session1);
			message.setFrom(new InternetAddress("pranavj@negociosit.com"));
			String ccAddressList = "amritha.prasanth@negociosit.com,praseetha.nair@negociosit.com,"+emailId+",pranavj@negociosit.com";
			message.setRecipients(Message.RecipientType.TO, InternetAddress.parse("aneesh.vijendran@negociosit.com"));
			message.setRecipients(Message.RecipientType.CC, InternetAddress.parse(ccAddressList));
			//message.setRecipients(Message.RecipientType.TO, InternetAddress.parse("pranavjayadev@gmail.com"));
			message.setRecipients(Message.RecipientType.BCC, InternetAddress.parse("pranavj@negociosit.com"));
			String yourName = (String) session.getAttribute("yourName");
			
			String subject = "Leave Request- " + yourName;
			message.setSubject(subject);
			
			StringBuffer buffer = new StringBuffer();
			buffer.append("Hi Aneesh, \n\n");
			
			buffer.append(yourName +" requested leave on "+ date+" \n\n");
			buffer.append("Reason: "+ reason+" \n\n");
			buffer.append("Awaiting your kind reply. \n\n");
			buffer.append("Regards, \n");
			buffer.append("Team Negocios Developers, \n");
			message.setText(buffer.toString());

			try {
				Thread.sleep(1000);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}


			Transport.send(message);
		} catch (AddressException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		} catch (MessagingException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
	}
}
