package controller;

import java.io.IOException;
import java.sql.SQLException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import dao.AnswerDAO;
import model.Answer;
import model.User;

/**
 * Servlet implementation class Answer
 */
@WebServlet("/answer")
public class AnswerServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
    private AnswerDAO answerDAO;
    
    public void init () {
    	answerDAO = new AnswerDAO();
    }
    /**
     * @see HttpServlet#HttpServlet()
     */
    public AnswerServlet() {
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
		
		int questionId = Integer.parseInt(request.getParameter("question_id"));
		String content = request.getParameter("content");
		
		Answer a = new Answer();
		a.setQuestionId(questionId);
		a.setUserId(user.getId());
		a.setContent(content);
		
		try {
			answerDAO.addAnswer(a);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		response.sendRedirect("question.jsp?id=" + questionId);
	}

}
