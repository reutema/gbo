package gui.mvp.basicquiz.model;

public class Question {

	
	private String question;
	private String[] possibleAnswers;
	private int indexOfCorrectAnswer;
	
	private int answered;
	private int correctAnswered;
	
	// weitere Attribute nach Bedarf
	public Question(String question, String[] possibleAnswers,	int indexOfCorrectAnswer)
	{
		if(indexOfCorrectAnswer < 0 || possibleAnswers.length <= indexOfCorrectAnswer) {
			throw new IllegalArgumentException("invalid index of correct answer");
		}
		
		this.question = question;
		this.possibleAnswers = possibleAnswers;
		this.indexOfCorrectAnswer = indexOfCorrectAnswer;
		
		resetScores();
	}
	
	public String getQuestion()
	{
		return question;
	}
	
	public String[] getPossibleAnswers()
	{
		return possibleAnswers;
	}
	
	public int getIndexOfCorrectAnswer()
	{
		return indexOfCorrectAnswer;
	}
	
	// weitere Methoden nach Bedarf

	
	public void answered(String selectedAnswer) {
		answered++;
		if(possibleAnswers[indexOfCorrectAnswer].equals(selectedAnswer)) {
			correctAnswered++;
		}
	}
	
	public void resetScores() {
		answered = 0;
		correctAnswered = 0;
	}
	
	public String toString() {
		return question + " (Antworten: " + answered + ", davon richtig: " + correctAnswered + ")";
		
	}
}
