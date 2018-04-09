

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.sun.corba.se.spi.orbutil.fsm.Guard.Result;

/**
 * Servlet implementation class login
 */
@WebServlet("/login")
public class login extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public login() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		PrintWriter out=response.getWriter();
		String name=request.getParameter("name");
		String pass=request.getParameter("pass");
		
		String name1 = null,pass1 = null;
		try
		{
			Class.forName("com.mysql.jdbc.Driver");
			Connection con=DriverManager.getConnection("jdbc:mysql://localhost:3306/akshay","root","root");
			String sql="select * from demo where name=? AND pass=?";
			PreparedStatement ps=con.prepareStatement(sql);
			ps.setString(1, name);
			ps.setString(2, pass);
			ResultSet rs=ps.executeQuery();
			while(rs.next())
			{
			 name1=rs.getString(1);
			 pass1=rs.getString(2);
			}
			
			
			
			if(name.equals(name1) && pass.equals(pass1))
			{
				request.getRequestDispatcher("home.html").include(request, response);
				out.println("login successful");
			}
			
			else
			{
				request.getRequestDispatcher("signin.html").include(request, response);
				out.println("Enter correct Details");
			}
		}
		catch (Exception e) {
			// TODO: handle exception
			System.out.println(e);
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
