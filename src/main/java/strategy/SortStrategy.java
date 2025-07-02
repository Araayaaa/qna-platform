package strategy;

import java.util.List;

import model.Question;

public interface SortStrategy {
	List<Question> getSortedQuestions();
}
