package question_library;

import java.util.ArrayList;
import java.util.List;

/**
 * A class that represents a list of all questions for the quiz.
 * @author Thomas Ortiz
 */
public class QuestionList {

	/** List of ElementaryQuestions */
	protected ArrayList<ElementaryQuestion> elementaryQuestions;
	/** List of StandardQuestions */
	protected ArrayList<StandardQuestion> standardQuestions;
	/** List of advanced Questions */
	protected ArrayList<AdvancedQuestion> advancedQuestions;

	/**
	 * Creates a list for each type of question with an initial size of 3.
	 */
	public QuestionList(){
		this.standardQuestions = new ArrayList<>(3);
		this.elementaryQuestions = new ArrayList<>(3);
		this.advancedQuestions = new ArrayList<>(3);
	}

	/**
	 * Returns the Elementary Question list.
	 *
	 * @return list of elementary questions
	 */
	public List<ElementaryQuestion> getElementaryQuestion() {
		if (elementaryQuestions == null) {
			elementaryQuestions = new ArrayList<>();
		}
		return this.elementaryQuestions;
	}

	/**
	 * Returns the Standard Question list.
	 *
	 * @return list of Standard questions
	 */
	public List<StandardQuestion> getStandardQuestion() {
		if (standardQuestions == null) {
			standardQuestions = new ArrayList<>();
		}
		return this.standardQuestions;
	}

	/**
	 * Returns the Advanced Question list.
	 *
	 * @return list of Advanced questions
	 */
	public List<AdvancedQuestion> getAdvancedQuestion() {
		if (advancedQuestions == null) {
			advancedQuestions = new ArrayList<>();
		}
		return this.advancedQuestions;
	}

	/**
	 * Adds a question based on its type.
	 *
	 * @param toAdd question to add to a list
	 */
	public void add(Question toAdd){
		if(toAdd.getType().equals("ElementaryQuestion")){
			ElementaryQuestion b = (ElementaryQuestion) toAdd;
			elementaryQuestions.add(b);

		} else if(toAdd.getType().equals("StandardQuestion")){
			StandardQuestion b = (StandardQuestion) toAdd;
			standardQuestions.add(b);
		} else if(toAdd.getType().equals("AdvancedQuestion")){
			AdvancedQuestion b = (AdvancedQuestion) toAdd;
			advancedQuestions.add(b);
		}
	}


}
