package com.tdtechweb;

import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import jakarta.*;

@WebServlet("/usersm")
public class usermanagement extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
    


	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		String id =request.getParameter("userid");
		String action=request.getParameter("action");
		RequestDispatcher dispatcher= null;
		Connection con=null;
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
			con =DriverManager.getConnection("jdbc:mysql://localhost:3306/techwebdb","root","");
			
			if(action.equals("delete")) {
				
				String sql ="DELETE FROM `users` WHERE `users`.`id` ='"+id+"'";
				PreparedStatement pst =con.prepareStatement(sql);
				pst.executeUpdate();
				
					request.setAttribute("status","failed");
					dispatcher =request.getRequestDispatcher("Analyses.jsp");
					dispatcher.forward(request, response);
				
				
			}else if (action.equals("block")) {
				String sql ="UPDATE `users` SET `statuss` = 'blocked' WHERE `users`.`id` ='"+id+"'";
				PreparedStatement pst =con.prepareStatement(sql);
				pst.executeUpdate();
				
				
					dispatcher =request.getRequestDispatcher("Analyses.jsp");
					dispatcher.forward(request, response);
			}
			else if (action.equals("select")) {
//				String sql ="select * FROM `users` WHERE `users`.`id` ="+id+"";
				String sql ="select * FROM `users` WHERE `users`.`id` ="+id+"";
				PreparedStatement pst =con.prepareStatement(sql);
				ResultSet rs = pst.executeQuery();
				  
				if (rs.next()) {
					request.setAttribute("idsea", rs.getString("id"));
					request.setAttribute("namesea", rs.getString("uname"));
					request.setAttribute("rolesea", rs.getString("grade"));
					request.setAttribute("mobilesea", rs.getString("umobile"));
					request.setAttribute("statussea", rs.getString("statuss"));
					request.setAttribute("displaysearch","table");
					dispatcher=request.getRequestDispatcher("Analyses.jsp");
					
					}else {
						request.setAttribute("err","there is an err or the user not found");
						dispatcher =request.getRequestDispatcher("Analyses.jsp");
					}
				dispatcher.forward(request, response);
			}
		
		} catch (Exception e) {
			e.printStackTrace();
			request.setAttribute("err",e.getMessage());
			dispatcher =request.getRequestDispatcher("Analyses.jsp");
			dispatcher.forward(request, response);
			return;
		}finally {
			try {
				con.close();
			} catch (SQLException e) {
				e.printStackTrace();
				request.setAttribute("err",e.getMessage());
				dispatcher =request.getRequestDispatcher("Analyses.jsp");
				dispatcher.forward(request, response);
				
			}
		}
		
	}



}
