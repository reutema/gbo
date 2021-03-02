package gui.mvp.undoredoquiz.model;


import java.util.Arrays;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

public class Model {
	
	private ObservableList<Question> scoreList;
	private int index;
	private Question dummy = new Question("dummy", new String[] {"dummy"}, 0);

	public Model() {
		this.scoreList = FXCollections.observableArrayList();;
		reset();
	}
	
	public void addQuestion(Question q) {
		this.scoreList.add(q);
	}

	
	public Question getNext() {
		Question q = scoreList.get(++index);
		return q;
	}
	
	public Question getCurrent() {
		return scoreList.get(index);
	}
	
	public int currentIndex() {
		return index;
	}
	
	public int numberOfQuestions() {
		return scoreList.size();
	}

	public void reset() {
		index = 0;
	}

	public void removeScores() {
		for(Question q : scoreList) {
			q.resetScores();
		}
		scoreList.add(dummy);
		scoreList.remove(dummy);
	}
	
	public ObservableList<Question> getScoreList(){
		
		return scoreList;
	}

	public void deleteQuestion(Question selectedQuestion) {
		scoreList.remove(selectedQuestion);
		
	}
	
	public void changeQuestion(Question oldQuestion, Question newQuestion) {
		scoreList.set(scoreList.indexOf(oldQuestion), newQuestion);
	}
	
	public int getIndexOf(Question question) {
		return scoreList.indexOf(question);
	}
	
	
	public void printAll() {
		
		for(Question q : scoreList) {
			System.out.println(q.getQuestion() + "; " + Arrays.toString(q.getPossibleAnswers()) + "; " + q.getIndexOfCorrectAnswer());
		}
	}

	public void insertQuestion(int position, Question question) {
		scoreList.add(position, question);
	}


}


