

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class signin
 */
@WebServlet("/signin")
public class signin extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public signin() {
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
		System.out.println(name+pass);
		try
		{
			if(!name.equals("")&&!pass.equals(""))
			{
			Class.forName("com.mysql.jdbc.Driver");
			Connection con=DriverManager.getConnection("jdbc:mysql://localhost:3306/akshay","root","root");
			String sql="insert into demo values(?,?)";
			PreparedStatement ps=con.prepareStatement(sql);
			ps.setString(1, name);
			ps.setString(2, pass);
			int i=ps.executeUpdate();
			
			
			
			
			if(i>0)
			{
				request.getRequestDispatcher("login.html").include(request, response);
				out.println("Successfully signed up");
			}
			}
			else
			{
				request.getRequestDispatcher("signin.html").include(request, response);
				out.print("fill detais ");
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
