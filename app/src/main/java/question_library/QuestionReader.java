package question_library;

import java.io.InputStream;
import java.util.InputMismatchException;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.Scanner;

/**
 * A class that reads in questions from a file and then adds them to their respective lists
 * through the QuestionList class.
 */
public class QuestionReader {

	/** Collection of Questions */
	private QuestionList questionList;
	/** The input stream of the file containing questions */
	private InputStream ip;

	/**
	 * Creates a QuestionReader for the given filename and
	 * starts processing the question file.  If there is a problem
	 * processing the file, a QuestionException is thrown.
	 * @param ip input stream of file to process
	 * @throws QuestionException thrown when problem processing file
	 */
	public QuestionReader(InputStream ip) throws QuestionException {
		this.ip = ip;
		processQuestionFileIP();
	}

	/**
	 * Processes through the file containing questions and will construct and tell QuestionList
	 * to add the newly constructed question if all is okay.
	 * If it encounted an issue while scanning through file it will catch the except, disregard the
	 * question, stop processing through the file, and the quiz will begin.
	 * @throws QuestionException
	 */
	private void processQuestionFileIP() throws QuestionException {
		this.questionList = new QuestionList();
		Scanner readInput = new Scanner(ip);
		String type, text, ans1, ans2, ans3, ans4, hint = null, answer;
		Question temp = null;

		while(true){

			try {

				type = readInput.nextLine().trim();
				switch (type) {
					case "ElementaryQuestion":
						text = readInput.nextLine().trim();
						ans1 = readInput.nextLine().trim();
						ans2 = readInput.nextLine().trim();
						ans3 = readInput.nextLine().trim();
						ans4 = readInput.nextLine().trim();
						hint = readInput.nextLine().trim();
						answer = readInput.nextLine().trim();
						break;
					case "StandardQuestion":
						text = readInput.nextLine().trim();
						ans1 = readInput.nextLine().trim();
						ans2 = readInput.nextLine().trim();
						ans3 = readInput.nextLine().trim();
						ans4 = readInput.nextLine().trim();
						answer = readInput.nextLine().trim();
						break;
					default:  // if type.equals("AdvancedQuestion")
						text = readInput.nextLine().trim();
						ans1 = readInput.nextLine().trim();
						ans2 = readInput.nextLine().trim();
						ans3 = readInput.nextLine().trim();
						ans4 = readInput.nextLine().trim();
						answer = readInput.nextLine().trim();
						break;
				}

			} catch (NullPointerException e){
				System.out.println(e.getMessage());
				break;
			} catch (InputMismatchException e) {
				System.out.println(e.getMessage());
				break;
			} catch (IllegalArgumentException e){
				System.out.println(e.getMessage());
				break;
			} catch (NoSuchElementException e){
				System.out.println(e.getMessage());
				break;
			}

			if(type.equals("ElementaryQuestion"))
				temp = new ElementaryQuestion(type, text, ans1, ans2, ans3,ans4, hint, answer);
			if(type.equals("StandardQuestion"))
				temp = new StandardQuestion(type, text, ans1, ans2, ans3, ans4, answer);
			if(type.equals("AdvancedQuestion"))
				temp = new AdvancedQuestion(type, text, ans1, ans2, ans3, ans4, answer);

			questionList.add(temp);
		}
	}

	/**
	 * Gets the elementary question list
	 * @return elementary question list
	 */
	public List<ElementaryQuestion> getElementaryQuestions() {
		return questionList.getElementaryQuestion();
	}

	/**
	 * Gets the standard question list
	 * @return standard question list
	 */
	public List<StandardQuestion> getStandardQuestions() {
		return questionList.getStandardQuestion();
	}

	/**
	 * Gets the advanced question list
	 * @return advanced question list
	 */
	public List<AdvancedQuestion> getAdvancedQuestions() {
		return questionList.getAdvancedQuestion();
	}
}
