package question_library;

/**
 * A class that represents an Elementary Question.
 * @author Thomas Ortiz
 */
public class ElementaryQuestion extends Question {
	private String hint;
	/**
	 * Consturctor to build an Elementary Question
	 * @param type of question
	 * @param text question text
	 * @param a choice a
	 * @param b choice b
	 * @param c choice c
	 * @param d choice d
	 * @param hint the hint to the question
	 * @param answer answer (a, b, c, d)
	 */
	public ElementaryQuestion(String type,String text, String a, String b, String c,String d, String hint, String answer) {
		super(type, text, a, b, c, d, answer);
		this.hint = hint;
	}

	/**
	 * Retrieves the hint of the question
	 * @return
	 */
	public String getHint() {
		return this.hint;
	}


}
