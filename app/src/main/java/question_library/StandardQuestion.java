package question_library;

/**
 * A class that represents a Standard Question.
 * @author Thomas Ortiz
 */
public class StandardQuestion extends Question {

	/**
	 * Consturctor to build a Standard Question
	 * @param type of question
	 * @param text question text
	 * @param a choice a
	 * @param b choice b
	 * @param c choice c
	 * @param d choice d
	 * @param answer answer (a, b, c, d)
	 */
	public StandardQuestion(String type,String text, String a, String b, String c,String d, String answer) {
		super(type, text, a, b, c, d, answer);
	}

}
