package strategy;

import dao.QuestionDAO;
import model.Question;

import java.sql.SQLException;
import java.util.List;

public class SortByPopularity implements SortStrategy {
    public List<Question> getSortedQuestions() {
        QuestionDAO dao = new QuestionDAO();
        try {
			return dao.getAllSortedByVoteCount();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
    }
}
