package com.tdtechweb;


import java.beans.Statement;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;


@WebServlet("/login")
public class Loginservlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
   

	
	
protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		String uemail =request.getParameter("username");
		String upwd =request.getParameter("password");
		
		RequestDispatcher dispatcher= null;
		Connection con=null;
		HttpSession session =request.getSession();
		if(uemail ==null ||uemail.equals("")) {
			request.setAttribute("status", "invalidemail");
			dispatcher=request.getRequestDispatcher("login.jsp");
			dispatcher.forward(request, response);
		}
		if(upwd ==null ||upwd.equals("")) {
			request.setAttribute("status", "invalidupwd");
			dispatcher=request.getRequestDispatcher("login.jsp");
			dispatcher.forward(request, response);
		}
		
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
			con =DriverManager.getConnection("jdbc:mysql://localhost:3306/techwebdb","root","");
			
			
			//PreparedStatement pst =con.prepareStatement("SELECT * FROM `users` WHERE uemail=? and upass=?");
		    // pst.setString(1, uemail);
			//pst.setString(2, upwd);
			
			
			String sql ="SELECT * FROM `users` WHERE uemail='"+uemail+"' and (upass='"+upwd+"')";
		
			PreparedStatement pst =con.prepareStatement(sql);
			
			
			
			ResultSet rs = pst.executeQuery();
			if (rs.next()) {
			session.setAttribute("id", rs.getString("id"));
			session.setAttribute("name", rs.getString("uname"));
			session.setAttribute("role", rs.getString("grade"));
			session.setAttribute("picture", rs.getString("picture"));
			
			dispatcher=request.getRequestDispatcher("index.jsp");
			
			}else {
				request.setAttribute("status","failed");
				dispatcher =request.getRequestDispatcher("login.jsp");
			}
			
		dispatcher.forward(request, response);
		} catch (Exception e) {
			e.printStackTrace();
		
			request.setAttribute("err",e.getMessage());
			dispatcher =request.getRequestDispatcher("login.jsp");
			dispatcher.forward(request, response);
			return;
		}finally {
			try {
				con.close();
			} catch (SQLException e) {
				e.printStackTrace();
				
				request.setAttribute("err",e.getMessage());
				dispatcher =request.getRequestDispatcher("login.jsp");
				dispatcher.forward(request, response);
				return;
			}
		}
	}

}
