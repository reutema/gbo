package gui.mvp.basicquiztable.model;

import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

public class Question {

	
	private StringProperty question;
	private String[] possibleAnswers;
	private int indexOfCorrectAnswer;
	
	private IntegerProperty answered;
	private IntegerProperty correctAnswered;
	
	// weitere Attribute nach Bedarf
	public Question(String question, String[] possibleAnswers,	int indexOfCorrectAnswer)
	{
		if(indexOfCorrectAnswer < 0 || possibleAnswers.length <= indexOfCorrectAnswer) {
			throw new IllegalArgumentException("invalid index of correct answer");
		}
		
		setQuestion(question);
		this.possibleAnswers = possibleAnswers;
		this.indexOfCorrectAnswer = indexOfCorrectAnswer;
		
		resetScores();
	}
	
	
	public String[] getPossibleAnswers()
	{
		return possibleAnswers;
	}
	
	public void setQuestion(String value) { 
    	questionProperty().set(value); }
    
    
    public String getQuestion() { 
    	return questionProperty().get(); 
    	
    }
    
    
    public int getIndexOfCorrectAnswer() {
    	return indexOfCorrectAnswer;
    }
    
    public StringProperty questionProperty() { 
        if (question == null) question = new SimpleStringProperty(this, "question");
        return question; 
    }
	
    public void setAnswered(int value) { 
    	answeredProperty().set(value); }
    
    
    public int getAnswered() { 
    	return answeredProperty().get(); 
    	
    }
    
    public IntegerProperty answeredProperty() { 
        if (answered == null) {
        	answered = new SimpleIntegerProperty(this, "answered");
        }
        return answered; 
    }
	
    
    
    public void setCorrectAnswered(int value) { 
    	correctAnsweredProperty().set(value); }
    
    
    public int getCorrectAnswered() { 
    	return correctAnsweredProperty().get(); 
    	
    }
    
    
    public IntegerProperty correctAnsweredProperty() { 
        if (correctAnswered == null) correctAnswered = new SimpleIntegerProperty(this, "correctAnswered");
        return correctAnswered; 
    }
	
	// weitere Methoden nach Bedarf

	
	public void answered(String selectedAnswer) {
		setAnswered(getAnswered() + 1);
		if(possibleAnswers[indexOfCorrectAnswer].equals(selectedAnswer)) {
			setCorrectAnswered(getCorrectAnswered() + 1); 
		}
	}
	
	public void resetScores() {
		setAnswered(0);
		setCorrectAnswered(0);
	}
	
	
}
