package strategy;

import java.sql.SQLException;
import java.util.Comparator;
import java.util.List;

import dao.QuestionDAO;
import model.Question;

public class SortByNewest implements SortStrategy {
	
	@Override
	public List<Question> getSortedQuestions() {
		QuestionDAO questions = new QuestionDAO();
		try {
			return questions.getAllSortedByCreatedAt();
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
