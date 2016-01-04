package movie101.quiz;

import java.io.InputStream;

import movie101.question.MovieQuestions;
import movie101.util.EmptyQuestionListException;
import question_library.QuestionException;
import question_library.QuestionReader;

/**
 * Specifies behaviors required for getting questions
 * and their possible answers, processing the user's answers, and
 * keeping track of the number of questions attempted and number
 * answered correctly.
 * @author Thomas Ortiz, Corey Colberg
 */
public class MovieQuiz implements QuizMaster {

	/** The questions */
	private MovieQuestions questions;

	/**
	 * Constructs the MovieQuiz object.
	 * @param ip File input stream
	 * @throws QuestionException if there is an error with QuestionReader
	 */
	public MovieQuiz(InputStream ip) throws QuestionException {
		QuestionReader read = new QuestionReader(ip);
		this.questions = new MovieQuestions(read.getElementaryQuestions(), read.getStandardQuestions(), read.getAdvancedQuestions());
	}

	/**
	 * Tests if there are any more questions.
	 * @return true if there are, false if there are not
	 */
	public boolean hasMoreQuestions(){
		return questions.hasMoreQuestions();
	}

	/**
	 * Get the text for the current question.
	 * @return the current question text
	 * @throws EmptyQuestionListException if there is no current question
	 */
	public String getCurrentQuestionText() throws EmptyQuestionListException{
		if(questions.getCurrentState().getCurrentQuestion() == null){
			throw new EmptyQuestionListException();
		}
		return questions.getCurrentQuestionText();
	}

	/**
	 * Get the possible answers for the current question
	 * @return the possible answers for the current question -- each answer
	 *         is a separate array element
	 * @throws EmptyQuestionListException if there is no current question
	 */
	public String[] getCurrentQuestionChoices() throws EmptyQuestionListException{
		if(questions.getCurrentState().getCurrentQuestion() == null){
			throw new EmptyQuestionListException();
		}
		return questions.getCurrentQuestionChoices();
	}

	/**
	 * Process the user's answer to the current question.
	 * @param s the user's answer to the question
	 * @return the graded response to the question
	 * @throws EmptyQuestionListException if there is no current question
	 */
	public String processAnswer(String s) throws EmptyQuestionListException{
		if(questions.getCurrentState().getCurrentQuestion() == null){
			throw new EmptyQuestionListException();
		}
		return questions.processAnswer(s);
	}

	/**
	 * Returns the questions the user answered correctly.
	 * @return the number of correct answers
	 */
	public String getNumCorrectQuestions(){
		return Integer.toString(questions.getNumCorrectQuestions());

	}

	/**
	 * Return the number of questions the user has attempted.
	 * @return the number of attempted questions.
	 */
	public String getNumAttemptedQuestions(){
		return Integer.toString(questions.getNumAttemptedQuestions());
	}

	/**
	 * Returns the MovieQuestions object questions.
	 * @return MovieQuestions
	 */
	public MovieQuestions getQuestions(){
		return this.questions;
	}
}
