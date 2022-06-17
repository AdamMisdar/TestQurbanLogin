package com.qurban.registration;

import java.io.*;
import javax.servlet.*;
import javax.servlet.annotation.*;
import javax.servlet.http.*;
import java.sql.*;

import com.qurban.dao.impl.ConnectionPool;


@WebServlet("/register")
public class RegistrationServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    public RegistrationServlet() {
        super();
    }

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		String client_username = request.getParameter("name"); 
		String client_email = request.getParameter("email");
		String client_password = request.getParameter("pass");
		String client_mobile = request.getParameter("contact");
		
		// requestDispatcher
		RequestDispatcher dispatcher = null;
		
		//Connection
		Connection connection = null;
		
/*		PrintWriter out = response.getWriter();
		
		out.print(client_username);
		out.print(client_email);
		out.print(client_password);
		out.print(client_mobile);
*/		
		try {
		/*	Class.forName("org.postgresql.Driver");
			connection = DriverManager.getConnection("jdbc:postgresql://localhost:5432/QurbanDatabase?useSSL=false", "postgres", "system");
		*/	
			connection = ConnectionPool.getInstance().getConnection();
			
			// SQL Query
			PreparedStatement pst = connection.prepareStatement("insert into client(client_username,client_password,client_email,client_mobile) values (?,?,?,?)");
			pst.setString(1, client_username);
			pst.setString(2, client_password);
			pst.setString(3, client_email);
			pst.setString(4, client_mobile);
			
			//Non-select query - return the number of rows affected - execute query
			int rowCount = pst.executeUpdate();
			
			//MVC
			dispatcher = request.getRequestDispatcher("registration.jsp");
			
			//to check if data is inserted
			if(rowCount > 0) {
				//redirect to register page , account created!
				request.setAttribute("status", "success");
			
			}
			else {
				request.setAttribute("status", "failed");
			}
			
			dispatcher.forward(request, response);
			
		} catch(Exception e) {
			e.printStackTrace();
			
		} finally {
			
			try {
				connection.close();
				
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
		}
		
		
	}
	

}
