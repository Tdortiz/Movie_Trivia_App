package question_library;

import java.util.Random;

public class Question {

	/** String representing what type of question the question is */
	protected String type;
	/** String representing the question */
	protected String question;
	/** String representing choice A */
	protected String choiceA;
	/** String representing choice B */
	protected String choiceB;
	/** String representing choice C */
	protected String choiceC;
	/** String representing choice D */
	protected String choiceD;
	/** String representing the answer choice */
	protected String answer;
	/** String representing a comment */
	protected String comment;
	/** Seen field to allow randomization */
	protected boolean seen;

	public Question(String type,String text, String a, String b, String c,String d,  String answer){
		this.type = type;
		this.question = text;
		this.choiceA = null;
		this.choiceB = null;
		this.choiceC = null;
		this.choiceD = null;
		mixAnswers(a, b, c, d);
		this.answer = answer;
		this.seen = false;
	}

	private void mixAnswers(String a, String b, String c, String d){
		String[] strings = new String[4];
		strings[0] = a;
		strings[1] = b;
		strings[2] = c;
		strings[3] = d;

		Boolean[] marked = new Boolean[4];
		for(int i = 0; i < marked.length; i++){
			marked[i] = false;
		}

		Random rand = new Random();
		int index = -1;

		while( choiceA == null || choiceB == null || choiceC == null || choiceD == null ){

			if(choiceA == null){
				index = rand.nextInt(4);
				choiceA = strings[index];
				marked[index] = true;
				index = -1;
			}

			if(choiceB == null){
				while(index == -1) {
					index = rand.nextInt(4);
					if( marked[index] ) {
						index = -1;
					}
				}
				choiceB = strings[index];
				marked[index] = true;
				index = -1;
			}

			if(choiceC == null){
				while(index == -1) {
					index = rand.nextInt(4);
					if( marked[index] ) {
						index = -1;
					}
				}
				choiceC = strings[index];
				marked[index] = true;
				index = -1;
			}

			if(choiceD == null){
				while(index == -1) {
					index = rand.nextInt(4);
					if( marked[index] ) {
						index = -1;
					}
				}
				choiceD = strings[index];
				marked[index] = true;
				index = -1;
			}
		}
	}

	/**
	 * Gets the value of the question property.
	 *
	 * @return String representing the question
	 */
	public String getQuestion() {
		return this.question;
	}

	/**
	 * Sets the value of the question property.
	 *
	 * @param value to set question text to.
	 */
	public void setQuestion(String value) {
		this.question = value;
	}

	/**
	 * Gets the value of the choiceA property.
	 *
	 * @return String of question choiceA
	 */
	public String getChoiceA() {
		return choiceA;
	}

	/**
	 * Sets the value of the choiceA property.
	 *
	 * @param value to set choiceA to
	 */
	public void setChoiceA(String value) {
		this.choiceA = value;
	}

	/**
	 * Gets the value of the choiceB property.
	 *
	 * @return String of question choiceB
	 *
	 */
	public String getChoiceB() {
		return choiceB;
	}

	/**
	 * Sets the value of the choiceB property.
	 *
	 * @param value to set choiceB to
	 *
	 */
	public void setChoiceB(String value) {
		this.choiceB = value;
	}

	/**
	 * Gets the value of the choiceC property.
	 *
	 * @return String of question choiceC
	 */
	public String getChoiceC() {
		return choiceC;
	}

	/**
	 * Sets the value of the choiceC property.
	 *
	 * @param value to set choiceC to
	 */
	public void setChoiceC(String value) {
		this.choiceC = value;
	}

	/**
	 * Gets the value of the choiceD property.
	 *
	 * @return String of question choiceD
	 */
	public String getChoiceD() {
		return choiceD;
	}

	/**
	 * Sets the value of the choiceD property.
	 *
	 * @param value to set choiceD to
	 *
	 */
	public void setChoiceD(String value) {
		this.choiceD = value;
	}

	/**
	 * Gets the value of the answer.
	 *
	 * @return answer of the question
	 */
	public String getAnswer() {
		return answer;
	}

	/**
	 * Sets the value of the answer.
	 *
	 * @param value to set answer to
	 */
	public void setAnswer(String value) {
		this.answer = value;
	}

	/**
	 * Returns the type of question.
	 *
	 * @return the type of question (easy, standard, advanced)
	 */
	public String getType(){
		return this.type;
	}

	/**
	 * Retreives the comment of the question.
	 *
	 * @return comment of question
	 */
	public String getComment() {
		return comment;
	}

	/**
	 * Sets the value of the comment property.
	 * @param value of new comment
	 */
	public void setComment(String value) {
		this.comment = value;
	}

	/**
	 * Returns true if the question has been seen false if it hasnt.
	 * @return true if the question has been seen false if it hasnt.
	 */
	public boolean getSeen(){
		return this.seen;
	}

	/**
	 * Once the question has been seen this method is called to set it to true.
	 */
	public void setSeenTrue(){
		this.seen = true;
	}

	/**
	 * Method to print out the question.
	 * @return question printed out fully
	 */
	public String toString(){
		return question + ": " + choiceA + "|" + choiceB + "|" + choiceC + "|" + choiceD + "|" + answer +  "|\n";
	}
}
