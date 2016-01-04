package question_library;

/**
 * A class that represents an Advanced Question.
 * @author Thomas Ortiz
 */
public class AdvancedQuestion extends Question {

	/**
	 * Consturctor to build an Advanced Question
	 * @param type of question
	 * @param text question text
	 * @param a choice a
	 * @param b choice b
	 * @param c choice c
	 * @param d choice d
	 * @param answer answer (a, b, c, d)
	 */
	public AdvancedQuestion(String type,String text, String a, String b, String c,String d, String answer) {
		super(type, text, a, b, c, d, answer);
	}

}
