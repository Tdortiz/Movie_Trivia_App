package question_library;

/**
 * Exception class that is thrown whenever there is a problem with
 * processing or creating an XML file.
 *
 * @author Dr. Sarah Heckman (heckman@csc.ncsu.edu)
 */
public class QuestionException extends Exception {

	/** Used for serialization */
	private static final long serialVersionUID = 1L;

	/**
	 * Creates a QuestionException with a pre set message
	 */
	public QuestionException() {
		this("QuestionException - error processing intelligent tutoring question file");
	}

	/**
	 * Creates a QuestionException with a provided message
	 * @param message message to include in the exception information
	 */
	public QuestionException(String message) {
		super(message);
	}

}
