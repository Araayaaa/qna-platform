package controller;

import java.io.IOException;
import java.sql.SQLException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import dao.VoteDAO;
import model.User;

/**
 * Servlet implementation class VoteServlet
 */
@WebServlet("/vote")
public class VoteServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
    private VoteDAO voteDAO;
    
    @Override
    public void init() {
    	voteDAO = new VoteDAO();
    }
    /**
     * @see HttpServlet#HttpServlet()
     */
    public VoteServlet() {
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
		
		String type = request.getParameter("type");
		int id = Integer.parseInt(request.getParameter("id"));
		int questionId = Integer.parseInt(request.getParameter("question_id"));

		int userId = user.getId();
		boolean alreadyVoted = false;

		if ("question".equals(type)) {
		    try {
				alreadyVoted = voteDAO.hasUserVotedForQuestion(userId, id);
			} catch (ClassNotFoundException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		    if (!alreadyVoted)
				try {
					voteDAO.upvoteQuestion(userId, id);
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				} catch (ClassNotFoundException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
		    response.sendRedirect("question.jsp?id=" + id);
		} else if ("answer".equals(type)) {
		    try {
				alreadyVoted = voteDAO.hasUserVotedForAnswer(userId, id);
			} catch (ClassNotFoundException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		    if (!alreadyVoted)
				try {
					voteDAO.upvoteAnswer(userId, id);
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				} catch (ClassNotFoundException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
		    // Redirect ke halaman pertanyaan dari jawaban
		    response.sendRedirect("question.jsp?id=" + questionId);
		}

	}

}
