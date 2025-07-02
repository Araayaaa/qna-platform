package controller;

import java.io.IOException;
import java.sql.SQLException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import dao.QuestionDAO;
import model.Question;
import model.User;

/**
 * Servlet implementation class QuestionServlet
 */
@WebServlet("/ask")
public class QuestionServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
    private QuestionDAO questionDAO;
    
    @Override
    public void init() {
    	questionDAO = new QuestionDAO();
    }
    /**
     * @see HttpServlet#HttpServlet()
     */
    public QuestionServlet() {
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
		HttpSession session = request.getSession(false);
		User user = (session != null) ? (User) session.getAttribute("user") : null;
		
		if (user == null) {
			response.sendRedirect("login.jsp");
			return;
		}
		
		int userId = user.getId();
		String title = request.getParameter("title");
		String content = request.getParameter("content");
		
		Question q = new Question();
		q.setUserId(userId);
		q.setTitle(title);
		q.setContent(content);
		
		try {
			if (questionDAO.addQuestion(q)) {
				response.sendRedirect("index.jsp?msg=questionAdded");
			}
			else {
				response.getWriter().println("Gagal menambahkan pertanyaan.");
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
