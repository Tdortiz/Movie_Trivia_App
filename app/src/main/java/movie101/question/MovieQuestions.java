package movie101.question;

import java.util.*;
import movie101.util.EmptyQuestionListException;
import question_library.AdvancedQuestion;
import question_library.ElementaryQuestion;
import question_library.Question;
import question_library.StandardQuestion;

/**
 * Handles the question aspect of the program by keeping track of the number of correct answers,
 * number attempts questions, and uses a finite state machine to keep track of the lists of 
 * questions.
 * @author Thomas Ortiz, Corey Colberg
 */
public class MovieQuestions{
	
	/** Number of correct answers */
	private int numCorrectAnswers;
	/** Number of attempted questions */
	private int numAttemptQuests;
	/** Correct! string */
	private static final String CORRECT = "Correct!";
	/** Incorrect! string */
	private static final String INCORRECT = "Incorrect!";
	/** Empty Space */
	private static final String SEPARATOR = " ";
	/** The Advance Question State of the finite state machine */
	private AdvancedQuestionState advQuestionState;
	/** The Standard Question State of the finite state machine */
	private StandardQuestionState staQuestionState;
	/** The Elementary Question State of the finite state machine */
	private ElementaryQuestionState eleQuestionState;
	/** The current Question State */
	private QuestionState currentQuestionState;

	/** A list of elementary questions */
	private ArrayList<Question> eleQuestion;
	/** A list of standard questions */
	private ArrayList<Question> staQuestion;
	/** A list of advanced questions */
	private ArrayList<Question> advQuestion;

	/** A list of elementary questions to retrieve hints */
	private List<ElementaryQuestion> eleHint;
	/** A list of standard Questions */
	private List<StandardQuestion> stans;
	/** A list of advanced questions to retrieve victory messages */
	private List<AdvancedQuestion> advMssg;
	
	/**
	 * Constructor for MovieQuestions.
	 * @param standard List of Standard Questions
	 * @param elementary List of Elementary Questions
	 * @param advanced List of Advanced Questions
	 */
	public MovieQuestions(List<ElementaryQuestion> elementary, List<StandardQuestion> standard, List<AdvancedQuestion> advanced){

		this.eleHint = elementary;
		this.stans = standard;
		this.advMssg = advanced;

		eleQuestion = new ArrayList<>(elementary.size());
		for(Question i: elementary)
			this.eleQuestion.add(i);

		staQuestion = new ArrayList<>(standard.size());
		for(Question i: standard)
			this.staQuestion.add(i);

		advQuestion = new ArrayList<>(advanced.size());
		for(Question i: advanced)
			this.advQuestion.add(i);

		this.advQuestionState = new AdvancedQuestionState(advQuestion);
		this.staQuestionState = new StandardQuestionState(staQuestion);
		this.eleQuestionState = new ElementaryQuestionState(eleQuestion);
		this.currentQuestionState = eleQuestionState;
		this.numCorrectAnswers = 0;
		this.numAttemptQuests = 0;
	}

	/**
	 * Tests if there are any more questions.
	 * @return true if there are, false if there are not
	 */
	public boolean hasMoreQuestions(){
		return currentQuestionState.hasMoreQuestions();
	}
	
	/**
	 * Get the text for the current question.
	 * @return the current question text
	 * @throws EmptyQuestionListException if there is no current question
	 */
	public String getCurrentQuestionText() throws EmptyQuestionListException{
		if(currentQuestionState.getCurrentQuestion() == null){
			throw new EmptyQuestionListException("Question Is Null");
		}
		return currentQuestionState.getCurrentQuestionText();
	}
	
	/**
	 * Get the possible answers for the current question
	 * @return the possible answers for the current question -- each answer
	 *         is a separate array element
	 * @throws EmptyQuestionListException if there is no current question
	 */
	public String[] getCurrentQuestionChoices() throws EmptyQuestionListException {
		if(currentQuestionState.getCurrentQuestion() == null){
			throw new EmptyQuestionListException();
		}
		return currentQuestionState.getCurrentQuestionChoices();
	}
	
	/**
	 * Process the user's answer to the current question.
	 * @param s the user's answer to the question
	 * @return the graded response to the question
	 * @throws EmptyQuestionListException if there is no current question
	 */
	public String processAnswer(String s) throws EmptyQuestionListException{
		if(currentQuestionState == advQuestionState){
			return advQuestionState.processAnswer(s);
		} else if(currentQuestionState == staQuestionState){
			return staQuestionState.processAnswer(s);
		} else{
			return eleQuestionState.processAnswer(s);
		}
	}
	
	/** 
	 * Returns the number of correct answered questions.
	 * @return number of correct questions
	 */
	public int getNumCorrectQuestions(){
		return this.numCorrectAnswers;
	}
	
	/**
	 * Returns the number of attempted questions.
	 * @return number of attempted questions
	 */
	public int getNumAttemptedQuestions(){
		return this.numAttemptQuests;
	}

	/**
	 * Returns the current state of the Finite State Machine.
	 * @return the current QuestionState
	 *//**/
	public QuestionState getCurrentState(){
		return this.currentQuestionState;
	}
	
	/** 
	 * Returns eleQuestionState.
	 * @return eleQuestionState
	 */
	public ElementaryQuestionState getEleQuestionState(){
		return this.eleQuestionState;
	}

	//---------------------------------------------------------------------------------------------
	/**
	 * The Advanced Question State that handles the Advanced Questions of the program.
	 * @author Thomas Ortiz, Corey Colberg
	 */
	public class AdvancedQuestionState extends QuestionState{
	
		/**
		 * Constructor for AdvancedQuestionState.
		 * @param list of Advanced Questions
		 */
		public AdvancedQuestionState(List<Question> list){
			super(list);
		}
		
		/**
		 * Processes the answer for Advanced Questions.
		 * @param s user's answer
		 * @throws EmptyQuestionListException if question is null
		 */
		public String processAnswer(String s) throws EmptyQuestionListException{
			if(currentQuestionState.getCurrentQuestion() == null){
				throw new EmptyQuestionListException();
			}
			// IF ANSWER IS CORRECT
			//if(s.equals(currentQuestionState.getCurrentQuestion().getAnswer().toLowerCase())){
			if(s.equals(currentQuestionState.getCurrentQuestion().getAnswer())){
				numCorrectAnswers++;
				numAttemptQuests++;
				currentQuestionState = advQuestionState;
				currentQuestionState.nextQuestion();

				return CORRECT + SEPARATOR;

			// IF ANSWER IS WRONG
			} else {
				String answer = currentQuestionState.getCurrentQuestionAnswer();
				currentQuestionState.getCurrentQuestion().setSeenTrue();
				currentQuestionState = staQuestionState;
				numAttemptQuests++;
				currentQuestionState.nextQuestion();
				return INCORRECT + "\n" + answer;
			}
		}
	}
	
	//---------------------------------------------------------------------------------------------
	/**
	 * The Standard Question State that handles the Standard Questions of the program.
	 * @author Thomas Ortiz, Corey Colberg
	 */
	public class StandardQuestionState extends QuestionState{
		
		/** Number of Standard Questions correct in a row */
		private int numCorrectInRow;
		/** Checks if we have answered the first Question */
		private boolean ansFirstAdvAlready;
		
		/**
		 * Constructor for StandardQuestionState.
		 * @param list of Standard Questions
		 */
		public StandardQuestionState(List<Question> list){
			super(list);
			numCorrectInRow = 0;
			ansFirstAdvAlready = false;
		}
		
		/**
		 * Processes the answer for Standard Questions.
		 * @param s user's answer
		 * @throws EmptyQuestionListException if question is null
		 */
		public String processAnswer(String s) throws EmptyQuestionListException{
			if(currentQuestionState.getCurrentQuestion() == null){
				throw new EmptyQuestionListException();
			}
			// IF THE ANSWER WAS CORRECT
			if(s.equals(currentQuestionState.getCurrentQuestion().getAnswer())){
			    numCorrectAnswers++;
				numAttemptQuests++;
				numCorrectInRow++; //Tracks Correct In Row 
				
				if(numCorrectInRow >= 2){
					currentQuestionState = advQuestionState;
					numCorrectInRow = 0;
					if(!(currentQuestionState.getCurrentQuestion() == advQuestionState.getFirstQuestion())){
						currentQuestionState.nextQuestion();
					} else if(ansFirstAdvAlready){ // Check this out!!@#!#@
						currentQuestionState.nextQuestion();
					}
					ansFirstAdvAlready = true;
					
				} else {
					currentQuestionState = staQuestionState;
					currentQuestionState.nextQuestion();
				}

				return CORRECT;
				
			// IF THE ANSWER WAS WRONG	
			} else {
				String answer = getCurrentState().getCurrentQuestionAnswer();
				numAttemptQuests++;
				numCorrectInRow = 0;
				currentQuestionState = eleQuestionState;
				currentQuestionState.nextQuestion();
				return INCORRECT + "\n" + answer;
			}
		}
	}
	
	//---------------------------------------------------------------------------------------------
	/**
	 * The Elementary Question State that handles the Elementary Questions of the program.
	 * @author Thomas Ortiz, Corey Colberg
	 */
	public class ElementaryQuestionState extends QuestionState{
		/** Number of attempts on a question */
		private int attempts;
		/** Number of questions right in a row */
		private int numCorrectInRow;
		/** Checks if we answered the first standard question already */
		private boolean ansFirstStanAlready;
		
		/**
		 * Constructor for ElementaryQuestionState.
		 * @param list of Elementary Questions
		 */
		public ElementaryQuestionState(List<Question> list){
			super(list);
			attempts = 0;
			numCorrectInRow = 0;
		}
		
		/**
		 * Processes the answer for Elementary Questions.
		 * @param s user's answer
		 * @throws EmptyQuestionListException if question is null
		 */
		public String processAnswer(String s) throws EmptyQuestionListException{
			if(currentQuestionState.getCurrentQuestion() == null){
				throw new EmptyQuestionListException();
			}  
			
			// IF THE ANSWER IS CORRECT
			//if(s.equals(currentQuestionState.getCurrentQuestion().getAnswer().toLowerCase())){
			if(s.equals(currentQuestionState.getCurrentQuestion().getAnswer())){
				numCorrectAnswers++;
				numCorrectInRow++;
				
				if(attempts == 0) {
					numAttemptQuests++;
				}

				// IF USER HAS ANSWERED 2 ELE QUESTIONS CORRECT IN A ROW GO TO STANDARD
				if(numCorrectInRow >= 2){
					currentQuestionState = staQuestionState;
					attempts = 0;
					numCorrectInRow = 0;
					
					// if the current question of staState is not the first question, move it. 
					if(!(currentQuestionState.getCurrentQuestion() == currentQuestionState.getFirstQuestion())){
						currentQuestionState.nextQuestion();
					} else if(ansFirstStanAlready){ //
						currentQuestionState.nextQuestion(); //
					}
					ansFirstStanAlready = true;
					
				} else { // IF USER **HASNT** ANSWER 2 ELE QUESTIONS CORRECT IN A ROW -->
					currentQuestionState = eleQuestionState;
					if(attempts == 0){
						numCorrectInRow++;
					}
					attempts = 0;
					currentQuestionState.nextQuestion();
				}
				return CORRECT;
				
			// IF THE ANSWER IS WRONG	
			} else {
				if(attempts != 0){
					numCorrectInRow = 0;
				}

				attempts++;
				//numCorrectInRow = 0;
				String hint = "";
				if(attempts >= 2){
					hint += currentQuestionState.getCurrentQuestionAnswer();
					currentQuestionState = eleQuestionState;
					currentQuestionState.nextQuestion();
					attempts = 0; // I removed this at some point for a reason?

				} else {
					currentQuestionState = eleQuestionState;
					numAttemptQuests++;
					hint = eleHint.get(eleQuestion.indexOf(
							  currentQuestionState.getCurrentQuestion())).getHint();
				}
				
				return INCORRECT + SEPARATOR + "\n" + hint;
			}
		}

		public void setAttemptsToZero(){
			this.attempts = 0;
		}

		public int getAttempts(){
			return this.attempts;
		}
	}
}
